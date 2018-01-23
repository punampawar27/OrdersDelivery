package com.tech20.mobiledelivery.dialogs;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.tech20.mobiledelivery.R;
import com.tech20.mobiledelivery.helpers.ToastUtils;

public class DialogNumberScroller extends DialogFragment{

    public static final String EXTRA_NUMBER = "EXTRA_NUMBER";
    public static final String EXTRA_MAX = "EXTRA_MAX";
    public static final String EXTRA_POSITION = "EXTRA_POSITION";

    public interface ISeekNumberSelected{
        void seekNumberSelected(int number,int position,Dialog dialog);
    }

    private ISeekNumberSelected iSeekNumberSelected = null;
    private SeekBar seekBarNumber = null;
    private EditText edtNumber = null;
    private Button btnSeekDone = null;
    private ImageView imgClose = null;

    private int progress = 1;
    private int max=0;
    private int positionItem = -1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        iSeekNumberSelected = (ISeekNumberSelected)context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_number_seeker,null);

        readBundle();
        seekBarNumber =  view.findViewById(R.id.seekBarNumber);
        edtNumber = view.findViewById(R.id.edtNumber);
        btnSeekDone = view.findViewById(R.id.btnSeekDone);
        imgClose = view.findViewById(R.id.imgClose);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable( new ColorDrawable(android.graphics.Color.TRANSPARENT));

        btnSeekDone.setOnClickListener(onClick);
        imgClose.setOnClickListener(onClickClose);
        seekBarNumber.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekBarNumber.setMax(max);
        setProgress(progress);
    }

    private void readBundle(){
        progress = getArguments().getInt(EXTRA_NUMBER,1);
        max= getArguments().getInt(EXTRA_MAX,1);
        positionItem = getArguments().getInt(EXTRA_POSITION,-1);
    }
    private void setProgress(int progress){
        seekBarNumber.setProgress(progress);
    }

    private void setEditText(int progress){
        edtNumber.setText(String.valueOf(progress));
    }
    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            setEditText(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private View.OnClickListener onClick = (v)->{

        int number = Integer.parseInt(edtNumber.getEditableText().toString());
        if(validate(number)){
            iSeekNumberSelected.seekNumberSelected(number,positionItem,getDialog());
        }
    };

    private boolean validate(int value){

        if(value<0){

            ToastUtils.showSnackBar(getActivity().findViewById(android.R.id.content),"Quantity cannot be 0 or less");
            return false;
        }else if(value>max){
            ToastUtils.showSnackBar(getActivity().findViewById(android.R.id.content),"Quantity is more than available quantity");
            return false;
        }

        return true;
    }

    private View.OnClickListener onClickClose = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };
}
