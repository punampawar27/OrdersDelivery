package com.tech20.mobiledelivery.helpers;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private float mItemOffset;

    public ItemOffsetDecoration(float itemOffset) {

        mItemOffset = itemOffset;

    }



    public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {

        this(context.getResources().getDimensionPixelSize(itemOffsetId));

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,

                               RecyclerView.State state) {

        super.getItemOffsets(outRect, view, parent, state);

        outRect.set((int)mItemOffset, (int)mItemOffset, (int)mItemOffset, (int)mItemOffset);

    }

}