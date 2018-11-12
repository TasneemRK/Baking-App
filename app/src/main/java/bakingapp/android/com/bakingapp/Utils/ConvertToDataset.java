package bakingapp.android.com.bakingapp.Utils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import bakingapp.android.com.bakingapp.Models.Integredients;
import bakingapp.android.com.bakingapp.Models.Recipe;

public class ConvertToDataset {

    public static Set<String> convertToSet(List<Integredients> integredients){
        Set<String> dataSet = new HashSet<>();
        Gson gson = new Gson();
        for (int i = 0; i < integredients.size(); i++) {
            Integredients integredients1 = integredients.get(i);
            String json =gson.toJson(integredients1);
            dataSet.add(json);
        }
        return dataSet;
    }


    public static List<Integredients> convertToList(Set<String> dataSet){
        List<Integredients> integredients = new ArrayList<>();
        Gson gson = new Gson();
            Iterator<String> iterator;
            String response;
            if (dataSet != null){
                iterator = dataSet.iterator();
                while (iterator.hasNext()){
                    response = iterator.next();
                    Integredients integredients1 = gson.fromJson(response,Integredients.class);
                    integredients.add(integredients1);
                }


        }

        return integredients;
    }
}
