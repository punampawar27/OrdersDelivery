package com.tech20.mobiledelivery.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.database.dataentities.DbModelCustomers;

import java.util.List;


public class AdapterCustomers extends RecyclerView.Adapter<AdapterCustomers.ViewHolderCustomer> {

    public static class ViewHolderCustomer extends RecyclerView.ViewHolder{

        private TextView txtCustomerName = null;
        private TextView txtCustomerAddress = null;
        private ImageView imgViewCall = null;
        private RelativeLayout relParent = null;



        public ViewHolderCustomer(View itemView, View.OnClickListener onClick) {
            super(itemView);

            txtCustomerName = (TextView)itemView.findViewById(R.id.txtName);
            txtCustomerAddress = (TextView)itemView.findViewById(R.id.txtAddress);
            imgViewCall = (ImageView) itemView.findViewById(R.id.imgCall);
            relParent = itemView.findViewById(R.id.relParent);

            imgViewCall.setOnClickListener(onClick);
        }
    }

    private View.OnClickListener onClickListener = null;
    private List<DbModelCustomers> list = null;
    private int colorAliceBlue = 0;
    private int colorWhite = 0;

    public AdapterCustomers(List<DbModelCustomers> list,View.OnClickListener onClickListener,
                            int colorGrey,int colorWhite){
        this.onClickListener=onClickListener;
        this.list = list;
        this.colorAliceBlue = colorGrey;
        this.colorWhite = colorWhite;
    }
    @Override
    public ViewHolderCustomer onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolderCustomer(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_customer,parent,false),
                                        onClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolderCustomer holder, int position) {

        if(position%2==0){
            holder.relParent.setBackgroundColor(colorWhite);
        }else{
            holder.relParent.setBackgroundColor(colorAliceBlue);
        }
        holder.txtCustomerName.setText(list.get(position).getCustomerName());
        holder.txtCustomerAddress.setText(list.get(position).getShippingAddress());
        holder.imgViewCall.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public DbModelCustomers getItem(int pos){
        return list.get(pos);
    }
}
