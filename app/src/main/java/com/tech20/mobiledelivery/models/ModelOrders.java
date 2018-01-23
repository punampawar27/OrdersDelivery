package com.tech20.mobiledelivery.models;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.tech20.mobiledelivery.database.DataAccess;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dao.NotesDao;
import com.tech20.mobiledelivery.database.dao.OrderDao;
import com.tech20.mobiledelivery.database.dao.OrderItemsDao;
import com.tech20.mobiledelivery.database.dataentities.DbModelNotes;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrderInventory;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrders;
import com.tech20.mobiledelivery.enums.EnumExpectedDeliveryTime;
import com.tech20.mobiledelivery.enums.EnumOrderStatus;
import com.tech20.mobiledelivery.executors.AppExecutor;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.RestClient;
import com.tech20.mobiledelivery.retrofitclient.ordersclient.Order;
import com.tech20.mobiledelivery.retrofitclient.ordersclient.OrderItem;
import com.tech20.mobiledelivery.retrofitclient.ordersclient.OrderNote;
import com.tech20.mobiledelivery.retrofitclient.ordersclient.OrdersBody;
import com.tech20.mobiledelivery.retrofitclient.ordersclient.OrdersClient;
import com.tech20.mobiledelivery.retrofitclient.ordersclient.OrdersResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


public class ModelOrders {

    public interface IOrdersLoaded{
        void onOrdersLoadedSuccess(List<DbModelOrders> list);
        void onOrdersLoadedFail(ErrorMessage errorMessage);
    }

    private IOrdersLoaded ordersLoaded = null;
    private Converter<ResponseBody,ErrorMessage> errorConvertor = null;
    private DatabaseHouse dataHouse = null;
    private PreferenceUtils prefUtils = null;

    public ModelOrders(IOrdersLoaded ordersLoaded){
        this.ordersLoaded = ordersLoaded;
    }

    public void getOrdersFromAppServer(DatabaseHouse dataHouse, PreferenceUtils preferenceUtils, String deliveryDate){

        this.dataHouse = dataHouse;
        this.prefUtils = preferenceUtils;
        errorConvertor = RestClient.getErrorConverter();
        OrdersClient ordersClient = RestClient.createServiceWithHeaders(OrdersClient.class,prefUtils);

        OrdersBody orderBody = new OrdersBody();
        orderBody.setDriverId(prefUtils.getString(Constants.PreferenceConstants.DRIVER_ID));
        orderBody.setDeliveryDate(deliveryDate);

        Call<List<OrdersResponse>> call = ordersClient.getOrders(orderBody);
        call.enqueue(callback);
    }

    public void getOrdersFromDatabase(IOrdersLoaded ordersLoaded,DatabaseHouse dataHouse,PreferenceUtils prefUtils){

        DataAccess.execute(new RunnableGetOrdersFromDatabase(ordersLoaded,
                                                            dataHouse,
                                                            prefUtils.getString(Constants.PreferenceConstants.ROUTE_ID)));
    }


    private Callback<List<OrdersResponse>> callback = new Callback<List<OrdersResponse>>() {
        @Override
        public void onResponse(Call<List<OrdersResponse>> call, Response<List<OrdersResponse>> response) {


            Log.d(Constants.LogConstants.TAG_DBASE,""+response.isSuccessful());

            if(response.isSuccessful()){

                DataAccess.execute(new RunnableInsertOrders((msg)->{

                    AppExecutor.getINSTANCE().getMainThread().execute(()->{

                        getOrdersFromDatabase(ordersLoaded,dataHouse,prefUtils);

                    });

                    return true;


                }, response.body().get(0), dataHouse));


            }else{


              DataAccess.execute(new RunnableDeleteOrdersFromDatabase(dataHouse,ordersLoaded));

                ErrorMessage errorMessage = null;

                try {

                    errorMessage = errorConvertor.convert(response.errorBody());

                } catch (IOException e) {

                    e.printStackTrace();
                    errorMessage = ErrorMessage.getBlankError();

                }
                finally {
                    ordersLoaded.onOrdersLoadedFail(errorMessage);
                }
            }

        }

        @Override
        public void onFailure(Call<List<OrdersResponse>> call, Throwable t) {

            ErrorMessage msg = new ErrorMessage();
            ordersLoaded.onOrdersLoadedFail(msg);
        }
    };


