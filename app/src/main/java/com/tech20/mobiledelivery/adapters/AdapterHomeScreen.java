package com.tech20.mobiledelivery.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.helpers.Constants;

import java.util.List;

public class AdapterHomeScreen extends RecyclerView.Adapter<AdapterHomeScreen.ViewHolderHomeItem> {

    public static class HomeContentItem{

        private String itemName = null;
        private int itemImageSource = 0;

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getItemImageSource() {
            return itemImageSource;
        }

        public void setItemImageSource(int itemImageSource) {
            this.itemImageSource = itemImageSource;
        }
    }

    public static class ViewHolderHomeItem extends RecyclerView.ViewHolder{

        private TextView txtTitle=null;
        private ImageView icon = null;
        private RelativeLayout relParent  =null;
        private View.OnClickListener onClickListener  = null;

        public ViewHolderHomeItem(View itemView,View.OnClickListener onClickListener) {
            super(itemView);
            txtTitle = (TextView)itemView.findViewById(R.id.txtDeliveries);
            icon = (ImageView)itemView.findViewById(R.id.imgIcon);
            relParent = (RelativeLayout)itemView.findViewById(R.id.relParent);
            relParent.setOnClickListener(onClickListener);
        }

    }

    private List<HomeContentItem> listItems = null;
    private View.OnClickListener onClickListener = null;

    public AdapterHomeScreen(List<HomeContentItem> listItems,View.OnClickListener onClickListener ) {
        this.listItems = listItems;
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolderHomeItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderHomeItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_screen_item,null),onClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolderHomeItem holder, int position) {
        HomeContentItem contentItem = listItems.get(position);

        holder.txtTitle.setText(contentItem.getItemName());
        holder.icon.setImageResource(contentItem.getItemImageSource());
        holder.relParent.setTag(position);

        Log.d(Constants.LogConstants.TAG_WASTE,"ContentItem:"+contentItem.getItemName());
    }

    @Override
    public int getItemCount() {

        return listItems == null?0:listItems.size();
    }

    public HomeContentItem getItem(int position){
        return listItems.get(position);
    }

}
