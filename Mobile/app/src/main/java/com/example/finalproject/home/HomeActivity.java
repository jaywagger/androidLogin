package com.example.finalproject.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class HomeActivity extends AppCompatActivity  {
    Button btn1; //공조
    Button open_btn;//문열기
    Button close_btn;//문닫기
    AsyncTaskDoorController asyncTaskDoorController;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    Socket socket;
    OutputStream os;
    PrintWriter pw;
    String userID;
    StringTokenizer st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn1 = findViewById(R.id.btn1);
        open_btn = findViewById(R.id.open_btn);
        close_btn = findViewById(R.id.close_btn);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        asyncTaskDoorController = new AsyncTaskDoorController();
        Log.d(userID,"유저아이디");
        asyncTaskDoorController.execute(10,20);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,TemperatureControllActivity.class);
                startActivity(intent);
            }
        });
    }
    //차량 문 제어 ==> open & close
    public void send_msg(final View view){
        new Thread(new Runnable() {
            String message = "";
            @Override
            public void run() {
                if(view.getId() == open_btn.getId()){
                    message = "sensor/led_on/"+userID;
                }else if(view.getId() == close_btn.getId()){
                    message = "sensor/led_off/"+userID;
                }
                //서버로 메시지 전송
                pw.println(message);
                pw.flush();
            }
        }).start();
    }

    class AsyncTaskDoorController extends AsyncTask<Integer,String,String>{

        @Override
        protected String doInBackground(Integer... integers) {
            try {
                socket = new Socket("70.12.230.80",55555);
                if (socket !=null){
                    ioWork();
                }
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            String msg;
                            try {
                                msg = br.readLine();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                t1.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }
    void ioWork(){
        try {
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            os = socket.getOutputStream();
            pw = new PrintWriter(os,true);

        } catch (IOException e){
            e.printStackTrace();
        }

    }
    private void filteringMsg(String msg) {
        System.out.println("서버가 받은 클라이언트의 메시지:"+msg);
        st = new StringTokenizer(msg,"/");
        String protocol = st.nextToken();
        /*if(protocol.equals("sensor")) {
            String message = st.nextToken();
            String userid = st.nextToken();
            System.out.println("메세지==>"+message+"유저아이디"+userid);
            //serverView.taclientlist.append(userid+"이 전송한 메시지:"+msg+"\n");*/
         if(protocol.equals("crash")) {
            String message = st.nextToken();
            String carnum = st.nextToken();
            if(message=="0") {
                Toast.makeText(this, carnum+"에서 충돌센서작동", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            is.close();
            isr.close();
            br.close();
            os.close();
            pw.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
