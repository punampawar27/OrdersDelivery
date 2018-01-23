package com.tech20.mobiledelivery.retrofitclient.ordersclient;

import com.google.gson.annotations.SerializedName;
import com.tech20.mobiledelivery.helpers.Constants;

import java.util.List;

import retrofit2.http.POST;


public class OrdersResponse {


    /*
 [
    {
        "RouteId": 1542,
        "Title": "new",
        "Orders": [
            {
                "OrderId": 2152,
                "InvoiceNo": 48,
                "OrderNote": [
                    {
                        "OrderDeliveryNotesId": 1822,
                        "OrderId": 2152,
                        "OrderNote": "This is whole order Notes",
                        "DateCreated": "2017-12-14T12:17:14.7230837"
                    }
                ],
                "ExpectedDeliveryDate": "2017-12-14T00:00:00",
                "ExpectedDeliveryTime": "Anytime",
                "ActualDeliveryDate": "0001-01-01T00:00:00",
                "order_items": [
                    {
                        "OrderItemId": 3309,
                        "OrderId": 2152,
                        "PlaceOrderId": 0,
                        "item_code": "3944839647971",
                        "item_name": "Atoms G5 Vaporizer",
                        "quantity": 1,
                        "price": 45,
                        "base_price": 45,
                        "package_size": "Ea",
                        "isOnSportOrder": false,
                        "routeInvenotoryId": 0,
                        "Tax": 0,
                        "OrderType": 1,
                        "StockItemCode": "57",
                        "Discount": 0
                    },
                    {
                        "OrderItemId": 3310,
                        "OrderId": 2152,
                        "PlaceOrderId": 0,
                        "item_code": "4422356131020",
                        "item_name": "Jack Frost(Eighth Oz\r\n)",
                        "quantity": 1,
                        "price": 60,
                        "base_price": 60,
                        "package_size": "Bulk",
                        "isOnSportOrder": false,
                        "routeInvenotoryId": 0,
                        "Tax": 0,
                        "OrderType": 1,
                        "StockItemCode": "25",
                        "Discount": 0
                    }
                ],
                "SequenceNo": 1,
                "Status": 1,
                "PaymentReceived": false,
                "CustomerName": "Elaina Head",
                "ContactNumber": "(708) 515-7776",
                "ShippingAddress": "4388 Norman R Bobins  Spokane WA 99211",
                "IsTaxExempted": false,
                "SubTotal": 105,
                "Tax": 7.35,
                "Discount": -2.25,
                "DeliveryAttempts": 0,
                "IsDirty": true
            }
        ]
    }
]
     */

    @SerializedName("RouteId")
    private String routeId = null;

    @SerializedName("Title")
    private String title = null;

    @SerializedName("Orders")
    private List<Order> listOrders = null;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Order> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Order> listOrders) {
        this.listOrders = listOrders;
    }
}
