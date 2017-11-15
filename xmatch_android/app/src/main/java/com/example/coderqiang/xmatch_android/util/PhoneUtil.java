package com.example.coderqiang.xmatch_android.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;

/**
 * Created by coderqiang on 2017/11/15.
 */

public class PhoneUtil {

    public static void call(Context context,String phone) {
        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
        context.startActivity(intent);
    }

    public static void sendEmail(Context context, String phoneNumber, String content) {
        if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
            intent.putExtra("sms_body", content);
            context.startActivity(intent);
        }
    }

}
