package com.example.finalproject.voice;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

import java.util.Locale;
import static android.speech.tts.TextToSpeech.ERROR;

//https://sharp57dev.tistory.com/27 여기 참고하면 자세한 설명 기재되어 있음
public class voiceActivity extends AppCompatActivity {
    private TextToSpeech tts;              // TTS 변수 선언
    private EditText editText;
    private Button button01, button02, button03, button04, button05;

    public voiceActivity() {
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_voice);


            button01 = (Button) findViewById(R.id.button01);
            button02 = (Button) findViewById(R.id.button02);


            String doorOpen = "차 문이 열렸습니다.";
            String doorClose = "차 문이 닫혔습니다.";



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
            button01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // editText에 있는 문장을 읽는다.
                    tts.speak(doorOpen,TextToSpeech.QUEUE_FLUSH, null);
                }
            });
            button02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //tts.setPitch(2.0f);         // 음성 톤을 2.0배 올려준다.
                    //tts.setSpeechRate(1.0f);    // 읽는 속도는 기본 설정
                    // editText에 있는 문장을 읽는다.
                    tts.speak(doorClose,TextToSpeech.QUEUE_FLUSH, null);
                }
            });


        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            // TTS 객체가 남아있다면 실행을 중지하고 메모리에서 제거한다.
            if(tts != null){
                tts.stop();
                tts.shutdown();
                tts = null;
            }
        }
    }