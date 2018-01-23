package com.tech20.mobiledelivery.models;


import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrders;
import com.tech20.mobiledelivery.enums.EnumOrderStatus;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.UtilDateFormat;
import com.tech20.mobiledelivery.location.LocationHelper;
import com.tech20.mobiledelivery.models.Entities.EntitySelectedOrderInventory;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.retrofitclient.RestClient;
import com.tech20.mobiledelivery.retrofitclient.deliverorderclient.DeliverOrderBody;
import com.tech20.mobiledelivery.retrofitclient.deliverorderclient.DeliverOrderBody.OrderDeliveryExtraItems;
import com.tech20.mobiledelivery.retrofitclient.deliverorderclient.DeliverOrderClient;
import com.tech20.mobiledelivery.retrofitclient.deliverorderclient.DeliverOrderResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class ModelDeliverOrder {

    public interface ICallBackDeliverOrder{
        void deliverOrderSuccess(DeliverOrderResponse deliverOrderResponse);
        void deliverOrderFail(ErrorMessage errorMessage);
    }

    private ICallBackDeliverOrder callBackDeliverOrder = null;
    private Converter<ResponseBody,ErrorMessage> errorConverter = null;

    public ModelDeliverOrder(ICallBackDeliverOrder callBackDeliverOrder){
        this.callBackDeliverOrder=callBackDeliverOrder;
        errorConverter = RestClient.getErrorConverter();
    }

    public void submitOrder(PreferenceUtils prefUtils, String orderId,
                            String status,
                            List<EntitySelectedOrderInventory> listOrderedInventory,
                            String note){

        DeliverOrderBody body = new DeliverOrderBody();
        body.setDriverId(prefUtils.getString(Constants.PreferenceConstants.DRIVER_ID));
        body.setRouteId(prefUtils.getString(Constants.PreferenceConstants.ROUTE_ID));
        body.setDeliveryDate(UtilDateFormat.getToday());
        body.setIsDeliveryLocation(String.valueOf(true));
        body.setLatitude(String.valueOf(prefUtils.getDouble(Constants.PreferenceConstants.LAST_LOCATOIN_LATTITUDE)));
        body.setLongitude(String.valueOf(prefUtils.getDouble(Constants.PreferenceConstants.LAST_LOCATOIN_LONGITUDE)));
        body.setOrderId(orderId);
        body.setStatus(status);


        DeliverOrderBody.OrderDeliveryNote orderDeliveryNote = new DeliverOrderBody.OrderDeliveryNote();
        orderDeliveryNote.setNote(note);
        orderDeliveryNote.setDateCreated(UtilDateFormat.getToday());
        body.setOrderNote(orderDeliveryNote);

        List<OrderDeliveryExtraItems> listExtraItems = new ArrayList<>();
        for(EntitySelectedOrderInventory ent:listOrderedInventory){
            OrderDeliveryExtraItems item = new OrderDeliveryExtraItems();
            item.setItemName(ent.getItemName());
            item.setDiscount(ent.getDiscount());
            item.setPackageSize(ent.getPackageSize());
            item.setPrice(ent.getPrice());
            item.setQuantity(ent.getQuantity());
            item.setTax(ent.getTax());

            //This is some kind of weird logic because of inconsistent response from server.
            if(ent.getITEM_TYPE() == EntitySelectedOrderInventory.OrderItemType.EXTRA_INVENTORY.ordinal()){
                item.setItemCode(ent.getBarCode());
                item.setStockItemCode(ent.getItemCode());
            }else{
                item.setItemCode(ent.getItemCode());
                item.setStockItemCode(ent.getStockItemCode());
            }
            listExtraItems.add(item);
        }
        body.setItemsList(listExtraItems);


        DeliverOrderClient client       = RestClient.createServiceWithHeaders(DeliverOrderClient.class,prefUtils);
        Call<DeliverOrderResponse> call = client.sendOrderStatus(body);

        prefUtils.putBoolean(Constants.PreferenceConstants.IS_ORDER_REFRESHED,true);
        call.enqueue(callBack);
    }

    private Callback<DeliverOrderResponse> callBack = new Callback<DeliverOrderResponse>() {
        @Override
        public void onResponse(Call<DeliverOrderResponse> call,
                               Response<DeliverOrderResponse> response) {

            if(response.isSuccessful()){
                DeliverOrderResponse orderResponse = response.body();
                callBackDeliverOrder.deliverOrderSuccess(orderResponse);
            }else{
                ErrorMessage errorMessage = null;

                try {
                    errorMessage = errorConverter.convert(response.errorBody());
                } catch (IOException e) {
                    e.printStackTrace();

                    errorMessage = ErrorMessage.getBlankError();
                }finally {
                    callBackDeliverOrder.deliverOrderFail(errorMessage);
                }
            }
        }

        @Override
        public void onFailure(Call<DeliverOrderResponse> call, Throwable t) {
            callBackDeliverOrder.deliverOrderFail(new ErrorMessage(t.getMessage()));
        }
    };
}
