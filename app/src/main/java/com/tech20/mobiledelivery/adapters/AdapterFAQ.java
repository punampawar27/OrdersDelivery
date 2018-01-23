package com.tech20.mobiledelivery.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.database.dataentities.DbModelDriver;
import com.tech20.mobiledelivery.database.dataentities.DbModelFaq;

import java.util.List;


public class AdapterFAQ extends RecyclerView.Adapter<AdapterFAQ.ViewHolderFAQ> {


    public static class ViewHolderFAQ extends RecyclerView.ViewHolder{

        private TextView txtQuestion = null;
        private TextView txtAnswer = null;

        public ViewHolderFAQ(View itemView) {
            super(itemView);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            txtAnswer = itemView.findViewById(R.id.txtAnswer);
        }
    }

    private List<DbModelFaq> listFaq = null;

    public AdapterFAQ(List<DbModelFaq> listFaq) {
        this.listFaq = listFaq;
    }

    @Override
    public ViewHolderFAQ onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderFAQ(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_faq,null));
    }

    @Override
    public void onBindViewHolder(ViewHolderFAQ holder, int position) {

        DbModelFaq faq = listFaq.get(position);

        holder.txtQuestion.setText(faq.getQuestion());
        holder.txtAnswer.setText(faq.getAnswer());
    }


    @Override
    public int getItemCount() {
        return listFaq.size();
    }

}
