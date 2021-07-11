package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class TrainingActivity extends AppCompatActivity implements PlanDetailDialog.PassPlanInterface{

    private static final String TAG ="TrainingActivity" ;
    private Button btnAddPlan;
    private TextView txtName,txtDesc;
    private ImageView image;

    @Override
    public void getPlan(Plan plan) {
        Log.d(TAG,"getPlan : Plan"+plan.toString());
        if(Utils.addPlan(plan)){
            Toast.makeText(this, plan.getTraining().getName()+" Add To Your Plan", Toast.LENGTH_SHORT).show();
            // TODO: 7/5/2021 Navigate user to plan 
            Intent intent=new Intent(this,PlanActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    public static final String TRAINING_KEY="training";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);


        initView();
        Intent intent =getIntent();
        if(null !=intent){
            Training training=intent.getParcelableExtra(TRAINING_KEY);
            if(null !=training){
                txtName.setText(training.getName());
                txtDesc.setText(training.getLongDesc());
                Glide.with(this).asBitmap()
                        .load(training.getImgUrl())
                        .into(image);

                btnAddPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 7/4/2021 show the dialog
                        PlanDetailDialog dialog=new PlanDetailDialog();
                        Bundle bundle=new Bundle();
                        bundle.putParcelable(TRAINING_KEY,training);
                        dialog.setArguments(bundle);
                        dialog.show(getSupportFragmentManager(),"plan detail dialog");
                    }
                });
            }
        }

    }

    private void initView() {
    btnAddPlan=findViewById(R.id.btnAddToPlan);
    txtName=findViewById(R.id.txtName);
    txtDesc=findViewById(R.id.txtDescription);
    image=findViewById(R.id.image);
    }
}