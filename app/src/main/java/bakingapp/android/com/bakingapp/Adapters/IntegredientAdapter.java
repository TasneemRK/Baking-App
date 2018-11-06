package bakingapp.android.com.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bakingapp.android.com.bakingapp.Models.Integredients;
import bakingapp.android.com.bakingapp.R;

public class IntegredientAdapter extends RecyclerView.Adapter<IntegredientAdapter.IntegredientViewHolder>{

    private Context context;
    private List<Integredients> integredients;

    public IntegredientAdapter(Context context) {
        this.context = context;
    }

    public void setIntegredients(List<Integredients> integredients){
        this.integredients = integredients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IntegredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.integrediant_item,null);
        IntegredientViewHolder holder = new IntegredientViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull IntegredientViewHolder integredientViewHolder, int i) {
        integredientViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return integredients.size();
    }

    public class IntegredientViewHolder extends RecyclerView.ViewHolder {

        TextView integredient_quantity;
        TextView integredient_measure;
        TextView integredient_integredients;

        public IntegredientViewHolder(@NonNull View itemView) {
            super(itemView);
            integredient_quantity = itemView.findViewById(R.id.integ_quantity);
            integredient_measure = itemView.findViewById(R.id.integ_measure);
            integredient_integredients = itemView.findViewById(R.id.integ_ingredient);
        }

        public void bind(int position){
            integredient_quantity.setText(integredients.get(position).getQuantity()+"");
            integredient_measure.setText(integredients.get(position).getMeasure()+"");
            integredient_integredients.setText(integredients.get(position).getIngredient()+"");
        }
    }

}