    private static class RunnableDeleteOrdersFromDatabase implements Runnable{
        private DatabaseHouse dataHouse = null;
        private IOrdersLoaded iOrdersLoaded = null;
        private String routeId = null;

        public RunnableDeleteOrdersFromDatabase(DatabaseHouse dataHouse,IOrdersLoaded iOrdersLoaded){
            this.dataHouse = dataHouse;
            this.iOrdersLoaded = iOrdersLoaded;
        }

        @Override
        public void run() {
            Log.d(Constants.LogConstants.TAG_DBASE,"inside RunnableDeleteOrdersFromDatabase run");
            dataHouse.getOrderDao().deleteAll();

            AppExecutor.getINSTANCE().getMainThread().execute(()->{

                iOrdersLoaded.onOrdersLoadedSuccess(null);

            });
        }
    }


    private static class RunnableGetOrdersFromDatabase implements Runnable{

        private DatabaseHouse dataHouse = null;
        private IOrdersLoaded iOrdersLoaded = null;
        private String routeId = null;

        public RunnableGetOrdersFromDatabase(IOrdersLoaded iOrdersLoaded ,DatabaseHouse dataHouse,String routeId){

            this.iOrdersLoaded=iOrdersLoaded;
            this.dataHouse = dataHouse;
            this.routeId = routeId;

            if(TextUtils.isEmpty(routeId)){
                throw new Error("RouteId cannot be empty");
            }
        }

        @Override
        public void run() {



            List<DbModelOrders> listOrder = dataHouse.getOrderDao().getOrders(routeId);

            Log.d(Constants.LogConstants.TAG_DBASE,"For query routeId:"+routeId+" orderSize:"+listOrder.size());

            for(DbModelOrders dbOrder:listOrder){

                List<DbModelOrderInventory> orderItems = dataHouse.getOrderItemsDao().getOrderItems(dbOrder.getOrderId());

                List<DbModelNotes> orderNotes = dataHouse.getNotesDao().getNotes(dbOrder.getOrderId());

                dbOrder.setListOrderItems(orderItems);

                dbOrder.setListNotes(orderNotes);

            }

            AppExecutor.getINSTANCE().getMainThread().execute(()->{

                iOrdersLoaded.onOrdersLoadedSuccess(listOrder);

            });

        }
    }
    private static class RunnableInsertOrders implements Runnable{

        private Handler.Callback callBack = null;
        private DatabaseHouse dataHouse = null;
        private OrdersResponse ordersResponse = null;

        private RunnableInsertOrders(Handler.Callback callBack ,OrdersResponse ordersResponse,DatabaseHouse dataHouse){
            this.callBack = callBack;
            this.ordersResponse = ordersResponse;
            this.dataHouse = dataHouse;
        }

