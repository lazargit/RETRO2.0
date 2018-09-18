package com.shamildev.retro.cache.local.util;



import com.google.gson.JsonElement;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;


/**
 * Created by Shamil Lazar on 16.08.2018.
 */

public class Util {

    @NonNull
    public static ArrayList<String> jsonToList(JsonElement element) {
        ArrayList<String> listdata = new ArrayList<String>();

        if (element.getAsJsonArray() != null) {
            for (int i = 0; i < element.getAsJsonArray().size(); i++) {

                listdata.add(element.getAsJsonArray().get(i).toString().replace("\"", ""));
            }
        }
        return listdata;
    }

}
