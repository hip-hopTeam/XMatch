package com.example.coderqiang.xmatch_android.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by coderqiang on 2017/11/12.
 */

public class SwtichActivityUtil {

    public static void toActivity(Context from,Class to){
        Intent intent = new Intent(from,to);
        from.startActivity(intent);
    }

}
