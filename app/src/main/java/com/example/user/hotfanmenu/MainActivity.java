package com.example.user.hotfanmenu;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.myflupifanmenu.FanMenuManager;
import com.example.myflupifanmenu.MenuItemObj;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RelativeLayout mainLayout;
    private FloatingActionButton mainFloatingBtn;
    private FanMenuManager menuManager;
    boolean odd = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainContainer);
        mainFloatingBtn = findViewById(R.id.mainFloatingBtn);


        ArrayList<MenuItemObj> list = new ArrayList<>();
        list.add(new MenuItemObj(0, "facebook", "move to facebookPath", "www.fakeImage.com"));
        list.add(new MenuItemObj(0, "facebook", "move to facebookPath", "www.fakeImage.com"));
        list.add(new MenuItemObj(1, "twitter", "move to twitter Path", "www.fakeImage.com"));
        list.add(new MenuItemObj(1, "twitter", "move to twitter Path", "www.fakeImage.com"));
        list.add(new MenuItemObj(2, "sms", "move to sms Pathh", "www.fakeImage.com"));
//        list.add(new MenuItemObj(3, "servicCenter", "move to servicCenter Path", "www.fakeImage.com"));
//        list.add(new MenuItemObj(3, "servicCenter", "move to servicCenter Path", "www.fakeImage.com"));


        menuManager = new FanMenuManager(this, mainLayout, mainFloatingBtn, list);


        mainFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                odd = !odd;
                if (odd) {
                    menuManager.open();
                } else {
                    menuManager.close();
                }
            }
        });

    }


}
