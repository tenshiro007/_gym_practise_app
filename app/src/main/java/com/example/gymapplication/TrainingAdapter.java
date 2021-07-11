package com.example.gymapplication;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import static com.example.gymapplication.TrainingActivity.TRAINING_KEY;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder> {
    private static final String TAG = "TrainingAdapter";
    private ArrayList<Training>trainings=new ArrayList<>();
    private Context context;

    public TrainingAdapter(Context context) {
        this.context = context;
    }

    public void setTrainings(ArrayList<Training> trainings) {
        this.trainings = trainings;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private MaterialCardView parent;
        private ImageView image;
        private TextView name,desctiption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.txtName);
            desctiption=itemView.findViewById(R.id.txtDescription);
        }
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.training_items,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull  TrainingAdapter.ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder : Called");
        holder.name.setText(trainings.get(position).getName());
        holder.desctiption.setText(trainings.get(position).getShortDesc());
        Glide.with(context).asBitmap()
                .load(trainings.get(position).getImgUrl())
                .into(holder.image);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 7/4/2021 Navigate the user
                Intent intent =new Intent(context,TrainingActivity.class);
                intent.putExtra(TRAINING_KEY,trainings.get(position));
                context.startActivity(intent);
//                Toast.makeText(context, "Yet to be completed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }
}
