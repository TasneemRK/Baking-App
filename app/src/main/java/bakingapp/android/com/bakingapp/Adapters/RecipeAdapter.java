package bakingapp.android.com.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bakingapp.android.com.bakingapp.Models.Recipe;
import bakingapp.android.com.bakingapp.Activites.RecipeSteps;
import bakingapp.android.com.bakingapp.R;
import bakingapp.android.com.bakingapp.Utils.Constants;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context context;
    private List<Recipe> recipes;

    public RecipeAdapter(Context context) {
        this.context = context;
    }

    public void setRecipes(List<Recipe>recipes){
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_item, null);
        RecipeViewHolder holder = new RecipeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int position) {
        recipeViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (recipes != null){
        return recipes.size();}
        else {
            return 0;
        }
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        CircleImageView recipe_image;
        TextView recipe_name;
        TextView recipe_servings;

        public RecipeViewHolder(@NonNull final View itemView) {
            super(itemView);
            recipe_image = itemView.findViewById(R.id.recipe_image);
            recipe_name = itemView.findViewById(R.id.recipe_name);
            recipe_servings = itemView.findViewById(R.id.recipe_servings);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, RecipeSteps.class);
                    intent.putExtra(Constants.RECIPE_PARCABLE,recipes.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }

        public void bind(int position) {
            if (!(recipes.get(position).getImage().equals(""))) {
                Picasso.get().load(recipes.get(position).getImage())
                        .error(R.drawable.icon)
                        .placeholder(R.drawable.icon)
                        .into(recipe_image);
            }else {
                Picasso.get().load(R.drawable.icon).fit().into(recipe_image);
            }
            recipe_name.setText(recipes.get(position).getName());
            recipe_servings.append(recipes.get(position).getServings()+"");
        }
    }

}
