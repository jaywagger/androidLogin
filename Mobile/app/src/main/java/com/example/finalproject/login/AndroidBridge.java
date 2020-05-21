package com.example.finalproject.login;

import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class AndroidBridge extends AppCompatActivity {
    private String TAG = "AndroidBridge";
    final public Handler handler= new Handler();

    private WebView mAppview;
    private FaceRec mContext;

    TextView webText;



    public AndroidBridge(WebView webView, FaceRec faceRec) {
        mAppview = webView;
        mContext = faceRec;

    }

    @JavascriptInterface
    public void call_log(final String msg){

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
