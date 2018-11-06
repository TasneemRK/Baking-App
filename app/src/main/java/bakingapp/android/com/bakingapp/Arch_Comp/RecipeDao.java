package bakingapp.android.com.bakingapp.Arch_Comp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import bakingapp.android.com.bakingapp.Models.Integredients;
import bakingapp.android.com.bakingapp.Models.Recipe;

@Dao
public interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addToRecipe(Recipe recipe);

    @Query("select * from Recipe")
    LiveData<List<Recipe>> loadAllIRecipes();

}
