package bakingapp.android.com.bakingapp.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import bakingapp.android.com.bakingapp.R;

public class SplashScreen extends AppCompatActivity {

    private static int DURATION = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();

            }

        }, DURATION);


    }
}
