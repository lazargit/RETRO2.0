package com.shamildev.retro.util;

import android.Manifest;

/**
 * Created by Shamil Lazar on 12.10.2018.
 */
public class Permissions {


    public static final String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA

    };

    public static final String[] WRITESTORAGE_PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE

    };
    public static final String[] READSTORAGE_PERMISSIONS = {

            Manifest.permission.READ_EXTERNAL_STORAGE,

    };
    public static final String[] CAMERA_PERMISSIONS = {

            Manifest.permission.CAMERA

    };

}
