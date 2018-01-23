package com.tech20.mobiledelivery.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.tech20.mobiledelivery.executors.AppExecutor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;


public class HelperSaveBitmap {

    private final int IMAGE_MAX_SIZE_IN_PIXELS = 500;
    public interface ISavedBitmap{

        void savedBitmap();
        void savedBitmapFailed(Exception exc);
    }

    private String filePath = null;
    private String fileName = null;
    private ISavedBitmap iSavedBitmap = null;
    private Uri imageUri = null;
    private Context context = null;


    public HelperSaveBitmap(ISavedBitmap iSavedBitmap,Uri imageUri,
                            @NonNull Context context,
                            @NonNull String filePath, @NonNull String fileName){
        this.iSavedBitmap = iSavedBitmap;
        this.filePath = filePath;
        this.fileName = fileName;
        this.imageUri = imageUri;
        this.context=context;
    }
    public void asynchronusSave(){

        AppExecutor.getINSTANCE().getDiskIO().execute(()->{
            File file = new File(filePath, fileName);
            if (file.exists()) file.delete();
            try {

                InputStream imageStream = context.getContentResolver().openInputStream(imageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream,null,getOptions(imageUri));

                FileOutputStream out = new FileOutputStream(file);
                selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, out);

                out.flush();
                out.close();

                if(iSavedBitmap!=null)
                iSavedBitmap.savedBitmap();
            } catch (Exception e) {
                e.printStackTrace();

                if(iSavedBitmap!=null)
                iSavedBitmap.savedBitmapFailed(e);
            }
        });

    }

    private BitmapFactory.Options getOptions(Uri imageUri) throws FileNotFoundException {

        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;

        BitmapFactory.decodeStream(context.getContentResolver().openInputStream(imageUri), null, o);

        int scale = 1;
        if (o.outHeight > IMAGE_MAX_SIZE_IN_PIXELS || o.outWidth > IMAGE_MAX_SIZE_IN_PIXELS) {
            scale = (int)Math.pow(2, (int) Math.ceil(Math.log(IMAGE_MAX_SIZE_IN_PIXELS /
                    (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
        }

        //Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;

        return o2;

    }


}
