package com.tech20.mobiledelivery.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.database.dataentities.DbModelCashDrawer;
import com.tech20.mobiledelivery.database.dataentities.DbModelCustomers;
import com.tech20.mobiledelivery.helpers.UtilDateFormat;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class AdapterCashDrawer extends RecyclerView.Adapter<AdapterCashDrawer.ViewHolderCashDrawer> {


    public static class ViewHolderCashDrawer extends RecyclerView.ViewHolder{

        private TextView txtValueOrderId = null,txtValueDeliveryDate = null,txtValueTotal = null;
        private RelativeLayout relParent = null;

        public ViewHolderCashDrawer(View itemView) {
            super(itemView);

            txtValueOrderId = itemView.findViewById(R.id.txtValueOrderId);
            txtValueDeliveryDate = itemView.findViewById(R.id.txtValueDeliveryDate);
            txtValueTotal = itemView.findViewById(R.id.txtValueTotal);
            relParent = itemView.findViewById(R.id.relParent);
        }
    }


    private List<DbModelCashDrawer> listCashDrawer = null;
    private int colorAliceBlue = 0;
    private int colorWhite = 0;


    public AdapterCashDrawer(List<DbModelCashDrawer> listCashDrawer,
                             int colorAliceBlue, int colorWhite){
        this.listCashDrawer = listCashDrawer;
        this.colorAliceBlue=colorAliceBlue;
        this.colorWhite = colorWhite;

    }

    @Override
    public ViewHolderCashDrawer onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderCashDrawer(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_cashdrawer,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolderCashDrawer holder, int position) {

        if(position%2==0){
            holder.relParent.setBackgroundColor(colorWhite);
        }else{
            holder.relParent.setBackgroundColor(colorAliceBlue);
        }

        DbModelCashDrawer drawerCash = listCashDrawer.get(position);

        holder.txtValueOrderId.setText(drawerCash.getOrderId());
        try {
            holder.txtValueDeliveryDate.setText(UtilDateFormat.format(UtilDateFormat.yyyy_MM_dd_T_HH_mm_ss,
                                                                        UtilDateFormat.DD_MMM_YY,
                    drawerCash.getDeliveryDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.txtValueTotal.setText(drawerCash.getCash());

    }

    @Override
    public int getItemCount() {
        return listCashDrawer.size();
    }


}
