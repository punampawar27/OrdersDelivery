package com.tech20.mobiledelivery.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.contracts.ContractLogin;
import com.tech20.mobiledelivery.database.DatabaseHouse;
import com.tech20.mobiledelivery.helpers.AndroidVersionUtil;
import com.tech20.mobiledelivery.helpers.Constants;
import com.tech20.mobiledelivery.helpers.NetworkHelper;
import com.tech20.mobiledelivery.helpers.PreferenceUtils;
import com.tech20.mobiledelivery.helpers.ToastUtils;
import com.tech20.mobiledelivery.models.Entities.EntityLogin;
import com.tech20.mobiledelivery.presenter.PresenterLogin;
import com.tech20.mobiledelivery.retrofitclient.ErrorMessage;
import com.tech20.mobiledelivery.utils.NotificationBuilder;
import com.tech20.mobiledelivery.utils.UtilNotification;

//storeId 6630
//user pankz
//pass pankz
public class ActivityLogin extends BaseActivity implements ContractLogin.IViewLogin,OnClickListener,View.OnFocusChangeListener{



    private final String STORE_ID= "5141",USERNAME= "nick",PASS= "1234",ADMIN_NO="12345678";
    private final String ERROR_MESSAGE_FALSE= "false";

    private  ContractLogin.IPresenterLogin iPresenterLogin =null;
    private EditText edtStoreId = null,edtUseName = null,edtPass = null;

