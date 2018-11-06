package bakingapp.android.com.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bakingapp.android.com.bakingapp.Activites.RecipeSteps;
import bakingapp.android.com.bakingapp.Frags.StepsFragment;
import bakingapp.android.com.bakingapp.Models.Steps;
import bakingapp.android.com.bakingapp.R;
import bakingapp.android.com.bakingapp.Utils.Constants;
import bakingapp.android.com.bakingapp.Utils.FragmentsUtil;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder>{

    private Context context;
    private List<Steps>stepsList;


    public StepAdapter(Context context) {
        this.context = context;
    }

    public void setStepsList(List<Steps> stepsList){
        this.stepsList = stepsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.step_item,null);
        StepViewHolder holder = new StepViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder stepViewHolder, int i) {
                stepViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        if (stepsList == null) return 0;
        return stepsList.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {

        TextView step_id;
        TextView step_descrip;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            step_id = itemView.findViewById(R.id.step_id);
            step_descrip = itemView.findViewById(R.id.step_descrip);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    StepsFragment fragment = new StepsFragment();
                    bundle.putParcelable(Constants.STEP_PARCABLE, stepsList.get(getAdapterPosition()));
                    fragment.setArguments(bundle);
                    if (context.getResources().getBoolean(R.bool.isTablet)){
                        FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.steps_frag, fragment, false);
                    }else {
                        FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.my_container, fragment, true);
                    }
                }
            });
        }

        public void bind(int position){
            step_id.setText(stepsList.get(position).getId()+"");
            step_descrip.setText(stepsList.get(position).getShortDescription()+"");
        }


    }


}
