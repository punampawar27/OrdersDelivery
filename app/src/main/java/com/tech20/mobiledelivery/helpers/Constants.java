package com.tech20.mobiledelivery.helpers;

public class Constants {

    public static final class INTENTEXTRAS{
        public static final String EXTRA_PUSHNOTIFICATOIN_FLAG="com.tech20.mobiledelivery.EXTRA_PUSHNOTIFICATOIN_FLAG";
    }
    public static final class INTENTACTIONS{
        public static final String ACTION_PUSH_RECEIVED="com.tech20.mobiledelivery.ACTION_PUSH_RECEIVED";
    }
    public static final class DiscountConstants{

        public static final String MIXMATCH_SALESPRICE      ="Mix and Match : Sales Price";
        public static final String MIXMATCH_PERCENTAGEPRICE ="Mix and Match : Percentage off";
        public static final String BULK                     ="BULK";
        public static final String PROMOTION_TYPE_PERCENT   ="%";
        public static final String PROMOTION_TYPE_DOLLAR    ="$";
    }
    public static final class RequestPermissionConstant{

        public static final int request_all_permission=1;
    }

    public static final class REQUESTCODES{

        public static final int REQUEST_CHECK_SETTINGS = 1;
        public static final int REQUEST_IMAGE_PICKER_REQUEST_CODE= 2;
        public static final int REQUEST_CODE_FOR_RESULT_EXTRA_INVENTROY= 3;

    }

    //Constants for logs to avoid hardcoded strings.
    public static final class LogConstants {

        public static final String COMMON_LOG_FILE  ="logs";
        public static final String TAG_WASTE        ="WASTE";
        public static final String TAG_RESPONSE     ="RESPONSE";
        public static final String TAG_FIREBASE     ="FIREBASEWASTE";
        public static final String TAG_LATCH        ="LATCH";
        public static final String TAG_REC_LOCATION ="REC_LOCATION";
        public static final String TAG_DBASE        ="DBASE";
    }


    public static final class PreferenceConstants {

        //session_id
        public static final String UNIQUE_INSTALLATION_ID       ="PREF_UNIQ_INSTALL_ID";
        //deviceId
        public static final String FIREBASE_TOKEN               ="PREF_FIREBASE_TOKEN";
        public static final String CONTACT_NO                   ="PREF_CONTACT_NO";
        public static final String APP_STATE                    ="PREF_APP_STATE";
        public static final String DRIVER_ID                    ="PREF_DRIVER_ID";
        public static final String STORE_ID                     ="PREF_STORE_ID";
        public static final String API_KEY                      ="PREF_API_KEY";
        public static final String API_SECRET                   ="PREF_API_SECRET";
        public static final String SYNC_DATE                    ="PREF_SYNC_DATE";
        public static final String ROUTE_ID                     ="PREF_ROUTE_ID";
        public static final String ROUTE_STATUS                 ="PREF_ROUTE_STATUS";

        public static final String PROFILE_PIC_NAME             ="PREF_PROFILE_PIC_NAME";

        public static final String LAST_LOCATOIN_LATTITUDE      ="PREF_LAST_LOCATOIN_LATTITUDE";
        public static final String LAST_LOCATOIN_LONGITUDE      ="PREF_LAST_LOCATOIN_LONGITUDE";
        public static final String IS_ORDER_REFRESHED           ="PREF_IS_ORDER_REFRESHED";
    }
    public static final class UrlConstants{


        public static final String SERVER_URL=ServerConstants.URL;

