package com.example.coderqiang.xmatch_android.util;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.List;

/**
 * Created by coderqiang on 2017/11/15.
 */

public class PhoneUtil {

    @SuppressLint("MissingPermission")
    public static void call(Context context, String phone) {
        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone));
        context.startActivity(intent);
    }

    public static void sendEmail(Context context, String phoneNumber, String content) {
        phoneNumber=phoneNumber;
//        if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
            intent.putExtra("sms_body", content);
            context.startActivity(intent);
//        }
    }

    public static void sendManyEmail(Context context, List<String> phoneNumber, String content) {
        try {
            SmsManager manager = SmsManager.getDefault();
            //自定一两个intent,发送两个隐式意图,这两个隐式意图自己定义
            Intent intent1 =  new Intent("com.example.coderqiang.xmatch_android.SENT");;
            Intent intent2 = new Intent("com.example.coderqiang.xmatch_android.DELIVERY");
        /*
        延迟意图 sendTextMessage()方法后边两个参数pi1和pi2
         为pendingIntent 延迟意图
         这两个延迟意图用来查看短信是否发出和接收,pi1在发送者发送信息送达基站时,会调用,标志着短信
        已经发出,pi2是从基站送达接收者手机时会被调用,标志着短信已
        经由接收者接收,因为发送出去的信息不一定会被接收到,所以pi2
        不一定会被调用,这里的pi1和pi2通过广播的方式,通知短信发送的
        相关信息
         */

            PendingIntent pi1 = PendingIntent.getBroadcast(context, 0, intent1 , 0);//延迟意图
            PendingIntent pi2 = PendingIntent.getBroadcast(context, 0, intent2 , 0);

            for (int i=0;i<phoneNumber.size();i++) {
                //循环发送短信
                System.out.println("phone:"+phoneNumber.get(i));
                manager.sendTextMessage(phoneNumber.get(i), null, content , pi1, pi2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
