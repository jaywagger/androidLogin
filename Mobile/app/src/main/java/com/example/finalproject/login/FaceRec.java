package com.example.finalproject.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

public class FaceRec extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST = 1;
    private static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    private static final String PERMISSION_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;



    WebView webView;
    String TAG = "태그";



    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_recog);

        webView =findViewById(R.id.webview);


        //webText.setText(webView.get);




         webView.setWebChromeClient(new WebChromeClient() {
           public void onPermissionRequest(final PermissionRequest request) {
               Log.d(TAG, "onPermissionRequest");
               FaceRec.this.runOnUiThread(new Runnable() {
                   @TargetApi(Build.VERSION_CODES.M)
                   @Override
                   public void run() {
                       Log.d(TAG, request.getOrigin().toString());
                       if(request.getOrigin().toString().equals("file:///")) {
                           Log.d(TAG, "GRANTED");
                           request.grant(request.getResources());
                       } else {
                           Log.d(TAG, "DENIED");
                           request.deny();
                       }
                   }
               });
           }
        });

        if (hasPermission()) {
            webView();
        } else {
            requestPermission();
        }

        AndroidBridge ab = new AndroidBridge(webView, FaceRec.this);
        webView.addJavascriptInterface(ab,"Android");

    }

    @SuppressLint("JavascriptInterface")
    public void webView(){
        String url ="file:///android_asset/sample.html";
        WebSettings settings = webView.getSettings();
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);




        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(url);
            }
        });

    }




    @Override
    public void onRequestPermissionsResult(
            final int requestCode, final String[] permissions, final int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                WebSettings settings = webView.getSettings();
                settings.setJavaScriptEnabled(true);
                webView.loadUrl("file:///android_asset/sample.html");
            } else {
                requestPermission();
            }
        }
    }
    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(PERMISSION_CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(PERMISSION_STORAGE) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (shouldShowRequestPermissionRationale(PERMISSION_CAMERA) ||
                    shouldShowRequestPermissionRationale(PERMISSION_STORAGE)) {

            }
            requestPermissions(new String[] {PERMISSION_CAMERA, PERMISSION_STORAGE}, PERMISSIONS_REQUEST);
        }
    }

}
