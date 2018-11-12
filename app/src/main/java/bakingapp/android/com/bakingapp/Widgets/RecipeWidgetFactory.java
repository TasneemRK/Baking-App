package bakingapp.android.com.bakingapp.Widgets;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.List;
import java.util.Set;

import bakingapp.android.com.bakingapp.Models.Integredients;
import bakingapp.android.com.bakingapp.R;
import bakingapp.android.com.bakingapp.Utils.Constants;
import bakingapp.android.com.bakingapp.Utils.ConvertToDataset;

public class RecipeWidgetFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    List<Integredients> integredients;

    public RecipeWidgetFactory(Context context) {
        this.context = context;

    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.INTEGREDIENT_DATA_PREFERENCE_FILE), Context.MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet(Constants.SHAERS_KEY, null);
        if (set != null) {
            integredients = ConvertToDataset.convertToList(set);
            if (integredients != null) {
                Log.d("widgetwidgetwidget",integredients.size()+"");
            }else {
                Log.d("widgetwidgetwidget","empty integ");
            }
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (integredients != null)
        return integredients.size();
        return 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        remoteViews.setTextViewText(R.id.widget_quantity, integredients.get(position).getQuantity() + "");
        remoteViews.setTextViewText(R.id.widget_measure, integredients.get(position).getMeasure());
        remoteViews.setTextViewText(R.id.widget_ingredient, integredients.get(position).getIngredient());
        Bundle bundle = new Bundle();
        bundle.putParcelable("int_parc", integredients.get(position));
        Intent intent = new Intent();
        intent.putExtras(bundle);
        remoteViews.setOnClickFillInIntent(R.id.widget_item, intent);
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
