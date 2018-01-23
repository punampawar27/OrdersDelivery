package com.tech20.mobiledelivery.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.tech20.mobiledelivery.enums.EnumDialogType;

//Reusable Dialog
public class DialogAlert extends DialogFragment {

    public static final String EXTRA_TITLE="EXTRA_TITLE";
    public static final String EXTRA_MESSAGE="EXTRA_MESSAGE";
    public static final String EXTRA_POSITIVE_TEXT="EXTRA_POSITIVE_TEXT";
    public static final String EXTRA_NEGATIVE_TEXT="EXTRA_NEGATIVE_TEXT";
    public static final String EXTRA_TYPE="EXTRA_TYPE";

    private IDialogLogoutCallback callBack = null;
    public interface IDialogLogoutCallback{
        void onDialogAlertButtonPositive(EnumDialogType enumType);
        void onDialogAlertButtonNegative(EnumDialogType enumType);
    }

    private String title = null;
    private String message = null;
    private String positiveButtonText = null;
    private String negativeButtonText = null;
    private EnumDialogType enumType = null;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBack = (IDialogLogoutCallback)context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle.getInt(EXTRA_TYPE,Integer.MIN_VALUE) == 0){
            throw new Error("Dialog type cannot be 0, use "+EnumDialogType.class.getName());
        }

        title = bundle.getString(EXTRA_TITLE,"no title");
        message = bundle.getString(EXTRA_MESSAGE,"no message");
        positiveButtonText = bundle.getString(EXTRA_POSITIVE_TEXT,"");
        negativeButtonText = bundle.getString(EXTRA_NEGATIVE_TEXT,"");
        enumType = EnumDialogType.getEnum(bundle.getInt(EXTRA_TYPE,Integer.MIN_VALUE));

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setMessage(message);
        if(!TextUtils.isEmpty(positiveButtonText)){
            builder.setPositiveButton(positiveButtonText, onDialogButtonClick);
        }

        if(!TextUtils.isEmpty(negativeButtonText)){
            builder.setNegativeButton(negativeButtonText, onDialogButtonClick);
        }


        AlertDialog alertDialog = builder.create();

        return alertDialog;
    }

    AlertDialog.OnClickListener onDialogButtonClick = (DialogInterface dialog, int which)->{
        if(which == AlertDialog.BUTTON_POSITIVE){
            if(callBack!=null)
                callBack.onDialogAlertButtonPositive(enumType);

        }else if(which == AlertDialog.BUTTON_NEGATIVE){

            if(callBack!=null)
                callBack.onDialogAlertButtonNegative(enumType);
        }
    };

}