       public static final String METHOD_AUTHENTICATE_USER              = SERVER_URL + "Drivers/Login";
       public static final String METHOD_GET_STORES                     = SERVER_URL + "Drivers/FetchAllStore";
       public static final String METHOD_GET_ORDERS                     = SERVER_URL + "Drivers/GetDriverRoutes";
       public static final String METHOD_GET_ORDER_DETAILS              = SERVER_URL + "Drivers/GetOrderItems/";
       public static final String METHOD_START_DUTY                     = SERVER_URL + "Drivers/UpdateAvailabilityStatus";
       public static final String METHOD_GET_ALL_FAQ                    = SERVER_URL + "FAQ/AllFAQs";
       public static final String METHOD_RAISE_QUESTION                 = SERVER_URL + "FAQ/RaiseQuestion";
       public static final String METHOD_GET_CASH_DRAWER                = SERVER_URL + "Drivers/GetCashDrawer";
       public static final String METHOD_GET_DRIVER_INFO                = SERVER_URL + "Drivers/GetDriverDetails/";
       public static final String METHOD_SAVE_DRIVER_INFO               = SERVER_URL + "Drivers/UpdateDriverDetails/";
       public static final String METHOD_CHANGE_PASSWORD                = SERVER_URL + "Drivers/ChangePassword";
       public static final String METHOD_GET_INVENTORY                  = SERVER_URL + "Routes/GetRouteInventory/";
       public static final String METHOD_GET_EXTRA_INVENTORY            = SERVER_URL + "Routes/GetRouteExtraInventory/";
       public static final String METHOD_GET_CUSTOMER                   = SERVER_URL + "Routes/GetCustomers/";
       public static final String METHOD_UPDATE_ROUTE_ORDER_DIRTY_FLAG  = SERVER_URL + "Routes/UpdateRouteOrderDirtyFlag";
       public static final String METHOD_ADD_ITEM_TO_ORDER              = SERVER_URL + "Orders/AddItemToOrder/";
       public static final String METHOD_START_ROUTE                    = SERVER_URL + "Drivers/StartRoute";
       public static final String METHOD_END_ROUTE                      = SERVER_URL + "Drivers/EndRoute";
       public static final String METHOD_SAVE_DRIVER_LOCATION           = SERVER_URL + "DriverLocations/SaveDriverLocation";
       public static final String METHOD_LOGOUT                         = SERVER_URL+"Drivers/Logout";
       public static final String METHOD_UPDATE_DELIVERY_STATUS         = SERVER_URL + "Orders/UpdateOrderStatus";
       public static final String METHOD_GET_NOTES                      = SERVER_URL + "Orders/GetOrderDeliveryNotes/";

       public  static  final String METHOD_SAVE_DEVICE_ID               = SERVER_URL + "Drivers/SaveDeviceId";

        public static final String POST_PARAMS_DRIVERID = "driverId";
        public static final String POST_PARAMS_STOREID = "storeId";

        public static final String HEADER_API_KEY                       = "api_key";
        public static final String HEADER_API_SECRET                    = "api_secret";
        public static final String HEADER_SESSION_ID                    = "session_id";
        public static final String HEADER_CONTENT_TYPE                  = "Content-Type";
        public static final String HEADER_APPLICATION_JSON              = "application/json";


    }

    public static final class DatabaseConstants{

        public static final String DATABASE_NAME                    ="MOBILE_DRIVER";
        public static final String COLUMN_NAME_AUTOINCREMENT_ID     ="ID";

        public static final class TABLE_DRIVER{
            public static final String TABLE_NAME_DRIVER        ="DRIVER";


            public static final String COLUMN_NAME_API_KEY      ="API_KEY";
            public static final String COLUMN_NAME_API_SECRET   ="API_SECRET";
            public static final String COLUMN_NAME_STORE_ID     ="STORE_ID";
            public static final String COLUMN_NAME_DRIVER_ID    ="DRIVER_ID";
            public static final String COLUMN_NAME_FIRST_NAME   ="FIRST_NAME";
            public static final String COLUMN_NAME_LAST_NAME    ="LAST_NAME";
            public static final String COLUMN_NAME_USERNAME     ="USERNAME";
            public static final String COLUMN_NAME_CITY         ="CITY";
            public static final String COLUMN_NAME_STATE        ="STATE";
            public static final String COLUMN_NAME_ZIPCODE      ="ZIPCODE";
            public static final String COLUMN_NAME_Country      ="COUNTRY";
            public static final String COLUMN_NAME_LICENCENO    ="LICENCENO";
            public static final String COLUMN_NAME_VEHICLENUMBER="VEHICLENUMBER";
            public static final String COLUMN_NAME_MOBILENUMBER ="MOBILENUMBER";
            public static final String COLUMN_NAME_ADDRESSLINE1 ="ADDRESSLINE1";
            public static final String COLUMN_NAME_ADDRESSLINE2 ="ADDRESSLINE2";

        }

