  package com.decard.mobilesdkexample.LoginRegister;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.decard.mobilesdkexample.Choose;
import com.decard.mobilesdkexample.R;
import com.google.gson.Gson;

import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

  public class loginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "loginActivity";
    private DBOpenHelper mDBOpenHelper;
    private TextView mTvLoginactivityRegister;
    private RelativeLayout mRlLoginactivityTop;
    private EditText mEtLoginactivityUsername;
    private EditText mEtLoginactivityPassword;
    private LinearLayout mLlLoginactivityTwo;
    private Button mBtLoginactivityLogin;
    private ImageView mIvLoginActivityBack;
    private StringBuffer mStrBuffer;
    private MessageHandler mHandler = new MessageHandler();

    private static final int INPUT_IS_EMPTY = 0;
    private static final int REQUST_SUCCESS = 1;
    private static final int REQUST_FAIL = 2;

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

        initView();

        mDBOpenHelper = new DBOpenHelper(this);
    }

    /**
     * onCreae()中大的布局已经摆放好了，接下来就该把layout里的东西
     * 声明、实例化对象然后有行为的赋予其行为
     * 这样就可以把视图层View也就是layout 与 控制层 Java 结合起来了
     */
    private void initView() {
        // 初始化控件
        mBtLoginactivityLogin = findViewById(R.id.bt_loginactivity_login);
        mTvLoginactivityRegister = findViewById(R.id.tv_loginactivity_register);
        mRlLoginactivityTop = findViewById(R.id.rl_loginactivity_top);
        mEtLoginactivityUsername = findViewById(R.id.et_loginactivity_username);
        mEtLoginactivityPassword = findViewById(R.id.et_loginactivity_password);
        mLlLoginactivityTwo = findViewById(R.id.ll_loginactivity_two);
        mIvLoginActivityBack = findViewById(R.id.iv_loginactivity_back);

        // 设置点击事件监听器
        mBtLoginactivityLogin.setOnClickListener(this);
        mTvLoginactivityRegister.setOnClickListener(this);
        mIvLoginActivityBack.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_loginactivity_back:
                Intent intent1 = new Intent(this, Choose.class);
                startActivity(intent1);
                finish();
                break;
            // 跳转到注册界面
            case R.id.tv_loginactivity_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
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
                if (TextUtils.isEmpty(accountNo) || TextUtils.isEmpty(password)){
                    mHandler.sendEmptyMessage(INPUT_IS_EMPTY);
                    return;
                }
//                connectIntenet(accountNo, password);
                this.postParamsJson(accountNo, password);
//                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
//                    ArrayList<LoginRequest> data = mDBOpenHelper.getAllData();
//                    boolean match = false;
//                    for (int i = 0; i < data.size(); i++) {
//                        LoginRequest user = data.get(i);
//                        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
//                            match = true;
//                            break;
//                        } else {
//                            match = false;
//                        }
//                    }
//                    if (match) {
//                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(this, Choose.class);
//                        startActivity(intent);
//                        finish();//销毁此Activity
//                    } else {
//                        Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
//                }
                break;
        }
    }

    private void connectIntenet(String accountNo, String password) {

        final String OrganizationCode = "1001";
        LoginRequest loginRequest = new LoginRequest(accountNo, OrganizationCode);

        new Thread() {
            public void run() {
                OutputStream outputStream = null;
                InputStream inputStream = null;
                ObjectInputStream objectInputStream = null;
                try {
//                    LoginRequest reqSend = new LoginRequest(accountNo, OrganizationCode);

                    //User u1=new User("testName","555555",18);
                    Gson gson=new Gson();
                    String json=gson.toJson(loginRequest);
                    System.out.println("序列化："+json);

                    LoginRequest loginResponse =gson.fromJson(json,LoginRequest.class);
                    System.out.println("反序列化："+ loginResponse.getAccountNo()+"-"+loginResponse.getPassword()+"-"+loginResponse.getOrganizationCode());


//                    URL url = new URL("https://www.zojoscreen.com/api/Values/GetObjectInfos/");
                    URL url = new URL("https://www.zojoscreen.com/api/Values/Login/");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    // 设置httpURLConnection连接的属性
                    httpURLConnection.setRequestMethod("POST");// 设置请求方式为POST
                    httpURLConnection.setReadTimeout(8000);// 设置读取超时时间
                    httpURLConnection.setConnectTimeout(8000);// 设置连接超时时间
                    // 设置
                    httpURLConnection.setDefaultUseCaches(false);// 设置是否使用缓冲区
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    // 打开连接
                    httpURLConnection.connect();
                    String requestStr = JSON.toJSONString(loginRequest);
                    outputStream = httpURLConnection.getOutputStream();
                    outputStream.write(requestStr.getBytes());
                    outputStream.flush();
                    int code = httpURLConnection.getResponseCode();
                    if (code == httpURLConnection.HTTP_OK) {
                        // 从服务器得到数据
                        inputStream = httpURLConnection.getInputStream();
                        mStrBuffer = new StringBuffer();
                        int length = 0;
                        byte[] bytes = new byte[1024 * 1024];
                        while ((length = inputStream.read(bytes)) != -1) {
                            mStrBuffer.append(new String(bytes, 0, length));
                        }
                        mHandler.sendEmptyMessage(REQUST_SUCCESS);
                    }else {
                        mHandler.sendEmptyMessage(REQUST_FAIL);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (outputStream != null && inputStream != null){
                        try {
                         outputStream.close();
                         inputStream.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }

    private void postParamsJson(String accountNo,String password){
        final String OrganizationCode = "1001";
        LoginRequest loginRequest = new LoginRequest(accountNo, OrganizationCode);

        new Thread(){
            public void  run()
            {
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

//                    JSONObject object = new JSONObject();
//                    object.put("AccountNo", "root");
//                    object.put("OrganizationCode", "1001");
//                    String  paramsString = object.toJSONString();
                    Gson gson=new Gson();
                    String paramsString=gson.toJson(loginRequest);


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

                        bos = new ByteArrayOutputStream();
                        byte[] buffer = new byte[10240];
                        int len = -1;
                        while ((len = inputStream.read(buffer)) != -1) {
                            bos.write(buffer, 0, len);
                        }
                        bos.flush();
                        byte[] resultByte = bos.toByteArray();
//                LogUtils.d(resultByte.length + "");
                        resultString = new String(resultByte);
                    } else {
//                LogUtils.e("httpUtils", "ResponseCode:" + responseCode);
                    }

                    GetAccountInfoResponse getAccountInfo =gson.fromJson(resultString,GetAccountInfoResponse.class);

//                    if(getAccountInfo.getResultCode()=="0")
                    {
                        if (getAccountInfo.getRecStatus() == "2") //注销状态，判断是否为注销用户
                        {
                            //var retStatusMsg = string.Format("该登录用户（{0}）已注销！", getAccountInfoResponse.getAccountNO());
                            return;
                        }
                        else if (getAccountInfo.getRecStatus()  == "0")
                        {
                            //var retStatusMsg = string.Format("该登录用户（{0}）是创建状态请审核后再登录！", getAccountInfoResponse.getAccountNO());
                            return;
                        }
                        String sSignSalt = getAccountInfo.getSignSalt();
                        String sourcePwd = String.format(password+sSignSalt);
                        String inputPswStr = ConvertMD5(sourcePwd);
                        if (inputPswStr == getAccountInfo.getAccountPwd())
                        {

                            //CurrAccount = getAccountInfo;//赋值当前登录对象信息
                            //用户名密码验证成功！进入登录后界面
                        }
                        else
                        {
                            //清除密码输入框
                            //("密码输入有误，请重新输入！");
                            return;
                        }
                    }
//                    else {
//                        //用户名不存在，重新输入
//                    }
//                    Log.i(TAG, "postParamsJson: " + resultString);

//            LogUtils.d(resultString + "");
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
              if(TextUtils.isEmpty(sourceStr)){
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
              return buf.toString();

          } catch (Exception e) {
              e.printStackTrace();
              return null;
          }
      }

    class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message message){
            switch (message.what){
                case INPUT_IS_EMPTY:
                    Toast.makeText(loginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    break;
                case REQUST_SUCCESS:
                    Toast.makeText(loginActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                    break;
                case REQUST_FAIL:
                    Toast.makeText(loginActivity.this, "请求连接失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}