        @Override
        public void run() {

            OrderDao orderDao = dataHouse.getOrderDao();
            OrderItemsDao orderItemsDao = dataHouse.getOrderItemsDao();
            NotesDao notesDaoDao = dataHouse.getNotesDao();

            //Clear Orders Database
            orderDao.deleteAll();

            List<Order> listOrder = ordersResponse.getListOrders();

            //Take Each Order
            for(Order order : listOrder){

                if(order.getStatus() == EnumOrderStatus.Delivered.getValue()){
                    //If Order is delived, avoid adding that order
                    continue;
                }
                Log.d(Constants.LogConstants.TAG_DBASE,"routId:"+ordersResponse.getRouteId()+" orderId:"+order.getOrderId());

                DbModelOrders dbOrder = new DbModelOrders();
                dbOrder.setOrderId(order.getOrderId());
                dbOrder.setRouteId(ordersResponse.getRouteId());
                dbOrder.setActualDeliveryDate(order.getActualDeliveryDate());
                dbOrder.setContactNumber(order.getContactNumber());
                dbOrder.setCustomerName(order.getCustomerName());
                dbOrder.setDeliveryAttempts(order.getDeliveryAttempts());
                dbOrder.setDiscount(order.getDiscount());
                dbOrder.setExpectedDeliveryDate(order.getExpectedDeliveryDate());
                dbOrder.setExpectedDeliveryTime(EnumExpectedDeliveryTime.getEnumByName(order.getExpectedDeliveryTime()).ordinal()); //order.getExpectedDeliveryTime()
                dbOrder.setInvoiceNo(order.getInvoiceNo());
                dbOrder.setIsDirty(order.getIsDirty());
                dbOrder.setIsTaxExempted(order.getIsTaxExempted());
                dbOrder.setPaymentReceived(order.getPaymentReceived());
                dbOrder.setSequenceNo(order.getSequenceNo());
                dbOrder.setShippingAddress(order.getShippingAddress());
                dbOrder.setStatus(order.getStatus());
                dbOrder.setSubTotal(order.getSubTotal());
                dbOrder.setTax(order.getTax());
                dbOrder.setVerified(order.isVerifiedIdentity());
                //Insert
                orderDao.insert(dbOrder);

                //Take Each Item from Order and add in List
                List<DbModelOrderInventory> listOrderItems = new ArrayList<>();

                if(order.getOrderItemList()!=null){
                    for(OrderItem orderItem:order.getOrderItemList()){

                        DbModelOrderInventory dbModelOrderItem = new DbModelOrderInventory();
                        dbModelOrderItem.setBasePrice(orderItem.getBasePrice());
                        dbModelOrderItem.setDiscount(orderItem.getDiscount());
                        dbModelOrderItem.setIsOnSportOrder(orderItem.getIsOnSportOrder());
                        dbModelOrderItem.setItemCode(orderItem.getItemCode());
                        dbModelOrderItem.setItemName(orderItem.getItemName());
                        dbModelOrderItem.setOrderId(orderItem.getOrderId());
                        dbModelOrderItem.setOrderItemId(orderItem.getOrderItemId());
                        dbModelOrderItem.setOrderType(orderItem.getOrderType());
                        dbModelOrderItem.setPackageSize(orderItem.getPackageSize());
                        dbModelOrderItem.setPlaceOrderId(orderItem.getPlaceOrderId());
                        dbModelOrderItem.setPrice(orderItem.getPrice());
                        dbModelOrderItem.setQuantity(orderItem.getQuantity());
                        dbModelOrderItem.setRouteInvenotoryId(orderItem.getRouteInvenotoryId());
                        dbModelOrderItem.setStockItemCode(orderItem.getStockItemCode());
                        dbModelOrderItem.setTax(orderItem.getTax());
                        listOrderItems.add(dbModelOrderItem);
                    }
                    //Insert All items in table
                    orderItemsDao.insertAll(listOrderItems);
                }

                //Take Each Note from Order
                List<DbModelNotes> listNotes = new ArrayList<>();
                if(order.getNotesList()!=null){
                    for(OrderNote note : order.getNotesList()){

                        DbModelNotes dbModelNotes = new DbModelNotes();
                        dbModelNotes.setOrderId(note.getOrderId());
                        dbModelNotes.setDateCreated(note.getDateCreated());
                        dbModelNotes.setNote(note.getNote());
                        dbModelNotes.setOrderDeliveryNotesId(note.getOrderDeliveryNotesId());

                        listNotes.add(dbModelNotes);
                    }

                    //Insert All notes
                    notesDaoDao.insertAll(listNotes);
                }

            }

            callBack.handleMessage(null);
        }
    }
}
