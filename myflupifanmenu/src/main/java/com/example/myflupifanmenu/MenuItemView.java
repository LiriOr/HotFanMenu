package com.example.myflupifanmenu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuItemView extends LinearLayout {

    private Context context;
    private TextView tvTitle;
    private CircleImageView imgaeCircle;


    public MenuItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MenuItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    public MenuItemView(Context context) {
        super(context);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.menu_item, MenuItemView.this);
        setParams(view);
        initViews(view);


    }

    private void initViews(View view) {
        tvTitle = view.findViewById(R.id.tv_menuView);
        imgaeCircle = view.findViewById(R.id.cvImage_menuView);


    }

    private void setParams(View view) {
        RelativeLayout.LayoutParams par = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        par.setMargins(15, 15, 15, 15);
        par.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        par.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        view.setLayoutParams(par);
    }


    public TextView getTvTitle() {
        return tvTitle;
    }

    public CircleImageView getImgaeCircle() {
        return imgaeCircle;
    }

    public void moveTitleToLeft(int num) {
            int pushLeft = num * -10;
            tvTitle.setTranslationX(pushLeft);

    }
}
