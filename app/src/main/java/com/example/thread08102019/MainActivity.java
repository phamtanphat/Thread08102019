package com.example.thread08102019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int a , b ,c;
    Laco laco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = b = c = 0;
        laco = new Laco(0);
        //Thread : Luong phu
        // MainThread : Luong Chinh
        Thread threada = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (laco){
                    for (int i = 0 ; i <= 1000 ;){
                        if (laco.position == 0){
                            a = i;
                            Log.d("BBB", "Gia tri cua A : " + a);
                            laco.position = 1;
                            laco.notifyAll();
                            i++;
                        }else {
                            try {
                                laco.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        });
        Thread threadb = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (laco){
                    for (int i = 0 ; i <= 1000 ;){
                        if (laco.position == 1){
                            b = i;
                            Log.d("BBB", "Gia tri cua B : " + b);
                            laco.position = 2;
                            laco.notifyAll();
                            i++;
                        }else{
                            try {
                                laco.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        });
        Thread threadc = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (laco){
                    for (int i = 0 ; i <= 1000 ; ){
                        if (laco.position == 2){
                            c = a + b;
                            Log.d("BBB", "Gia tri cua C : " + c);
                            laco.position = 0;
                            laco.notifyAll();
                            i++;
                        }else {
                            try {
                                laco.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }

            }
        });

        threada.start();
        threadb.start();
        threadc.start();
    }
}
