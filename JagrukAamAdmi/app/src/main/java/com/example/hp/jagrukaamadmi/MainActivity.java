package com.example.hp.jagrukaamadmi;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private  static  int SPLASH_SCREEN = 3000;

    Animation topAnim;  //variables
    ImageView image;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        //animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);


        //hooks
        image = findViewById(R.id.imageView);
        image.setAnimation(topAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Login_Activity.class);
                Pair[] pairs = new Pair[2];
                pairs[0]  = new Pair<View, String>(image , "logo_image");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs[0]);
                startActivity(intent ,options.toBundle());
                finish();
            }
        }, SPLASH_SCREEN);
    }
}
