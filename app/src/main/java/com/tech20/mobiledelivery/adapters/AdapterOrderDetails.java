package com.tech20.mobiledelivery.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.database.dataentities.DbModelOrderInventory;
import com.tech20.mobiledelivery.models.Entities.EntitySelectedOrderInventory;

import java.util.List;

public class AdapterOrderDetails extends RecyclerView.Adapter<AdapterOrderDetails.ViewHolderOrderDetail> {

    public static class ViewHolderOrderDetail extends RecyclerView.ViewHolder{

        private TextView txtTitle = null;
        private TextView txtItemQty = null;
        private TextView txtPackageSize = null;
        private TextView txtPrice = null;
        private RelativeLayout relParent = null;

        public ViewHolderOrderDetail(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.tv_item_title);
            txtItemQty = itemView.findViewById(R.id.tv_item_qty);
            txtPackageSize = itemView.findViewById(R.id.tv_item_package_size);
            txtPrice = itemView.findViewById(R.id.tv_item_price);
            relParent = itemView.findViewById(R.id.relParent);

        }
    }

    private List<EntitySelectedOrderInventory> listOrderItems = null;
    private String strCurrencySign = null;
    private int bgWhiteBorder,bgBlueBorder;

    public AdapterOrderDetails(List<EntitySelectedOrderInventory> listOrderItems, String strCurrencySign,
                               int bgWhiteBorder,int bgBlueBorder){
        this.listOrderItems = listOrderItems;
        this.strCurrencySign = strCurrencySign;
        this.bgWhiteBorder=bgWhiteBorder;
        this.bgBlueBorder=bgBlueBorder;

    }
    @Override
    public ViewHolderOrderDetail onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderOrderDetail(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_order_detail2,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolderOrderDetail holder, int position) {

        EntitySelectedOrderInventory model = listOrderItems.get(position);

        holder.txtTitle.setText(model.getItemName());
        holder.txtItemQty.setText(model.getQuantity());
        holder.txtPackageSize.setText(model.getPackageSize());
        holder.txtPrice.setText(strCurrencySign+model.getPrice());

        if(model.getITEM_TYPE() == EntitySelectedOrderInventory.OrderItemType.ORDERED_INVENTORY.ordinal()){
            holder.relParent.setBackgroundResource(bgWhiteBorder);
        }else{
            holder.relParent.setBackgroundResource(bgBlueBorder);
        }

    }

    @Override
    public int getItemCount() {
        return listOrderItems.size();
    }

    public List<EntitySelectedOrderInventory> getList(){
        return listOrderItems;
    }
}
