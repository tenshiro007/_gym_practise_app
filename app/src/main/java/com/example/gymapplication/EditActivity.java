package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity implements PlanAdapter.RemovePlan {

    private static final String TAG = "EditActivity";
    private TextView txtDay;
    private RecyclerView recyclerView;
    private Button btnAddPlan;

    private PlanAdapter adapter;

    @Override
    public void onRemovePlanResult(Plan plan) {
        if(Utils.removePlan(plan)){
            Toast.makeText(this, "Removed successfully", Toast.LENGTH_SHORT).show();
            adapter.setPlans(getPlanByDay(plan.getDay()));
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,PlanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
//        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initView();


        adapter=new PlanAdapter(this);

        adapter.setType("edit");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent=getIntent();
        if(null !=intent){
            String day=intent.getStringExtra("day");
            if(null !=day){
                txtDay.setText(day);

                ArrayList<Plan>plans=getPlanByDay(day);

                adapter.setPlans(plans);
            }
        }
        btnAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPlanIntent=new Intent(EditActivity.this,AllTrainingActivity.class);
                startActivity(addPlanIntent);
            }
        });
    }

    private ArrayList<Plan> getPlanByDay (String day){
        ArrayList<Plan>allplans=Utils.getPlans();
        ArrayList<Plan>plans=new ArrayList<>();
        for(Plan a:allplans){
            if (a.getDay().equalsIgnoreCase(day)){
                plans.add(a);
            }
        }
        return plans;
    }

    private void initView() {
        Log.d(TAG,"initView :started");
        txtDay=findViewById(R.id.txtDay);
        recyclerView=findViewById(R.id.recyclerView);
        btnAddPlan=findViewById(R.id.btnaddPlan);
    }
}