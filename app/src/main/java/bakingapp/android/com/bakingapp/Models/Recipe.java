package bakingapp.android.com.bakingapp.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe implements Parcelable {

    @PrimaryKey
    @NonNull
    private int id;
    private String name;

    //    @TypeConverters(DataTypeConverters.class)
    @SerializedName("integrediets")
    @Expose
    public List<Integredients> integredientsList = null;

    //    @TypeConverters(DataTypeConverters.class)
    @SerializedName("steps")
    @Expose
    public List<Steps> stepsList = null;

    private int servings;
    private String image;

    @Ignore
    public Recipe() {
    }

    @Ignore
    public Recipe(int id, String name, int servings, String image) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
    }

    public Recipe(int id, String name, List<Integredients> integredientsList, List<Steps> stepsList, int servings, String image) {
        this.id = id;
        this.name = name;
        this.integredientsList = integredientsList;
        this.stepsList = stepsList;
        this.servings = servings;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integredients> getIntegredients() {
        if (integredientsList == null)
            integredientsList = new ArrayList<>();
        return integredientsList;
    }

    public void setIntegredients(List<Integredients> integredientsList) {
        this.integredientsList = integredientsList;
    }

    public List<Steps> getSteps() {
        if (stepsList == null)
            stepsList = new ArrayList<>();
        return stepsList;
    }

    public void setSteps(List<Steps> stepsList) {
        this.stepsList = stepsList;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeTypedList(this.integredientsList);
        dest.writeTypedList(this.stepsList);
        dest.writeInt(this.servings);
        dest.writeString(this.image);
    }

    protected Recipe(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.integredientsList = in.createTypedArrayList(Integredients.CREATOR);
        this.stepsList = in.createTypedArrayList(Steps.CREATOR);
        this.servings = in.readInt();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
