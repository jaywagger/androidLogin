package com.example.finalproject.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.login.LoginActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;
import java.util.StringTokenizer;

import static android.speech.tts.TextToSpeech.ERROR;

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


    //음성
    private TextToSpeech tts;
    String doorOpen = "차 문이 열렸습니다.";
    String doorClose = "차 문이 닫혔습니다.";
    String airCon = "공조 제어 중 입니다.";

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
                tts.speak(airCon,TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        // TTS를 생성하고 OnInitListener로 초기화 한다.
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);
                }
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
                    tts.setSpeechRate(0.8f);
                    tts.speak(doorOpen,TextToSpeech.QUEUE_FLUSH, null);

                }else if(view.getId() == close_btn.getId()){
                    message = "sensor/led_off/"+userID;
                    tts.setSpeechRate(0.8f);
                    tts.speak(doorClose,TextToSpeech.QUEUE_FLUSH, null);
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
                socket = new Socket("70.12.224.100",12345);
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
                                filteringMsg(msg);
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

            pw.println("android");
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    private void filteringMsg(String msg) {
        System.out.println("서버가 받은 클라이언트의 메시지:"+msg);
        st = new StringTokenizer(msg,"/");
        String protocol = st.nextToken();
        Log.d(msg,"ddddd");
        /*if(protocol.equals("sensor")) {
            String message = st.nextToken();
            String userid = st.nextToken();
            System.out.println("메세지==>"+message+"유저아이디"+userid);
            //serverView.taclientlist.append(userid+"이 전송한 메시지:"+msg+"\n");*/
         if(protocol.equals("crash")) {
            String message = st.nextToken();
            String carnum = st.nextToken();
            if(message=="0") {
                Toast.makeText(HomeActivity.this, carnum+"에서 충돌센서작동", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //메뉴: 로그아웃
    //엑티비티가 만들어질 때 자동으로 호출되는 메서드: 이 안에서 메뉴를 생성한다
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout,menu);
        return true;
    }
    //옵션 메뉴 클릭시 호출되는 메소드 (이벤트연결)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("menu", item.getItemId()+"");
        int id = item.getItemId();
        String msg="";
        switch (id){
            case R.id.action_myInfo:
                Toast.makeText(this, "아직 없습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_sign_out:
                signOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //앱바 로그아웃
    private void signOut(){
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(tts != null){
            tts.stop();
            tts.shutdown();
            tts = null;
        }
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