        public static final class TABLE_STORE{
            public static final String TABLE_NAME_STORE="STORE";

            public static final String COLUMN_NAME_STORE_ID     ="STORE_ID";
            public static final String COLUMN_NAME_SHOP_NAME    ="SHOP_NAME";
            public static final String COLUMN_NAME_EMAIL        ="EMAIL";
            public static final String COLUMN_NAME_CONTACT_NO   ="CONTACT_NO";
            public static final String COLUMN_NAME_ADDRESS_LINE_1="ADDRESS_LINE_1";
            public static final String COLUMN_NAME_ADDRESS_LINE_2="ADDRESS_LINE_2";
            public static final String COLUMN_NAME_CITY         ="CITY";
            public static final String COLUMN_NAME_STATE_CODE   ="STATE_CODE";
            public static final String COLUMN_NAME_COUNTRY_CODE ="COUNTRY_CODE";
            public static final String COLUMN_NAME_POSTAL_CODE  ="POSTAL_CODE";

        }

        public static final class TABLE_FAQ{
            public static final String TABLE_NAME_FAQ="FAQ";

            public static final String COLUMN_NAME_FAQ_ID="FAQ_ID";
            public static final String COLUMN_NAME_QUESTION="QUESTION";
            public static final String COLUMN_NAME_ANSWER="ANSWER";
            public static final String COLUMN_NAME_DATECREATED="DATECREATED";
        }

        public static final class TABLE_CUSTOMER{
            public static final String TABLE_NAME_CUSTOMER="CUSTOMER";

            public static final String COLUMN_NAME_CUSTOMER_NAME="CUSTOMER_NAME";
            public static final String COLUMN_NAME_CONTACT_NUMBER="CONTACT_NUMBER";
            public static final String COLUMN_NAME_SHIPPING_ADDRESS="SHIPPING_ADDRESS";
        }

        public static final class TABLE_SQLITE_MASTER {
            public static final String TABLE_NAME_SQLITE_MASTER="sqlite_master";

            public static final String COLUMN_NAME_NAME="name";
            public static final String COLUMN_NAME_TYPE="type";

        }

        public static final class TABLE_CASHDRAWER{

            public static final String TABLE_NAME_CUSTOMER="CASHDRAWER";

            public static final String COLUMN_NAME_DRAWERID="DRAWERID";
            public static final String COLUMN_NAME_DRIVERID="DRIVERID";
            public static final String COLUMN_NAME_DELIVERYDATE="DELIVERYDATE";
            public static final String COLUMN_NAME_CASH="CASH";
            public static final String COLUMN_NAME_CLIENTID="CLIENTID";
            public static final String COLUMN_NAME_ROUTEID="ROUTEID";
            public static final String COLUMN_NAME_ORDERID="ORDERID";
        }

        public static final class TABLE_ORDERS{
            public static final String TABLE_NAME_ORDERS="ORDERS";

