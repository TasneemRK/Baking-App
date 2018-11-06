package bakingapp.android.com.bakingapp.Arch_Comp;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import bakingapp.android.com.bakingapp.Models.Recipe;

public class MutableRecipeViewModel extends ViewModel {

    MutableLiveData<List<Recipe>> mutableLiveData;

    public MutableLiveData<List<Recipe>> getMutableLiveData() {
        if (mutableLiveData == null)
            mutableLiveData = new MutableLiveData<List<Recipe>>();
        return mutableLiveData;
    }

    public void setMutableLiveData(MutableLiveData<List<Recipe>> mutableLiveData) {
        this.mutableLiveData = mutableLiveData;
    }
}
