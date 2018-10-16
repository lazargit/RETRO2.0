package com.shamildev.retro.retroimage.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Shamil Lazar on 23.09.2018.
 */
public class BitmapConverter {
    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static String drawableToString(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return BitmapConverter.BitMapToString(bitmapDrawable.getBitmap());
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return BitmapConverter.BitMapToString(bitmap);
    }


    static public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth =(int) 220;//mContext.getResources().getDimension(R.dimen.prof_pic_diameter);
        int targetHeight =(int) 220;//mContext.getResources().getDimension(R.dimen.prof_pic_diameter);

        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG) {
            {
                setDither(true);
                setAntiAlias(true);
                setFilterBitmap(true);
            }
        };

        Path path = new Path();
        path.reset();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);

        BitmapDrawable bitmapDrawable = new BitmapDrawable(scaleBitmapImage);
        bitmapDrawable.setDither(true);
        bitmapDrawable.setAntiAlias(true);
        scaleBitmapImage = bitmapDrawable.getBitmap();

        canvas.drawBitmap(scaleBitmapImage,
                new Rect(0, 0, scaleBitmapImage.getWidth(),
                        scaleBitmapImage.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), paint);

        return targetBitmap;
    }

    static public byte[] DrawableToByteArray(Drawable drawable){


        Bitmap bitmap1 = ((BitmapDrawable)drawable).getBitmap();



        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        return data;

    }

    static public Bitmap ByteArrayToBitmap(byte[] byteArray){

        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);

    }

    public static String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    /**
     * @param encodedString
     * @return bitmap (from given string)
     */
    public static Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

   static public Bitmap applySmoothEffect(Bitmap src, double value) {
        //create convolution matrix instance
        ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
        convMatrix.setAll(1);
        convMatrix.Matrix[1][1] = value;
        // set weight of factor and offset
        convMatrix.Factor = value + 8;
        convMatrix.Offset = 1;
        return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
    }

    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,70, baos);
        byte [] b=baos.toByteArray();
        return b;
    }
}
