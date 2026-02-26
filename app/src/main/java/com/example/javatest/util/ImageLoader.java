package com.example.javatest.util;

import android.net.Uri;
import android.widget.ImageView;

import com.example.javatest.R;

import java.io.File;

public class ImageLoader {

    public static void load(ImageView img, String value){

        if(value == null || value.isEmpty()){
            img.setImageResource(R.drawable.cf_den);
            return;
        }

        try{
            // ===== FILE PATH =====
            if(value.startsWith("/") || value.startsWith("file://")){
                File f = new File(value.replace("file://",""));
                if(f.exists()){
                    img.setImageURI(Uri.fromFile(f));
                    return;
                }
            }

            // ===== CONTENT URI =====
            if(value.startsWith("content://")){
                img.setImageURI(Uri.parse(value));
                return;
            }

            // ===== DRAWABLE NAME =====
            int resId = img.getContext().getResources()
                    .getIdentifier(value,"drawable",
                            img.getContext().getPackageName());

            if(resId != 0){
                img.setImageResource(resId);
            }else{
                img.setImageResource(R.drawable.cf_den);
            }

        }catch(Exception e){
            img.setImageResource(R.drawable.cf_den);
        }
    }
}