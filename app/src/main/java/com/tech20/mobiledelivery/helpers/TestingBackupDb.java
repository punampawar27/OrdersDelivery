package com.tech20.mobiledelivery.helpers;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Log;

import com.tech20.mobiledelivery.database.DataAccess;
import com.tech20.mobiledelivery.executors.AppExecutor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by fidel25 on 12/04/2017.
 */

public class TestingBackupDb {

    private TestingBackupDb(){

    }
    private static TestingBackupDb INSTANCE = null;

    public static TestingBackupDb getINSTANCE(){

        if(INSTANCE == null){

            INSTANCE = new TestingBackupDb();
        }

        return INSTANCE;
    }

    public void backupDb(Context context) {

        DataAccess.execute(new MyRunnable(context));
    }

    private static class MyRunnable implements Runnable{

        private Context context = null;
        public MyRunnable(Context context){
            this.context= context;
        }
        @Override
        public void run() {
            try {
                backup();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void backup() throws IOException {
            String databasePath = context.getDatabasePath(Constants.DatabaseConstants.DATABASE_NAME).getAbsolutePath();
            final String backupPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();

            File currentDb = new File(databasePath);
            File backupFilePath = new File(backupPath);

            if(!backupFilePath.exists()){
                backupFilePath.mkdirs();
            }


            final File backupDb = new File(backupFilePath.getAbsolutePath()+File.separator+Constants.DatabaseConstants.DATABASE_NAME);
            Log.d(Constants.LogConstants.TAG_WASTE,"currentDb.exists():"+currentDb.exists());
            if(currentDb.exists()){
                FileChannel src = new FileInputStream(currentDb).getChannel();
                FileChannel dst = new FileOutputStream(backupDb).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
            }

            backupDb.setExecutable(true);
            backupDb.setReadable(true);
            backupDb.setWritable(true);

            AppExecutor.getINSTANCE().getMainThread().execute(new Runnable() {
                @Override
                public void run() {
                    MediaScannerConnection.scanFile(context, new String[] {backupDb.toString()}, null, null);
                }
            });
            Log.d(Constants.LogConstants.TAG_WASTE,"BackupFinished");

        }
    }
}
