package bakingapp.android.com.bakingapp.Widgets;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import bakingapp.android.com.bakingapp.Models.Recipe;
import bakingapp.android.com.bakingapp.R;
import bakingapp.android.com.bakingapp.Utils.Constants;
import bakingapp.android.com.bakingapp.Utils.ConvertToDataset;

public class RecipeWidgetFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private List<Recipe> recipes;

    public RecipeWidgetFactory(Context context) {
        this.context = context;

    }

    @Override
    public void onCreate() {
//        recipes = Requests.GetRequest(context);

    }

    @Override
    public void onDataSetChanged() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.RECIPE_DATA_PREFERENCE_FILE), Context.MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet(Constants.SHAERS_KEY,null);
        if (set != null){
            recipes = ConvertToDataset.convertToList(set);
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
       return recipes.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        remoteViews.setTextViewText(R.id.widget_item_name,recipes.get(position).getName());
        remoteViews.setTextViewText(R.id.widget_item_servings,"Servings : " + recipes.get(position).getServings()+"");
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        remoteViews.setOnClickFillInIntent(R.id.widget_item_btn,intent);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
