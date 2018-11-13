package bakingapp.android.com.bakingapp.Activites;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import bakingapp.android.com.bakingapp.Frags.IntegredientsFragment;
import bakingapp.android.com.bakingapp.Frags.StepsFragment;
import bakingapp.android.com.bakingapp.R;
import bakingapp.android.com.bakingapp.Utils.FragmentsUtil;

public class RecipeSteps extends AppCompatActivity {

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        if (savedInstanceState == null) {
            if ((getResources().getBoolean(R.bool.isTablet))) {
                fragment = new StepsFragment();
                FragmentsUtil.addFragment(this, R.id.my_container, fragment, false);
            } else {
                fragment = new IntegredientsFragment();
                FragmentsUtil.addFragment(this, R.id.my_container, fragment, false);
            }
        } else {
            fragment = getSupportFragmentManager().getFragment(savedInstanceState, "myFragment");


        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fragment != null) {
            if (fragment.isAdded()) {
                getSupportFragmentManager().putFragment(outState, "myFragment", fragment);
                Log.d("fffffffff", fragment.toString());
            }
        }
    }


}
