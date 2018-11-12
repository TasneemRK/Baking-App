package bakingapp.android.com.bakingapp.Frags;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import bakingapp.android.com.bakingapp.Adapters.IntegredientAdapter;
import bakingapp.android.com.bakingapp.Adapters.StepAdapter;
import bakingapp.android.com.bakingapp.Models.Integredients;
import bakingapp.android.com.bakingapp.Models.Recipe;
import bakingapp.android.com.bakingapp.Models.Steps;
import bakingapp.android.com.bakingapp.R;
import bakingapp.android.com.bakingapp.Utils.Constants;
import bakingapp.android.com.bakingapp.Utils.ConvertToDataset;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntegredientsFragment extends Fragment{

    @BindView(R.id.integ_recycleview)
    RecyclerView integ_recycle;

    @BindView(R.id.steps_recycleview)
    RecyclerView steps_recycle;

    List<Integredients> integredients;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.integredient_fragment,null);

        ButterKnife.bind(this,view);

        Intent intent = getActivity().getIntent();
        Recipe recipe =intent.getParcelableExtra(Constants.RECIPE_PARCABLE);

        sharedPreferences = getContext().getSharedPreferences(getString(R.string.INTEGREDIENT_DATA_PREFERENCE_FILE), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        SetupIntegerRecycle(recipe);
        SetupStepsRecycle(recipe);

        return view;
    }

    private void SetupStepsRecycle(Recipe recipe) {
        List<Steps> steps = recipe.getSteps();
        StepAdapter stepAdapter = new StepAdapter(getContext());
        stepAdapter.setStepsList(steps);
        steps_recycle.setAdapter(stepAdapter);
        steps_recycle.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    private void SetupIntegerRecycle(Recipe recipe) {
        integredients = recipe.getIntegredients();
        IntegredientAdapter adapter = new IntegredientAdapter(getContext());
        adapter.setIntegredients(integredients);
        integ_recycle.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        integ_recycle.setLayoutManager(layoutManager);
    }

    @OnClick(R.id.addToWidget)
    void setAddToWidget(){
        editor.clear();
        editor.putStringSet(Constants.SHAERS_KEY, ConvertToDataset.convertToSet(integredients));
        Log.d("setset",sharedPreferences.getStringSet(Constants.SHAERS_KEY,null).toString());
        Toast.makeText(getContext(), "add to widget", Toast.LENGTH_SHORT).show();
        boolean tt = editor.commit();
        Log.d("setset11",tt+"");
    }
}
