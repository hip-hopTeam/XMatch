package com.example.coderqiang.xmatch_android.util;

import android.content.Context;

import com.example.coderqiang.xmatch_android.dto.DepManagerDto;
import com.example.coderqiang.xmatch_android.model.DepManager;

/**
 * Created by coderqiang on 2017/11/13.
 */

public class DepManagerLab {
    private static DepManagerLab depManagerLab;

    private Context context;

    private DepManagerDto depManagerDto;

    private DepManagerLab(Context context){
        this.context=context;
    }

    public static DepManagerLab get(Context context){
        if (depManagerLab == null) {
            depManagerLab = new DepManagerLab(context);
        }
        return depManagerLab;
    }

    public DepManagerDto getDepManagerDto() {
        return depManagerDto;
    }

    public void setDepManagerDto(DepManagerDto depManagerDto) {
        this.depManagerDto = depManagerDto;
    }

}
