package com.tech20.mobiledelivery.utils;


        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.nio.charset.Charset;

public class IOUtils {

    public static String readFromFile(String filePath) throws IOException{

        FileInputStream fis = new FileInputStream(filePath);

        StringBuilder strBuilder = new StringBuilder();
        byte[] bytes = new byte[1024];
        int count;

        while ((count = fis.read(bytes, 0, bytes.length)) > -1) {
            strBuilder.append(new String(bytes, 0, count, Charset
                    .forName("UTF-8")));
        }
        fis.close();

        return strBuilder.toString();
    }

    public static void writeToFile(String info,String filePath,String fileName){

        FileOutputStream fos;
        try {
            File f = new File(filePath);
            if(!f.exists()){
                f.mkdirs();
            }
            File file = new File(filePath+File.separator+fileName);
            if(!file.exists()){
                file.createNewFile();
            }

            fos = new FileOutputStream(file,true);
            fos.write(info.trim()
                    .getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}