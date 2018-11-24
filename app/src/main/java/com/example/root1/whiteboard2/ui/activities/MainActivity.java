package com.example.root1.whiteboard2.ui.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.root1.whiteboard2.R;
import com.example.root1.whiteboard2.ui.fragments.*;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private FloatingActionButton fab;
    private boolean isPlus = true;
    private Context context;
    private boolean fabExpanded = false;
    //Linear layout holding the Save submenu
    private RelativeLayout layoutFabSave;

    //Linear layout holding the Edit submenu
    private LinearLayout layoutFabEdit;
    private LinearLayout layoutFabPhoto;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Fragment fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_orders:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_chat:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_profile);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new HomeFragment();
        loadFragment(fragment);
        mTextMessage = (TextView) findViewById(R.id.message);
        context = getApplicationContext();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        //fab = (FloatingActionButton) this.findViewById(R.id.fabSetting);

        //iew view = findViewById(R.id.fabFrame);

        layoutFabSave = (RelativeLayout) findViewById(R.id.layoutFabSave);
//        layoutFabEdit = (LinearLayout) this.findViewById(R.id.layoutFabEdit);
//        layoutFabPhoto = (LinearLayout) this.findViewById(R.id.layoutFabPhoto);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fabExpanded == true){
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
//                fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorFabRed)));
                fab.setRippleColor(ContextCompat.getColor(context, R.color.colorBackround));


                if(isPlus)
                {
                    fab.setImageResource(R.drawable.avd_plus_to_cross);
//                    fab.setRippleColor(ContextCompat.getColor(context, R.color.colorFabRed));
                    fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorFabRed)));


                }

                else
                {
                    fab.setImageResource(R.drawable.avd_cross_to_plus);
                    //fab.setRippleColor(ContextCompat.getColor(context, R.color.colorFabRed));
                    fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorPrimaryDark)));


                }


                Drawable drawable = fab.getDrawable();
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }
                isPlus = !isPlus;

            }
        });
        closeSubMenusFab();

    }

    private void loadFragment(Fragment fragment)
    {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //closes FAB submenus
    private void closeSubMenusFab(){
        layoutFabSave.setVisibility(View.INVISIBLE);

         //View v = findViewById(R.id.semiTransParentFill);
         //v.setVisibility(View.INVISIBLE);
       // layoutFabEdit.setVisibility(View.INVISIBLE);
        //layoutFabPhoto.setVisibility(View.INVISIBLE);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab(){
        layoutFabSave.setVisibility(View.VISIBLE);
        //View v = findViewById(R.id.semiTransParentFill);
       // v.setVisibility(View.VISIBLE);

        //Dialog dialog=new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        //dialog.setContentView(R.layout.layout_plus_fab_submenue);
        //dialog.show();

        //layoutFabEdit.setVisibility(View.VISIBLE);
        //layoutFabPhoto.setVisibility(View.VISIBLE);
        //Change settings icon to 'X' icon
        fabExpanded = true;
    }

}
