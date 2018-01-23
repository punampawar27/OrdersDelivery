package com.tech20.mobiledelivery.adapters;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrders;
import com.tech20.mobiledelivery.enums.EnumExpectedDeliveryTime;
import com.tech20.mobiledelivery.enums.EnumOrderStatus;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;

import java.util.List;

public class AdapterOrders extends RecyclerView.Adapter<AdapterOrders.ViewHolderOrders>{



    public static class ViewHolderOrders extends RecyclerView.ViewHolder{

        private TextView txtValueOrderId  =null,txtValueAddress  =null;
        private ImageView imgCall = null, imgMap = null;
        private RelativeLayout relParent = null;


        public ViewHolderOrders(View itemView, View.OnClickListener onClick) {
            super(itemView);

            txtValueOrderId = itemView.findViewById(R.id.txtValueOrderId);
            txtValueAddress = itemView.findViewById(R.id.txtValueAddress);
            imgCall = itemView.findViewById(R.id.imgCall);
            imgMap = itemView.findViewById(R.id.imgMap);
            relParent = itemView.findViewById(R.id.relParent);

            imgCall.setOnClickListener(onClick);
            imgMap.setOnClickListener(onClick);
            relParent.setOnClickListener(onClick);
        }
    }


    private Context mContext = null;
    private View.OnClickListener onClick = null;
    private List<DbModelOrders> listOrders = null;

    public AdapterOrders(View.OnClickListener onClick,Context mContext,List<DbModelOrders> listOrders){
        this.onClick = onClick;
        this.listOrders = listOrders;
        this.mContext = mContext;
    }

    @Override
    public ViewHolderOrders onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderOrders(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_orders,parent,false),onClick);
    }

    @Override
    public void onBindViewHolder(ViewHolderOrders holder, int position) {

        DbModelOrders dbOrder = listOrders.get(position);

        holder.txtValueOrderId.setText(dbOrder.getOrderId());
        holder.txtValueAddress.setText(dbOrder.getShippingAddress());

        holder.imgMap.setTag(position);
        holder.imgCall.setTag(position);
        holder.relParent.setTag(position);

        if(dbOrder.getStatus() == EnumOrderStatus.UnDelivered.getValue()){
            holder.relParent.setBackgroundColor(ContextCompat.getColor(mContext,R.color.trans_red));
        }else if(EnumExpectedDeliveryTime.Morning.ordinal() == dbOrder.getExpectedDeliveryTime()){
            holder.relParent.setBackgroundColor(ContextCompat.getColor(mContext,R.color.color_alice_blue));
        }else if(EnumExpectedDeliveryTime.AFTERNOON.ordinal() == dbOrder.getExpectedDeliveryTime()){
            holder.relParent.setBackgroundColor(ContextCompat.getColor(mContext,R.color.color_lavender_blush));
        }else if(EnumExpectedDeliveryTime.Evening.ordinal() == dbOrder.getExpectedDeliveryTime()){
            holder.relParent.setBackgroundColor(ContextCompat.getColor(mContext,R.color.color_moccasin));
        }else if(EnumExpectedDeliveryTime.Anytime.ordinal() == dbOrder.getExpectedDeliveryTime()){
            holder.relParent.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorWhite));
        }

        //Undelivered Dark Red
        //Morning skyblue
        //Afternoon pink
        //Evening orange
        //Anytime white
    }



    @Override
    public int getItemCount() {
        return listOrders.size();
    }

    public DbModelOrders getItem(int pos){

        if(pos<0 || pos>=listOrders.size()){
            return null;
        }
        return listOrders.get(pos);
    }
}
