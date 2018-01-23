package com.tech20.mobiledelivery.helpers;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by fidel25 on 12/05/2017.
 */

public class ToastUtils {

    private static Toast toast = null;


    public static void showSnackBar(View view,String message){

        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).setActionTextColor(Color.WHITE).show();


    }


    public static void showToast(Context context,String str) {

        if (toast == null)
            toast = Toast.makeText(
                    context, str,
                    Toast.LENGTH_SHORT);

        toast.setText(str);
        toast.show();
    }
}
