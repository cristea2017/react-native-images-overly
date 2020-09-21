package com.imagesoverly;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Base64;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import com.imagesoverly.ImagesOverlyModule;

import java.io.ByteArrayOutputStream;

public class ImagesOverlyModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public ImagesOverlyModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ImagesOverly";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }
      @ReactMethod
     public void overlyImages(String image1, String image2, int posX, int posY, Callback callback) {
         try {
             byte[] decodedString = Base64.decode(image1, Base64.DEFAULT);
             Bitmap bg = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
          
             byte[] decodedString1 = Base64.decode(image2, Base64.DEFAULT);
             Bitmap overly = BitmapFactory.decodeByteArray(decodedString1, 0, decodedString1.length);
          
            Bitmap bmOverlay = Bitmap.createBitmap(bg.getWidth(), bg.getHeight(), bg.getConfig());
            Canvas canvas = new Canvas(bmOverlay);
            canvas.drawBitmap(bg, new Matrix(), null);
            canvas.drawBitmap(overly, posX,posY, null);
        
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
          bmOverlay.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
          byte[] byteArray = byteArrayOutputStream .toByteArray();
    

         callback.invoke(Base64.encodeToString(byteArray, Base64.DEFAULT));
        } catch (Exception e) {
         callback.invoke("errr: ");
        }
    }
}
