package bakingapp.android.com.bakingapp.Arch_Comp;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import bakingapp.android.com.bakingapp.Models.Integredients;
import bakingapp.android.com.bakingapp.Models.Steps;

public class DataTypeConverters implements Serializable{

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Integredients> ToIntegerList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Integredients>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ToIntegerString(List<Integredients> data) {
        return gson.toJson(data);
    }

    @TypeConverter
    public static List<Steps> ToStepsList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Steps>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ToStepsString(List<Steps> data) {
        return gson.toJson(data);
    }

}
