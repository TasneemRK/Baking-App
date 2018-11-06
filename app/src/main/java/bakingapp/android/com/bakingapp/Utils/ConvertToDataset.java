package bakingapp.android.com.bakingapp.Utils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import bakingapp.android.com.bakingapp.Models.Recipe;

public class ConvertToDataset {

    public static Set<String> convertToSet(List<Recipe> recipes){
        Set<String> dataSet = new HashSet<>();
        Gson gson = new Gson();
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            String json =gson.toJson(recipe);
            dataSet.add(json);
        }
        return dataSet;
    }


    public static List<Recipe> convertToList(Set<String> dataSet){
        List<Recipe> recipes = new ArrayList<>();
        Gson gson = new Gson();
            Iterator<String> iterator;
            String response;
            if (dataSet != null){
                iterator = dataSet.iterator();
                while (iterator.hasNext()){
                    response = iterator.next();
                    Recipe recipe = gson.fromJson(response,Recipe.class);
                    recipes.add(recipe);
                }


        }

        return recipes;
    }
}
