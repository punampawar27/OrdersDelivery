package com.tech20.mobiledelivery.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.retrofitclient.extrainventoryclient.ExtraInventoryResponse;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterExtraInventory extends RecyclerView.Adapter<AdapterExtraInventory.InventoryViewHolder>{



    public static class InventoryViewHolder extends RecyclerView.ViewHolder{

        private TextView txtProductName = null;
        private TextView txtValuePackageSize = null;
        private TextView txtValueAvailableQuantity = null;
        private TextView txtValuePrice = null;
        private TextView txtAdd = null;
        private TextView txtValueAvailedQty = null;
        private RelativeLayout relRight = null;
        private RelativeLayout relParent = null;

        public InventoryViewHolder(View itemView, View.OnClickListener onClick) {
            super(itemView);

            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtValuePackageSize = itemView.findViewById( R.id.txtValuePackageSize);
            txtValueAvailableQuantity = itemView.findViewById(R.id.txtValueAvailableQuantity);
            txtValuePrice = itemView.findViewById(R.id.txtValuePrice);
            txtAdd = itemView.findViewById(R.id.txtAdd);
            txtValueAvailedQty = itemView.findViewById(R.id.txtValueAvailedQty);
            relRight = itemView.findViewById(R.id.relRight);
            relParent = itemView.findViewById(R.id.relParent);

            txtAdd.setOnClickListener(onClick);
        }

    }

    private Context context = null;
    private DecimalFormat decimalFormat = null;
    private List<ExtraInventoryResponse> listRespponse = null;
    private String strCurrencySign = null,strAvail = null;
    private View.OnClickListener onClick = null;

    private int bgBlue = 0;
    private int bgWhite = 0;

    public AdapterExtraInventory(int bgBlue,
                                 int bgWhite,
                                 Context context, View.OnClickListener onClick, List<ExtraInventoryResponse> listRespponse){
        this.listRespponse = listRespponse;
        decimalFormat = new DecimalFormat("##.00");
        strCurrencySign = context.getString(R.string.currency_sign);
        strAvail = context.getString(R.string.available);
        this.onClick = onClick;
        this.bgBlue = bgBlue;
        this.bgWhite =bgWhite;
        this.context = context;
    }
    @Override
    public InventoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InventoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_extra_inventory,parent,false),onClick);
    }

    @Override
    public void onBindViewHolder(InventoryViewHolder holder, int position) {

        ExtraInventoryResponse item = listRespponse.get(position);
        holder.txtProductName.setText(item.getItemName());
        holder.txtValueAvailableQuantity.setText(item.getQuantity());
        holder.txtValuePrice.setText(strCurrencySign+decimalFormat.format(Double.parseDouble(item.getItemPrice())));
        holder.txtValuePackageSize.setText(item.getPackageSize());
        holder.txtAdd.setTag(position);
        if(item.getSelectedQuantity()>0){

            holder.relParent.setBackgroundResource(bgBlue);
            holder.relRight.setVisibility(View.VISIBLE);
            holder.txtValueAvailedQty.setText(String.valueOf(item.getSelectedQuantity()));
            holder.txtAdd.setText(context.getString(R.string.change));
        }else{
            holder.txtAdd.setText(context.getString(R.string.add));
            holder.relParent.setBackgroundResource(bgWhite);
            holder.relRight.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return listRespponse.size();
    }

    public ExtraInventoryResponse getItem(int position){
        return listRespponse.get(position);
    }
    public List<ExtraInventoryResponse> getList(){
        return listRespponse;
    }

    public void setList(List<ExtraInventoryResponse> listExtraInventory){
        this.listRespponse = listExtraInventory;
        notifyDataSetChanged();
    }
}
