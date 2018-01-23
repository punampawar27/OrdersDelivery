package com.tech20.mobiledelivery.adapters;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.retrofitclient.inventoryclient.InventoryResponse;

import java.util.List;

public class AdapterInventory extends RecyclerView.Adapter<AdapterInventory.ViewHolderInventory>{

    public static class ViewHolderInventory extends RecyclerView.ViewHolder{

        private TextView txtRowInventoryProduct = null, txtRowInventoryUnits = null,
                txtRowInventoryQuantity = null,
                txtRowInventoryStatus = null;
        private LinearLayout linParent = null;

        public ViewHolderInventory(View itemView) {
            super(itemView);

            txtRowInventoryProduct = itemView.findViewById(R.id.tv_rowInventory_product);
            txtRowInventoryUnits = itemView.findViewById(R.id.tv_rowInventory_units);
            txtRowInventoryQuantity = itemView.findViewById(R.id.tv_rowInventory_quantity);
            txtRowInventoryStatus = itemView.findViewById(R.id.tv_rowInventory_status);
            linParent = itemView.findViewById(R.id.linParent);
        }
    }

    private int colorWhite =0;
    private int colorblue =0;
    private List<InventoryResponse> listInventory = null;
    public AdapterInventory(List<InventoryResponse> listInventory,int colorWhite,int colorBlue){
        this.listInventory = listInventory;
        this.colorWhite=colorWhite;
        this.colorblue = colorBlue;
    }
    @Override
    public ViewHolderInventory onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderInventory(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_inventory,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolderInventory holder, int position) {

        if (holder.getAdapterPosition() % 2 == 0) {

            holder.linParent.setBackgroundColor(colorWhite);
        } else {
            holder.linParent.setBackgroundColor(colorblue);
        }

        InventoryResponse inventoryResponse = listInventory.get(position);

        holder.txtRowInventoryProduct.setText(inventoryResponse.getItemName());
        holder.txtRowInventoryQuantity.setText(inventoryResponse.getQuantity());
        holder.txtRowInventoryUnits.setText(inventoryResponse.getPackageSize());
        holder.txtRowInventoryStatus.setText(inventoryResponse.getStatus());

    }

    @Override
    public int getItemCount() {
        return listInventory.size();
    }


}
