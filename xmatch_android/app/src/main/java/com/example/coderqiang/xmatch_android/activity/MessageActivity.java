package com.example.coderqiang.xmatch_android.activity;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coderqiang.xmatch_android.R;
import com.example.coderqiang.xmatch_android.api.DepManagerApi;
import com.example.coderqiang.xmatch_android.dto.MemberDto;
import com.example.coderqiang.xmatch_android.util.PhoneUtil;
import com.example.coderqiang.xmatch_android.util.WindowUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by coderqiang on 2017/12/14.
 */

public class MessageActivity extends Activity {
    private static final String TAG = "MessageActivity";


    @BindView(R.id.message_back)
    ImageView messageBack;
    @BindView(R.id.message_select)
    TextView messageSelect;
    @BindView(R.id.message_bar)
    AppBarLayout messageBar;
    @BindView(R.id.message_content)
    EditText messageContent;
    @BindView(R.id.message_send)
    TextView messageSend;
    @BindView(R.id.message_recycler)
    RecyclerView messageRecycler;

    Activity activity;


    private MyReciver receiver;//声明一个广播接收器
    private List<MessageMember> allMemberDtos = new ArrayList<>();
    private List<MessageMember> sendMemberDtos = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        WindowUtil.setConfig(this);
        ButterKnife.bind(this);
        activity = this;
        initView();
        initData();
    }

    private void initView() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(activity,2);
        messageRecycler.setLayoutManager(gridLayoutManager);
        messageRecycler.setAdapter(new MessageAdapter());
    }

    private void initData() {
        Observable.create(new Observable.OnSubscribe<List<MemberDto>>() {
            @Override
            public void call(Subscriber<? super List<MemberDto>> subscriber) {
                List<MemberDto> memberDtos = DepManagerApi.getMembersByState(activity);
                if (memberDtos == null) {
                    subscriber.onCompleted();
                } else {
                    subscriber.onNext(memberDtos);
                }

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<MemberDto>>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: memeberDto==null");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<MemberDto> memberDtos) {
                allMemberDtos.clear();
                for (MemberDto memberDto : memberDtos) {
                    if (memberDto.getState() == MemberDto.STATE_OFFICE) {
                        MessageMember messageMember = new MessageMember();
                        messageMember.setUserName(memberDto.getUsername());
                        messageMember.setPhone(memberDto.getPhoneNum());
                        allMemberDtos.add(messageMember);
                    }
                }
                messageRecycler.getAdapter().notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver=new MyReciver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.coderqiang.xmatch_android.SENT");
        filter.addAction("com.example.coderqiang.xmatch_android.DELIVERY");
        /**
         * 安卓系统后台有一个服务（Service），专门用来接收短消息。当有新的消息达到时，它会发送一个广播，
         * 广播的Action是“android.provider.Telephony.SMS_RECEIVED”并且将收到短消息作为广播Intent的一部分（Intent的Extra）发送出去。权限高的先得到
         */
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        //给filter添加一个最高权限;
        filter.setPriority(Integer.MAX_VALUE);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销注册
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    @OnClick({R.id.message_back, R.id.message_select, R.id.message_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.message_back:
                super.onBackPressed();
                break;
            case R.id.message_select:
                if (messageSelect.getText().equals("全选")){
                    for (MessageMember messageMember : allMemberDtos) {
                        messageMember.setCheck(true);
                    }
                    messageSelect.setText("全不选");
                }else {
                    for (MessageMember messageMember : allMemberDtos) {
                        messageMember.setCheck(false);
                    }
                    messageSelect.setText("全选");
                }
                messageRecycler.getAdapter().notifyDataSetChanged();
                break;
            case R.id.message_send:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(activity,"发送短信权限没给",Toast.LENGTH_SHORT).show();
                    //进行权限请求
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 2);
                }
                Log.i(TAG, "onViewClicked: 发送 "+sendMemberDtos.size());
                List<String> sendMembers = new ArrayList<>();
                for (int i=0;i<sendMemberDtos.size();i++) {
                    sendMembers.add(sendMemberDtos.get(i).getPhone());
                }
                PhoneUtil.sendManyEmail(activity,sendMembers,messageContent.getText().toString());
                break;
        }
    }

    class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MeessageHolder> {

        @Override
        public MessageAdapter.MeessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MeessageHolder(LayoutInflater.from(activity).inflate(R.layout.item_message_select, parent, false));
        }

        @Override
        public void onBindViewHolder(MessageAdapter.MeessageHolder holder, int position) {
            MessageMember memberDto=allMemberDtos.get(position);
            holder.itemMessageName.setText(memberDto.getUserName());
            holder.itemMessageState.setText("");
            if (memberDto.isCheck()) {
                holder.itemMessageCheck.setChecked(true);
            }else {
                holder.itemMessageCheck.setChecked(false);
            }
            if (memberDto.isSend()) {
                holder.itemMessageState.setText("发送成功");
                holder.itemMessageCheck.setVisibility(View.GONE);
            }
            holder.itemMessageCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        sendMemberDtos.add(memberDto);
                    }else {
                        sendMemberDtos.remove(memberDto);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return allMemberDtos.size();
        }


        class MeessageHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.item_message_check)
            CheckBox itemMessageCheck;
            @BindView(R.id.item_message_name)
            TextView itemMessageName;
            @BindView(R.id.item_message_state)
            TextView itemMessageState;
            @BindView(R.id.item_message_layout)
            LinearLayout itemMessageLayout;
            public MeessageHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    public class MyReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i(TAG, "onReceive: 接收到广播");
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    Log.d(TAG, "Activity.RESULT_OK");
                    if ("com.example.coderqiang.xmatch_android.SENT".equals(action)) {
                        Log.d("TAG", "短信发送成功" + System.currentTimeMillis());
                    } else if ("com.example.coderqiang.xmatch_android.DELIVERY".equals(action)) {
                        Log.i("TAG", "对方已经收到短信" + System.currentTimeMillis());
                    }
//                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
//                    Log.d(TAG, "RESULT_ERROR_GENERIC_FAILURE");
//                    break;
//                case SmsManager.RESULT_ERROR_NO_SERVICE:
//                    Log.d(TAG, "RESULT_ERROR_NO_SERVICE");
//                    break;
//                case SmsManager.RESULT_ERROR_NULL_PDU:
//                    Log.d(TAG, "RESULT_ERROR_NULL_PDU");
//                    break;
//                case SmsManager.RESULT_ERROR_RADIO_OFF:
//                    Log.d(TAG, "RESULT_ERROR_RADIO_OFF");
//                    break;
                default:
                    Log.d("TAG", "短信发送失败");
                    break;
            }
            //如果intent的action是android.provider.Telephony.SMS_RECEIVED,这时我们就可以开始处理短信内容了
            if ("android.provider.Telephony.SMS_RECEIVED".equals(action)) {
                //处理消息,StringBuilder 对象用来存放短信信息
                //String number 用来存放电话号码
                Bundle bundle = intent.getExtras();
                Object[] pdus = (Object[]) bundle.get("pdus");
                StringBuilder sb = new StringBuilder();
                String number = "";
                //把一个一个的pdus转换为一段段的短消息
                for (int i = 0; i < pdus.length; i++) {
                    SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    //然后把正文拼接起来
                    sb.append(message.getDisplayMessageBody());
                    //获取电话号码
                    number = message.getDisplayOriginatingAddress();
                }
                for (int i=0;i<sendMemberDtos.size();i++) {
                    sendMemberDtos.get(i).setSend(true);
                    messageRecycler.getAdapter().notifyDataSetChanged();
                }
            }

        }
    }

    class MessageMember{

        private String userName;
        private String phone;
        private boolean isCheck;
        private boolean isSend;

        public boolean isSend() {
            return isSend;
        }

        public void setSend(boolean send) {
            isSend = send;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }
    }
}
