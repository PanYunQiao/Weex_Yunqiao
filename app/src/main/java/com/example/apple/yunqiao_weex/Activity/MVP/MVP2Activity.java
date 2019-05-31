package com.example.apple.yunqiao_weex.Activity.MVP;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.yunqiao_weex.R;

public class MVP2Activity extends AppCompatActivity implements View.OnClickListener,ILoginView{
    private EditText et_password;
    private EditText et_username;
    private Button button;
    private IPresenter iPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp2);
        //initView
        initView();
        iPresenter=new IpresenterCompl(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        et_password = (EditText) findViewById(R.id.et_password);
        et_username = (EditText) findViewById(R.id.et_username);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                String use=et_username.getText().toString().trim();
                String pwdstr=et_password.getText().toString().trim();
                //判断输入内容不能为空
                if (!TextUtils.isEmpty(use)&&!TextUtils.isEmpty(pwdstr)){
                    //调用逻辑层的登录
                    iPresenter.login(use,pwdstr);
                }else{

                }
                break;

        }
    }

    @Override
    public void LoginResult(boolean ret, String code) {
        Toast.makeText(this,"登陆结果"+ret+code,Toast.LENGTH_SHORT).show();;
    }
}
