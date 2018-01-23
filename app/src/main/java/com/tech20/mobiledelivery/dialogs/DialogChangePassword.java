package com.tech20.mobiledelivery.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.contracts.ContractDialogChangePassword;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.ToastUtils;
import com.tech20.mobiledelivery.presenter.PresenterDialogChangePassword;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;


public class DialogChangePassword extends DialogFragment implements ContractDialogChangePassword.IViewChangePassword {

    public static final String EXTRA_DRIVER_ID="EXTRA_DRIVER_ID";

    private EditText edtOldpassword = null, edtNewpassword =null, edtNewcompassword =null;
    private ContractDialogChangePassword.IPresenterChangePassword iPresenterChangePassword = null;
    private IDialogChangePasswordCallback iViewChangePassword = null;
    private String driverId = null;
    private InputMethodManager inputMethodManager = null;
    public interface IDialogChangePasswordCallback{
        void onDialogChangePasswordSuccess();
        void onDialogChangePasswordFail(ErrorMessage errorMessage);
        void toggleProgressIndigator(boolean show);
        void toggleProgressIndigator(boolean show,String title,String message);
    }

    public static DialogChangePassword getInstance(String driverId){
        Bundle bundle = new Bundle();
        bundle.putString(DialogChangePassword.EXTRA_DRIVER_ID,driverId);

        DialogChangePassword dialogChangePassword = new DialogChangePassword();
        dialogChangePassword.setArguments(bundle);
        return dialogChangePassword;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iViewChangePassword = (IDialogChangePasswordCallback)context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iPresenterChangePassword = new PresenterDialogChangePassword();
        iPresenterChangePassword.attach(this);
        driverId = getArguments().getString(EXTRA_DRIVER_ID);

        if(TextUtils.isEmpty(driverId)){
            throw new Error("Driver Id cannot be empty");
        }

        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        iPresenterChangePassword.dettach();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        inputMethodManager = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.dialog_change_password, null);


        edtOldpassword = (EditText) view.findViewById(R.id.ETpasswordold);
        edtNewpassword = (EditText) view.findViewById(R.id.ETpasswordNew);
        edtNewcompassword = (EditText) view.findViewById(R.id.ETpasswordConNew);
        view.findViewById(R.id.TVsubmit_password).setOnClickListener(onClick);
        view.findViewById(R.id.discard).setOnClickListener(onClick);


        return view;
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(v.getId() == R.id.discard){
                inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                dismiss();
            }else if(v.getId() == R.id.TVsubmit_password){

                String oldPass = edtOldpassword.getEditableText().toString();
                String newPass = edtNewpassword.getEditableText().toString();
                String confPass = edtNewcompassword.getEditableText().toString();

                if(TextUtils.isEmpty(oldPass) || TextUtils.isEmpty(newPass) || TextUtils.isEmpty(confPass)){
                    ToastUtils.showSnackBar(getView(),getString(R.string.password_cannot_emtpy));
                    return;
                }else if(!confPass.equals(newPass)){
                    ToastUtils.showSnackBar(getView(),getString(R.string.password_new_conf_same));
                    return;
                }

                inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                iPresenterChangePassword.onChangePassword(driverId,oldPass,newPass, PreferenceUtils.getINSTANCE(getActivity()),
                        null,getString(R.string.prg_change_pass));
            }
        }
    };

    @Override
    public void toggleProgressIndigator(boolean show) {
        iViewChangePassword.toggleProgressIndigator(show);
    }

    @Override
    public void toggleProgressIndigator(boolean show, String title, String message) {
        iViewChangePassword.toggleProgressIndigator(show,title,message);
    }

    @Override
    public void onChangePasswordSuccess() {

        dismiss();
        iViewChangePassword.onDialogChangePasswordSuccess();
    }

    @Override
    public void onChangePasswordFail(ErrorMessage errorMessage) {
        ToastUtils.showSnackBar(getView(),errorMessage.getMessage());
        iViewChangePassword.onDialogChangePasswordFail(errorMessage);
    }

}
