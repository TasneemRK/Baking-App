package bakingapp.android.com.bakingapp.Widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class RecipService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeWidgetFactory(this.getApplicationContext());
    }
}
