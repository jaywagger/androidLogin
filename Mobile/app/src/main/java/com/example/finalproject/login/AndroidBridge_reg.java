package com.example.finalproject.login;

import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.camera.FaceRec_reg;


public class AndroidBridge_reg extends AppCompatActivity {
    private String TAG = "AndroidBridge";
    final public Handler handler= new Handler();

    private WebView mAppview;
    private FaceRec_reg mContext;

    TextView webText;



    public AndroidBridge_reg(WebView register_face, FaceRec_reg face) {
        mAppview = register_face;
        mContext = face;

    }

    @JavascriptInterface
    public void call_log2(final String msg){

        Log.d(TAG, msg);
        handler.post(new Runnable() {
            @Override
            public void run() {
                //mAppview.loadUrl("javascript:alert('["+msg+"] 라고 로그를 남겼습니다.')");
                Log.d(TAG, msg);
                /*webText = findViewById(R.id.webText);
                webText.setText(msg);*/
            }
        });
    }

}
