package com.example.secoundpracticetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener   {
    public Button bt1;
    public Button bt2;
    public Button bt3;
    private float startX,startY,offSetX,offSetY;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt1.setBackgroundResource(R.drawable.bt1_n);
        bt2.setBackgroundResource(R.drawable.bt2_n);
        bt3.setBackgroundResource(R.drawable.bt3_n);
        this.setGestureListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
                i=1;
               account(i);
                break;
            case R.id.bt2:
                i=2;
               account(i);
                break;
            case  R.id.bt3:
                i=3;
                account(3);
                break;
            default:
                break;

        }
    }
    private void setGestureListener(){
        View myView =(View)findViewById(R.id.mylayout);
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //记录点下屏幕的位置
                        startX=event.getX();
                        startY=event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        //记录离开屏幕的位置
                        offSetX=event.getX()-startX;
                        offSetY=event.getY()-startY;
                        if (Math.abs(offSetX)>Math.abs(offSetY)) {
                            if (offSetX<-20) {
                                swipeRight();
                            }else if (offSetX>20) {
                                swipeLeft();
                            }
                        }
                    default:
                        break;
                }
                return true;
            }
        });
    }
    private int getI(){
        return i;
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.bottom_layout,fragment);
        fragmentTransaction.commit();
    }
    private void swipeRight() {
        i=getI()+1;
        if (i>3){
            i=1;
        }
        account(i);
    }
    private void swipeLeft() {
        i=getI()-1;
        if (i<1){
            i=3;
        }
        account(i);
    }

    private void red(){
        replaceFragment(new RedFragment());
        bt1.setBackgroundResource(R.drawable.bt1_h);
        bt2.setBackgroundResource(R.drawable.bt2_n);
        bt3.setBackgroundResource(R.drawable.bt3_n);
    }
    private void blue(){
        replaceFragment(new BlueFragment());
        bt2.setBackgroundResource(R.drawable.bt2_h);
        bt1.setBackgroundResource(R.drawable.bt1_n);
        bt3.setBackgroundResource(R.drawable.bt3_n);
    }
    private void yellow(){
        replaceFragment(new YellowFragment());
        bt3.setBackgroundResource(R.drawable.bt3_h);
        bt1.setBackgroundResource(R.drawable.bt1_n);
        bt2.setBackgroundResource(R.drawable.bt2_n);
    }
    private void  account(Integer i){
        if (i==1){
            red();
        }else if (i==2){
            blue();
        }else if (i==3){
            yellow();
        }
        return ;
    }
}
