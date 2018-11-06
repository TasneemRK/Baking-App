package bakingapp.android.com.bakingapp.Utils;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bakingapp.android.com.bakingapp.Activites.MainActivity;
import bakingapp.android.com.bakingapp.Models.Integredients;
import bakingapp.android.com.bakingapp.Models.Recipe;
import bakingapp.android.com.bakingapp.Models.Steps;

public class Requests {
    public static List<Recipe> GetRequest(final Context context) {
        final List<Recipe> recipes = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest request = new JsonArrayRequest(com.android.volley.Request.Method.GET, Constants.RECIPE_URL, null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0 ; i<response.length();i++){
                    try {
                        JSONObject result = response.getJSONObject(i);

                        int id = result.getInt(Constants.ID);
                        String name = result.getString(Constants.NAME);
                        int servings = result.getInt(Constants.SERVINGS);
                        String image = result.getString(Constants.IMAGE);
                        JSONArray ingredients = result.getJSONArray(Constants.INTEGREDIENTS);
                        List<Integredients> integredientsList = new ArrayList<>();
                        for (int j=0 ; j<ingredients.length();j++){
                            JSONObject ingredientsObject = ingredients.getJSONObject(j);
                            int quantity = ingredientsObject.getInt(Constants.QUANTITY);
                            String measure = ingredientsObject.getString(Constants.MEASURE);
                            String ingredient = ingredientsObject.getString(Constants.INTEGREDIENT);
                            Integredients integredients = new Integredients(quantity,measure,ingredient);
                            integredientsList.add(integredients);
                        }
                        JSONArray steps = result.getJSONArray(Constants.STEPS);
                        List<Steps>stepsList = new ArrayList<>();
                        for (int x=0; x<steps.length();x++){
                            JSONObject step_object = steps.getJSONObject(x);
                            int step_id = step_object.getInt(Constants.ID);
                            String shortDescription = step_object.getString(Constants.SHORT_DESCRIPTION);
                            String description = step_object.getString(Constants.DESCRIPTION);
                            String videoURL = step_object.getString(Constants.VIDEO_URL);
                            String thumbnailURL = step_object.getString(Constants.THUMBNAIL_URL);
                            Steps steps1 = new Steps(step_id,shortDescription,description,videoURL,thumbnailURL);
                            stepsList.add(steps1);
                        }

                        Recipe recipe = new Recipe(id,name,integredientsList,stepsList,servings,image);
                        recipes.add(recipe);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "no internet", Toast.LENGTH_SHORT).show();

            }

        });
        queue.add(request);
        return recipes;
    }
}
