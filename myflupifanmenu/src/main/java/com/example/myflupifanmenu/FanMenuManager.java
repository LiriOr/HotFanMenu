package com.example.myflupifanmenu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class FanMenuManager implements View.OnClickListener {


    private static final double ANGLE_FOR_ONE_SUB_MENU = 0;

    private Activity activity;
    private float widthOfScreen;
    private float halfScreen;
    private ArrayList<MenuItemView> buttonslist;
    private RelativeLayout.LayoutParams params;
    private Handler handler;
    private RelativeLayout dividerContainer;
    private RelativeLayout mainLayout;
    private FloatingActionButton mainFloatingBtn;
    ArrayList<MenuItemObj> listOfMenuItems;

    public FanMenuManager(Activity activity, RelativeLayout mainLayout, FloatingActionButton mainFloatingBtn, ArrayList<MenuItemObj> list) {
        this.activity = activity;
        this.mainFloatingBtn = mainFloatingBtn;
        this.params = (RelativeLayout.LayoutParams) mainFloatingBtn.getLayoutParams();
        this.listOfMenuItems = list;
        this.mainLayout = mainLayout;

        createDividerContainer();

        setWidthOfScreen(activity);
        halfScreen = getRadios();


        handler = new Handler();

    }


    public void open() {
        dividerContainer.removeAllViews();
        addSubButtons();
        mainFloatingBtn.bringToFront();
        dividerContainer.setVisibility(View.VISIBLE);
        int count = buttonslist.size()-1;
        for (int i = 0; i < buttonslist.size(); i++) {

            float indexX = (getXoffset(-i)) * -1;
            float indexY = getYoffset(i);
            startOpenAnimation(buttonslist.get(count--), i, indexX, indexY);
        }
    }


    public void close() {
        for (int i = 0; i < buttonslist.size(); i++) {
            float indexX = mainFloatingBtn.getTranslationX();
            float indexY = mainFloatingBtn.getTranslationY();
            startOpenAnimation(buttonslist.get(i), i, indexX, indexY);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dividerContainer.setVisibility(View.GONE);
            }
        }, 270);


    }


    private void setWidthOfScreen(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        widthOfScreen = size.x;
    }


    private float getRadios() {
        float radios = widthOfScreen / 2;
        if (listOfMenuItems.size() <= 5) {
            return radios;
        } else if (listOfMenuItems.size() < 9) {
            float num = (float) (listOfMenuItems.size() / 10.0);
            radios = widthOfScreen * num;
            radios = radios - 40;
        } else {
            float num = (float) (8 / 10.0);
            radios = widthOfScreen * num;
            radios = radios - 40;
        }
        return radios;
    }


    private void addSubButtons() {
        buttonslist = new ArrayList<>();
        for (int i = 0; i < listOfMenuItems.size(); i++) {

            MenuItemView itemContainer = new MenuItemView(activity);

            itemContainer.moveTitleToLeft(i*2);

            itemContainer.setTag(listOfMenuItems.get(i).getApplicationPath());
            itemContainer.setOnClickListener(this);


            buttonslist.add(itemContainer);
            dividerContainer.addView(itemContainer);
        }

    }

    private void createDividerContainer() {
        dividerContainer = new RelativeLayout(activity.getApplicationContext());
        dividerContainer.setBackgroundColor(Color.parseColor("#9c000000"));
        RelativeLayout.LayoutParams relParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dividerContainer.setLayoutParams(relParams);
        dividerContainer.setVisibility(View.GONE);
        mainLayout.addView(dividerContainer);

    }


    private void startOpenAnimation(final View v, final int index, final float indexX, final float indexY) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20 * index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //todo make velocity
                        AnimatorSet animatorSet = new AnimatorSet();
//                        float fixedIndex = (getXoffset(-index)) * -1;
                        animatorSet.play(ObjectAnimator.ofFloat(v, "translationX", indexX));
                        animatorSet.play(ObjectAnimator.ofFloat(v, "translationY", indexY));

                        // set the speed of items
                        animatorSet.setDuration(200);
                        animatorSet.start();
                    }
                });
            }
        }).start();


    }

    private float getYoffset(int index) {
        double eachAngle = getEachArcAngleInDegrees();
        double totalAngleForChild = eachAngle * (index);
        return (int) (halfScreen * Math.sin(Math.toRadians(totalAngleForChild)));
    }

    private float getXoffset(int index) {
        double eachAngle = getEachArcAngleInDegrees();
        double totalAngleForChild = eachAngle * (index);
        return (int) (halfScreen * Math.cos(Math.toRadians(totalAngleForChild)));
    }

    private double getEachArcAngleInDegrees() {
        if (buttonslist.size() == 1)
            return ANGLE_FOR_ONE_SUB_MENU;
        else
            return -90 / ((double) buttonslist.size() - 1);
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(activity, v.getTag() + "", Toast.LENGTH_SHORT).show();
    }

    public void createView() {


    }

}