            public static final String COLUMN_NAME_ORDER_ID="ORDER_ID";
            public static final String COLUMN_NAME_ROUTE_ID="ROUTE_ID";
            public static final String COLUMN_NAME_INVOICENO="InvoiceNo";
            public static final String COLUMN_NAME_EXPECTEDDELIVERYDATE="EXPECTEDDELIVERYDATE";
            public static final String COLUMN_NAME_EXPECTEDDELIVERYTIME="EXPECTEDDELIVERYTIME";
            public static final String COLUMN_NAME_ACTUALDELIVERYDATE="ACTUALDELIVERYDATE";
            public static final String COLUMN_NAME_SEQUENCENO="SEQUENCENO";
            public static final String COLUMN_NAME_STATUS="Status";
            public static final String COLUMN_NAME_PAYMENTRECEIVED="PaymentReceived";
            public static final String COLUMN_NAME_CUSTOMERNAME="CUSTOMERNAME";
            public static final String COLUMN_NAME_CONTACTNUMBER="CONTACTNUMBER";
            public static final String COLUMN_NAME_SHIPPINGADDRESS="SHIPPINGADDRESS";
            public static final String COLUMN_NAME_ISTAXEXEMPTED="ISTAXEXEMPTED";
            public static final String COLUMN_NAME_SUBTOTAL="SUBTOTAL";
            public static final String COLUMN_NAME_TAX="TAX";
            public static final String COLUMN_NAME_DISCOUNT="DISCOUNT";
            public static final String COLUMN_NAME_DELIVERYATTEMPTS="DELIVERYATTEMPTS";
            public static final String COLUMN_NAME_ISDIRTY="ISDIRTY";
            public static final String COLUMN_NAME_ISVERIFIED="ISVERIFIED";
        }

        public static final class TABLE_ORDER_ITEMS{
            public static final String TABLE_NAME_ORDERITEMS="ORDER_ITEMS";

            public static final String COLUMN_NAME_ORDER_ITEM_ID="ORDER_ITEM_ID";
            public static final String COLUMN_NAME_ORDERID="ORDERID";
            public static final String COLUMN_NAME_PLACEORDERID="PLACEORDERID";
            public static final String COLUMN_NAME_ITEM_CODE="ITEM_CODE";
            public static final String COLUMN_NAME_ITEM_NAME="ITEM_NAME";
            public static final String COLUMN_NAME_QUANTITY="QUANTITY";
            public static final String COLUMN_NAME_PRICE="PRICE";
            public static final String COLUMN_NAME_BASE_PRICE="BASE_PRICE";
            public static final String COLUMN_NAME_PACKAGE_SIZE="PACKAGE_SIZE";
            public static final String COLUMN_NAME_ISONSPORTORDER="ISONSPORTORDER";
            public static final String COLUMN_NAME_ROUTEINVENOTORYID="ROUTEINVENOTORYID";
            public static final String COLUMN_NAME_TAX="Tax";
            public static final String COLUMN_NAME_ORDERTYPE="ORDERTYPE";
            public static final String COLUMN_NAME_STOCKITEMCODE="STOCKITEMCODE";
            public static final String COLUMN_NAME_DISCOUNT="DISCOUNT";
        }

        public static final class TABLE_NOTES {
            public static final String TABLE_NAME_NOTES= "NOTES";

            public static final String COLUMN_NAME_ORDERDELIVERYNOTESID="ORDERDELIVERYNOTESID";
            public static final String COLUMN_NAME_ORDERID="ORDERID";
            public static final String COLUMN_NAME_NOTE="NOTE";
            public static final String COLUMN_NAME_DATECREATED="DATECREATED";
        }
    }

    public static final class FILECONSTANTS{
        public static final String PROFILE_PIC_PREPEND="profilepic_";
    }

    public static final class MAPCONST{
        public static final String MAP_STRING="http://maps.google.com/maps?daddr=";
    }

    public static final class NOTIFICATION{
        public static final String NOTIFICATION_CHANNEL_GLOBAL ="NOTIFICATION_CHANNEL_GLOBAL";

        public static final int NOTIFICATION_ID_SERVICESAVE_FORGROUND=1000;
        public static final int NOTIFICATION_ID_PUSH_DATA=1001;
    }
}
