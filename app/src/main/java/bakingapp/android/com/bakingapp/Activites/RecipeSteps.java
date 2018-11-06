package bakingapp.android.com.bakingapp.Activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Set;

import bakingapp.android.com.bakingapp.Frags.IntegredientsFragment;
import bakingapp.android.com.bakingapp.Frags.StepsFragment;
import bakingapp.android.com.bakingapp.R;
import bakingapp.android.com.bakingapp.Utils.Constants;
import bakingapp.android.com.bakingapp.Utils.FragmentsUtil;

public class RecipeSteps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);


        if ((getResources().getBoolean(R.bool.isTablet))) {
            FragmentsUtil.addFragment(this, R.id.steps_frag, new StepsFragment(), false);
        }else {
            FragmentsUtil.addFragment(this, R.id.my_container, new IntegredientsFragment(), false);
        }

    }
}
