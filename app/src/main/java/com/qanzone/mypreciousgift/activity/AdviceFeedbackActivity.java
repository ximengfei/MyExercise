package com.qanzone.mypreciousgift.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qanzone.mypreciousgift.R;
import com.qanzone.mypreciousgift.bean.Advice;
import com.qanzone.mypreciousgift.utils.PublicFunc;
import com.qanzone.mypreciousgift.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AdviceFeedbackActivity extends AppCompatActivity {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.user_icon)
    CircleImageView userIcon;
    @BindView(R.id.user_phone_number)
    EditText userPhoneNumber;
    @BindView(R.id.edt_af_content)
    EditText edtAfContent;
    @BindView(R.id.ll_advice)
    LinearLayout llAdvice;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.activity_advice_feedback)
    RelativeLayout activityAdviceFeedback;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_feedback);
        ButterKnife.bind(this);
        mContext = this;
        titleText.setText("意见反馈");
    }

    @OnClick(R.id.title_back)
    void back() {
        finish();
    }

    @OnClick(R.id.btn_submit)
    void btn_submit() {

        String contact = userPhoneNumber.getText().toString();
        String content = edtAfContent.getText().toString();
        if (contact.isEmpty()) {
            PublicFunc.showMsg(mContext, getResources().getString(R.string.contact_is_empty));
            return;
        }

        if (content.isEmpty()) {
            PublicFunc.showMsg(mContext, getResources().getString(R.string.advice_is_empty));
            return;
        }

        //提交数据到后台
        Advice p2 = new Advice();
        p2.setContact(contact);
        p2.setContent(content);
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    AlertDialog dialog = PublicFunc.showDialog(mContext, "Complete", "蟹蟹你的建议~~",
                            "呵呵", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            }, null, null);
                    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            finish();
                        }
                    });
                }else{
                    PublicFunc.showMsg(mContext, "反馈失败，原因：" + e.getMessage());
                }
            }
        });



    }
}
