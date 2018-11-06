package bakingapp.android.com.bakingapp.Models;

import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;

public class Integredients implements Parcelable {

    private int quantity;
    private String ingredient;
    private String measure;

    public Integredients(int quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.ingredient = ingredient;
        this.measure = measure;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.quantity);
        dest.writeString(this.ingredient);
        dest.writeString(this.measure);
    }

    protected Integredients(Parcel in) {
        this.quantity = in.readInt();
        this.ingredient = in.readString();
        this.measure = in.readString();
    }

    public static final Parcelable.Creator<Integredients> CREATOR = new Parcelable.Creator<Integredients>() {
        @Override
        public Integredients createFromParcel(Parcel source) {
            return new Integredients(source);
        }

        @Override
        public Integredients[] newArray(int size) {
            return new Integredients[size];
        }
    };
}