    private String userNameError = null,
            passwordError = null,
            storeIdError = null;
    private TextView textViewVersionName = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        isFetchLocation(false);
        initView();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destory();
    }

    private void initView(){
        edtStoreId = (EditText)findViewById(R.id.edt_storeId);
        edtUseName = (EditText)findViewById(R.id.edt_username);
        edtPass = (EditText)findViewById(R.id.edt_password);
        textViewVersionName = (TextView) findViewById(R.id.textViewVersionName);

        ((Button)findViewById(R.id.btn_authenticate)).setOnClickListener(this);
        findViewById(R.id.tv_help).setOnClickListener(this);
        edtStoreId.setOnFocusChangeListener(this);
        edtUseName.setOnFocusChangeListener(this);
        edtPass.setOnFocusChangeListener(this);

    }
    private void init(){
        iPresenterLogin = new PresenterLogin();
        iPresenterLogin.attach(this);

                userNameError = getString(R.string.please_enter_username);
                passwordError = getString(R.string.please_enter_password);
                storeIdError = getString(R.string.please_enter_storeid);
                textViewVersionName.setText(AndroidVersionUtil.getVersionName(this));

        String uniqueIdentification = PreferenceUtils.getINSTANCE(getApplicationContext()).getString(Constants.PreferenceConstants.UNIQUE_INSTALLATION_ID);
        if(TextUtils.isEmpty(uniqueIdentification)){
            uniqueIdentification = AndroidVersionUtil.getUniqueDeviceIdentification(this);
            PreferenceUtils.getINSTANCE(getApplicationContext()).putString(Constants.PreferenceConstants.UNIQUE_INSTALLATION_ID, uniqueIdentification);
        }

    }

    private void destory(){
        iPresenterLogin.dettach();
    }

    private void login(boolean isForceUpdate){



        String strStoreId = edtStoreId.getEditableText().toString();
        String strUserName= edtUseName.getEditableText().toString();
        String strPass = edtPass.getEditableText().toString();


        if(validateUI(strStoreId,strUserName,strPass)){
            if(NetworkHelper.isOnline(this)){

                String firebaseRegKey = PreferenceUtils.getINSTANCE(getApplicationContext()).getString(Constants.PreferenceConstants.FIREBASE_TOKEN);

                //Old project- sessionId=deviceId, deviceId=firebaseRegId/token, regId=firebaseRegId (redandunt)
                EntityLogin login = new EntityLogin();
                login.setFireBaseRegKey(firebaseRegKey);
                login.setStoreId(strStoreId);
                login.setUsername(strUserName);
                login.setPass(strPass);
                login.setDeviceId(PreferenceUtils.getINSTANCE(getApplicationContext()).getString(Constants.PreferenceConstants.UNIQUE_INSTALLATION_ID));
                login.setIsSessionUpdated(isForceUpdate);


                iPresenterLogin.login(login,
                        DatabaseHouse.getSingleTon(getApplicationContext()),
                        PreferenceUtils.getINSTANCE(getApplicationContext()));

            }else{
                NetworkHelper.noNetworkToast(getApplicationContext());
            }
        }


    }

    @Override
    public void loginSuccess() {
        Intent intentActivityCurrentStatus = new Intent(ActivityLogin.this,ActivityCurrentStatus.class);
        startActivity(intentActivityCurrentStatus);
        finish();
    }

    @Override
    public void loginFailed(ErrorMessage errorMessage) {

        //AppServer responds with Message:false, it indicates that other user has logged in with same username on different device.
        //We have to show dialog to user to force log in
        if(ERROR_MESSAGE_FALSE.equalsIgnoreCase(errorMessage.getMessage())){
            forceLoginDialog();
        }else{
            ToastUtils.showSnackBar(findViewById(android.R.id.content),errorMessage.getMessage());
        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_authenticate:

                login(false);

                break;
            case R.id.tv_help:
                callStore();
                break;
        }
    }




    private boolean validateUI(String storeId,String username,String password) {

        if(!vaildateStoreId(storeId) ||
                !vaildateUserName(username) ||
                !vaildatePassword(password)){
         return false;
        }

        return true;
    }

    private boolean vaildateStoreId(String storeId){
        if("".equalsIgnoreCase(storeId)){
            edtStoreId.setError(storeIdError);
            return false;
        }else{
            edtStoreId.setError(null);
        }
        return true;
    }

    private boolean vaildateUserName(String userName){
        if("".equalsIgnoreCase(userName)){
            edtUseName.setError(userNameError);
            return false;
        }else{
            edtUseName.setError(null);
        }
        return true;
    }

    private boolean vaildatePassword(String password){
        if("".equalsIgnoreCase(password)){
            edtPass.setError(passwordError);
            return false;
        }else{
            edtPass.setError(null);
        }
        return true;
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        switch (v.getId()){
            case R.id.edt_storeId:
                if(!hasFocus){
                    vaildateStoreId(edtStoreId.getText().toString());
                }else{
                    edtStoreId.setError(null);
                }
                break;
            case R.id.edt_username:
                if(!hasFocus){
                    vaildateUserName(edtUseName.getText().toString());
                }else {
                    edtUseName.setError(null);
                }

                break;
            case R.id.edt_password:
                if(!hasFocus){
                    vaildatePassword(edtPass.getText().toString());
                }else{
                    edtPass.setError(null);
                }
                break;
        }
    }

    private void callStore() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent intent = new Intent(Intent.ACTION_DIAL,
                                Uri.parse("tel:" + ADMIN_NO));
                        startActivity(intent);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };
        ContextThemeWrapper cw = new ContextThemeWrapper(this, R.style.AlertDialogTheme);
        final AlertDialog.Builder builder = new AlertDialog.Builder(cw);
        builder.setTitle(Html.fromHtml("<b>" + getString(R.string.alert_call_support) + "</b>"))
                .setMessage(getString(R.string.call_support))
                .setPositiveButton(getString(R.string.yes), dialogClickListener)
                .setNegativeButton(getString(R.string.no), dialogClickListener);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        TextView tvMessage = (TextView) alertDialog.findViewById(android.R.id.message);
        tvMessage.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_medium));
    }

    private void forceLoginDialog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        login(true);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };
        ContextThemeWrapper cw = new ContextThemeWrapper(this, R.style.AlertDialogTheme);
        final AlertDialog.Builder builder = new AlertDialog.Builder(cw);
        builder.setTitle(Html.fromHtml("<b>" + getString(R.string.alert_update_session_id) + "</b>"))
                .setMessage(getString(R.string.logout_other_devices))
                .setPositiveButton(getString(R.string.yes), dialogClickListener)
                .setNegativeButton(getString(R.string.no), dialogClickListener);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        TextView tvMessage = (TextView) alertDialog.findViewById(android.R.id.message);
        tvMessage.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_medium));
    }

}
