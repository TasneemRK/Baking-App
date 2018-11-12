package bakingapp.android.com.bakingapp.Activites;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bakingapp.android.com.bakingapp.Adapters.RecipeAdapter;
import bakingapp.android.com.bakingapp.Models.Integredients;
import bakingapp.android.com.bakingapp.Models.Recipe;
import bakingapp.android.com.bakingapp.Models.Steps;
import bakingapp.android.com.bakingapp.R;
import bakingapp.android.com.bakingapp.Utils.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private List<Recipe> recipes;
    private RecipeAdapter adapter;
//    private RecipeModelView modelView;

    @BindView(R.id.recipeRecycleView)
    RecyclerView recipe_recycleview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        modelView = ViewModelProviders.of(this).get(RecipeModelView.class);




        ButterKnife.bind(this);

        SetupRecipeRecycle();

        GetRequest();

    }

    public List<Recipe> GetRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Constants.RECIPE_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject result = response.getJSONObject(i);

                        int id = result.getInt(Constants.ID);
                        String name = result.getString(Constants.NAME);
                        int servings = result.getInt(Constants.SERVINGS);
                        String image = result.getString(Constants.IMAGE);
                        JSONArray ingredients = result.getJSONArray(Constants.INTEGREDIENTS);
                        List<Integredients> integredientsList = new ArrayList<>();
                        for (int j = 0; j < ingredients.length(); j++) {
                            JSONObject ingredientsObject = ingredients.getJSONObject(j);
                            int quantity = ingredientsObject.getInt(Constants.QUANTITY);
                            String measure = ingredientsObject.getString(Constants.MEASURE);
                            String ingredient = ingredientsObject.getString(Constants.INTEGREDIENT);
                            Integredients integredients = new Integredients(quantity, measure, ingredient);
                            integredientsList.add(integredients);
                        }
                        JSONArray steps = result.getJSONArray(Constants.STEPS);
                        List<Steps> stepsList = new ArrayList<>();
                        for (int x = 0; x < steps.length(); x++) {
                            JSONObject step_object = steps.getJSONObject(x);
                            int step_id = step_object.getInt(Constants.ID);
                            String shortDescription = step_object.getString(Constants.SHORT_DESCRIPTION);
                            String description = step_object.getString(Constants.DESCRIPTION);
                            String videoURL = step_object.getString(Constants.VIDEO_URL);
                            String thumbnailURL = step_object.getString(Constants.THUMBNAIL_URL);
                            Steps steps1 = new Steps(step_id, shortDescription, description, videoURL, thumbnailURL);
                            stepsList.add(steps1);
                        }

                        Recipe recipe = new Recipe(id, name, integredientsList, stepsList, servings, image);
                        recipes.add(recipe);
//                        modelView.insertRecipe(recipe);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.setRecipes(recipes);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = checkInternet(error);
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

            }

        });
        queue.add(request);
        return recipes;
    }

    private String checkInternet(VolleyError error) {
        String message = null;
        if (error instanceof NetworkError) {
            message = "Cannot connect to Internet...Please check your connection!";
        } else if (error instanceof ServerError) {
            message = "The server could not be found. Please try again after some time!!";
        } else if (error instanceof AuthFailureError) {
            message = "Cannot connect to Internet...Please check your connection!";
        } else if (error instanceof ParseError) {
            message = "Parsing error! Please try again after some time!!";
        } else if (error instanceof NoConnectionError) {
            message = "Cannot connect to Internet...Please check your connection!";
        } else if (error instanceof TimeoutError) {
            message = "Connection TimeOut! Please check your internet connection.";
        }
        return message;
    }

    private void SetupRecipeRecycle() {
        recipes = new ArrayList<>();
        adapter = new RecipeAdapter(this);
        adapter.setRecipes(recipes);
        recipe_recycleview.setAdapter(adapter);

        if (getResources().getBoolean(R.bool.isTablet)) {
            GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
            recipe_recycleview.setLayoutManager(layoutManager);
        } else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recipe_recycleview.setLayoutManager(layoutManager);
        }
    }
}
