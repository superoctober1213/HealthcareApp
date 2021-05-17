package com.decard.mobilesdkexample;
/**
 * 纯粹实现登录注册功能，其它功能都被注释掉了
 * 起作用的代码（连带着packag、import算上） 共 73 行
 * 不多吧？
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.decard.mobilesdkexample.BaseClass.GetAccountInfoResponse;
import com.decard.mobilesdkexample.BaseClass.LoginRequest;
import com.decard.mobilesdkexample.HelpClass.CustomerHelp;
import com.google.gson.Gson;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.zip.GZIPInputStream;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "loginActivity";
    private EditText mEtLoginactivityUsername;
    private EditText mEtLoginactivityPassword;
    private Button mBtLoginactivityLogin;
    private MessageHandler mHandler = new MessageHandler();
    MyApp myApp;
    private TextView textView123;

    private static final int INPUT_IS_EMPTY = 0;
    private static final int REQUST_SUCCESS = 1;
    private static final int REQUST_FAIL = 2;
    private static final int REQUST_ERROR1 = 3;
    private static final int REQUST_ERROR2 = 4;
    private static final int REQUST_ERROR3 = 5;


    /**
     * 创建 Activity 时先来重写 onCreate() 方法
     * 保存实例状态
     * super.onCreate(savedInstanceState);
     * 设置视图内容的配置文件
     * setContentView(R.layout.activity_login);
     * 上面这行代码真正实现了把视图层 View 也就是 layout 的内容放到 Activity 中进行显示
     * 初始化视图中的控件对象 initView()
     * 实例化 DBReadHelper，待会进行登录验证的时候要用来进行数据查询
     * mDBOpenHelper = new DBReadHelper(this);
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myApp = (MyApp) getApplication();
        initView();
    }

    /**
     * onCreae()中大的布局已经摆放好了，接下来就该把layout里的东西
     * 声明、实例化对象然后有行为的赋予其行为
     * 这样就可以把视图层View也就是layout 与 控制层 Java 结合起来了
     */
    private void initView() {
        // 初始化控件
        mBtLoginactivityLogin = findViewById(R.id.bt_loginactivity_login);
//        mTvLoginactivityRegister = findViewById(R.id.tv_loginactivity_register);
        mEtLoginactivityUsername = findViewById(R.id.et_loginactivity_username);
        mEtLoginactivityPassword = findViewById(R.id.et_loginactivity_password);

        // 设置点击事件监听器
        mBtLoginactivityLogin.setOnClickListener(this);
//        mTvLoginactivityRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            // 跳转到注册界面
//            case R.id.tv_loginactivity_register:
//                startActivity(new Intent(this, RegisterActivity.class));
//                finish();
//                break;
            /**
             * 登录验证：
             *
             * 从EditText的对象上获取文本编辑框输入的数据，并把左右两边的空格去掉
             *  String name = mEtLoginactivityUsername.getText().toString().trim();
             *  String password = mEtLoginactivityPassword.getText().toString().trim();
             *  进行匹配验证,先判断一下用户名密码是否为空，
             *  if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password))
             *  再进而for循环判断是否与数据库中的数据相匹配
             *  if (name.equals(user.getName()) && password.equals(user.getPassword()))
             *  一旦匹配，立即将match = true；break；
             *  否则 一直匹配到结束 match = false；
             *
             *  登录成功之后，进行页面跳转：
             *
             *  Intent intent = new Intent(this, MainActivity.class);
             *  startActivity(intent);
             *  finish();//销毁此Activity
             */
            case R.id.bt_loginactivity_login:
                String accountNo = mEtLoginactivityUsername.getText().toString().trim();
                String password = mEtLoginactivityPassword.getText().toString().trim();
                if (TextUtils.isEmpty(accountNo) || TextUtils.isEmpty(password)) {
                    mHandler.sendEmptyMessage(INPUT_IS_EMPTY);
                    return;
                }
                this.postParamsJson(accountNo, password);
                break;
        }
    }

    private void postParamsJson(String accountNo, String password) {
        final String OrganizationCode = "1001";
        LoginRequest loginRequest = new LoginRequest(accountNo, OrganizationCode);

        new Thread() {
            public void run() {
                String resultString = null;
                HttpURLConnection conn = null;
                InputStream inputStream = null;
                ByteArrayOutputStream bos = null;
                try {
                    String srcUrl = "https://www.zojoscreen.com/api/Values/Login/";
                    URL url = new URL(srcUrl);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setUseCaches(false);
                    conn.connect();
                    Gson gson = new Gson();
                    String paramsString = gson.toJson(loginRequest);

                    //传入参数
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(paramsString.getBytes());

                    int responseCode = conn.getResponseCode();
                    if (responseCode == conn.HTTP_OK || true) {
                        if (null != conn.getHeaderField("Content-Encoding")) {
                            inputStream = new GZIPInputStream(conn.getInputStream());
                        } else {
                            inputStream = conn.getInputStream();

                        }
                    }
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,
                            "GB2312"));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);

                    }

                    GetAccountInfoResponse getAccountInfo = gson.fromJson(response.toString(),
                            GetAccountInfoResponse.class);

                    if (getAccountInfo.getResultCode().equals("0")) {

                        if (getAccountInfo.getRecStatus().equals("2")) {
                            //注销状态，判断是否为注销用户
                            //var retStatusMsg = string.Format("该登录用户（{0}）已注销！",
                            // getAccountInfoResponse.getAccountNO());
                            mHandler.sendEmptyMessage(REQUST_ERROR2);
                            return;
                        } else if (getAccountInfo.getRecStatus().equals("0")) {
                            //var retStatusMsg = string.Format("该登录用户（{0}）是创建状态请审核后再登录！",
                            // getAccountInfoResponse.getAccountNO());
                            mHandler.sendEmptyMessage(REQUST_ERROR1);
                            return;
                        } else if (getAccountInfo.getRecStatus().equals("1")) {
                            String sSignSalt = getAccountInfo.getSignSalt();
                            String sourcePwd = String.format(password + sSignSalt);
                            String inputPswStr = ConvertMD5(sourcePwd);
                            if (inputPswStr.equals(getAccountInfo.getAccountPwd())) {
                                //CurrAccount = getAccountInfo;//赋值当前登录对象信息
                                //用户名密码验证成功！进入登录后界面
                                myApp.currAccount = getAccountInfo;
                                mHandler.sendEmptyMessage(REQUST_SUCCESS);

                            } else {
                                //清除密码输入框
                                //("密码输入有误，请重新输入！");
                                mHandler.sendEmptyMessage(REQUST_FAIL);
                                return;
                            }
                        }
                    } else {
                        mHandler.sendEmptyMessage(REQUST_ERROR3);
                        //用户名不存在，重新输入
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (bos != null) {
                        try {
                            bos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }


    @Nullable
    private static String ConvertMD5(String sourceStr) {
        try {
            if (TextUtils.isEmpty(sourceStr)) {
                return "";
            }
            // 获得MD5摘要算法的 MessageDigest对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(sourceStr.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int tmp = md[i];
                if (tmp < 0)
                    tmp += 256;
                if (tmp < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(tmp));
            }
            //return buf.toString().substring(8, 24);// 16位加密
            return buf.toString().toUpperCase();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case INPUT_IS_EMPTY:
                    Toast.makeText(loginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    break;
                case REQUST_SUCCESS:
                    Toast.makeText(loginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, CustomerHelp.class);
                    startActivity(intent);
                    finish();
                    break;
                case REQUST_FAIL:
                    mEtLoginactivityUsername.setText("");
                    mEtLoginactivityPassword.setText("");
                    Toast.makeText(loginActivity.this, "密码输入有误，请重新输入！", Toast.LENGTH_SHORT).show();
                    break;
                case REQUST_ERROR1:
                    Toast.makeText(loginActivity.this, "该登录用户是创建状态,请审核后再登录！", Toast.LENGTH_SHORT).show();
                    break;
                case REQUST_ERROR2:
                    Toast.makeText(loginActivity.this, "该登录用户已注销！", Toast.LENGTH_SHORT).show();
                    break;
                case REQUST_ERROR3:
                    mEtLoginactivityUsername.setText("");
                    mEtLoginactivityPassword.setText("");
                    Toast.makeText(loginActivity.this, "用户名不存在，重新输入！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}



