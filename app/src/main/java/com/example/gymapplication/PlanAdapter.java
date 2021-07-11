package com.example.gymapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import static com.example.gymapplication.TrainingActivity.TRAINING_KEY;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {
    public interface RemovePlan{
        void onRemovePlanResult(Plan plan);
    }
    private static final String TAG = "PlanAdapter";

    private RemovePlan removePlan;



    private ArrayList<Plan> plans = new ArrayList<>();
    private Context context;
    private String type = "";



    public PlanAdapter(Context context) {
        this.context = context;
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
    }

    @NonNull





    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder :started");
        holder.txtName.setText(plans.get(position).getTraining().getName());
//        holder.txtminutes.setText(plans.get(position).getMinutes());
        holder.txtminutes.setText(String.valueOf(plans.get(position).getMinutes()));
        holder.txtDescript.setText(plans.get(position).getTraining().getShortDesc());

        Glide.with(context).asBitmap()
                .load(plans.get(position).getTraining().getImgUrl())
                .into(holder.trainingImage);

        if (plans.get(position).isAccomplished()) {
            holder.checkCircle.setVisibility(View.VISIBLE);
            holder.emptyCircle.setVisibility(View.GONE);


        } else {
            holder.checkCircle.setVisibility(View.GONE);
            holder.emptyCircle.setVisibility(View.VISIBLE);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrainingActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(TRAINING_KEY, plans.get(position).getTraining());
                context.startActivity(intent);
            }
        });


        if (type.equals("edit")) {
            holder.emptyCircle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Finshed")
                            .setMessage("Have you finished" + plans.get(position).getTraining().getName() + " ? ")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for (Plan a : Utils.getPlans()) {
                                        if (a.equals(plans.get(position))) {
                                            a.setAccomplished(true);

                                        }
                                    }
                                    notifyDataSetChanged();
                                }
                            });
                    builder.create().show();
                }
            });

            holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Remove")
                            .setMessage("Are you want to delete " + plans.get(position).getTraining().getName() + " ?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    if (Utils.removePlan(plans.get(position))) {
//                                        Toast.makeText(context, "Plan Removed Successfully", Toast.LENGTH_SHORT).show();
//                                        notifyDataSetChanged();
//                                    }


                                    try{
                                        removePlan =(RemovePlan) context;
                                        removePlan.onRemovePlanResult(plans.get(position));
                                    }catch (ClassCastException e){
                                        e.printStackTrace();
                                    }
                                                                    }
                            });
                    builder.create().show();
                    return true;
                }
            });


        }
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public int getItemCount() {
        return plans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtminutes, txtDescript;
        private ImageView trainingImage, checkCircle, emptyCircle;
        private MaterialCardView parent;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtminutes = itemView.findViewById(R.id.txtMinutes);
            txtDescript = itemView.findViewById(R.id.txtDescription);

            parent = itemView.findViewById(R.id.parent);

            trainingImage = itemView.findViewById(R.id.trainingImage);
            checkCircle = itemView.findViewById(R.id.checkedCircle);
            emptyCircle = itemView.findViewById(R.id.emptyCircle);

        }
    }
}
