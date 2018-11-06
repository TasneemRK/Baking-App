package bakingapp.android.com.bakingapp.Arch_Comp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import bakingapp.android.com.bakingapp.Models.Recipe;

@Database(entities = {Recipe.class},version = 1,exportSchema = false)
@TypeConverters(DataTypeConverters.class)
public abstract class RecipeRoom extends RoomDatabase {

    public static final Object LOCK = new Object();
    public static final String DB_NAME = "Recipe_DB";
    public static  RecipeRoom instance;
    public abstract RecipeDao recipeDao();


    public static RecipeRoom getInstance(Context context){
        if (instance == null){
            synchronized (LOCK){
                instance = Room.databaseBuilder(context.getApplicationContext(),RecipeRoom.class,DB_NAME).build();
            }
        }
        return instance;
    }



}
