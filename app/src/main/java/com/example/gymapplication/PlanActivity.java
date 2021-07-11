package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {

    private static final String TAG ="PlanActivity" ;
    private TextView monEdit,tuesEdit,webEdit,thEdit,friEdit,satEdit,sunEdit;
    private RecyclerView monRecy,tuRecy,wedRecy,thRecy,friRecy,saRecy,sunRecy;
    private RelativeLayout noPlan;
    private NestedScrollView nestedScrollView;
    private Button btnAdd;

    private PlanAdapter monAdapter,tuAdapter,webAdapter,thAdapter,friAdapter,satAdapter,suAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        initView();
        ArrayList<Plan>plans=Utils.getPlans();

        if(null !=plans){
            if(plans.size()>0){
                noPlan.setVisibility(View.GONE);
                nestedScrollView.setVisibility(View.VISIBLE);
                // TODO: 7/5/2021 Plan recyclerviewAdapter
                initRecyclerView();
                setEditOnclickListeners();

            }else{
                noPlan.setVisibility(View.VISIBLE);
                nestedScrollView.setVisibility(View.GONE);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(PlanActivity.this,AllTrainingActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                });
            }

        }else{
            noPlan.setVisibility(View.VISIBLE);
            nestedScrollView.setVisibility(View.GONE);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(PlanActivity.this,AllTrainingActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            });
        }
    }

    private void setEditOnclickListeners() {
        Intent intent=new Intent(this,EditActivity.class);
        monEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day","Monday");
                startActivity(intent);
            }
        });
        tuesEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day","Tuesday");
                startActivity(intent);
            }
        });
        webEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day","Wednesday");
                startActivity(intent);
            }
        });
        thEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day","Thursday");
                startActivity(intent);
            }
        });
        friEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day","Friday");
                startActivity(intent);
            }
        });
        satEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day","Saturday");
                startActivity(intent);
            }
        });
        sunEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("day","Sunday");
                startActivity(intent);
            }
        });
    }

    private void initRecyclerView() {
        Log.d(TAG,"initRecyclerView :started");
        monAdapter=new PlanAdapter(this);
        monRecy.setAdapter(monAdapter);
        monRecy.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        monAdapter.setPlans(getPlanByDay("Monday"));

        tuAdapter=new PlanAdapter(this);
        tuRecy.setAdapter(tuAdapter);
        tuRecy.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        tuAdapter.setPlans(getPlanByDay("Tuesday"));

        webAdapter=new PlanAdapter(this);
        wedRecy.setAdapter(webAdapter);
        wedRecy.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        webAdapter.setPlans(getPlanByDay("Wednesday"));

        thAdapter=new PlanAdapter(this);
        thRecy.setAdapter(thAdapter);
        thRecy.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        thAdapter.setPlans(getPlanByDay("Thursday"));

        friAdapter=new PlanAdapter(this);
        friRecy.setAdapter(friAdapter);
        friRecy.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        friAdapter.setPlans(getPlanByDay("Friday"));

        satAdapter=new PlanAdapter(this);
        saRecy.setAdapter(satAdapter);
        saRecy.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        satAdapter.setPlans(getPlanByDay("Saturday"));

        suAdapter=new PlanAdapter(this);
        sunRecy.setAdapter(suAdapter);
        sunRecy.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        suAdapter.setPlans(getPlanByDay("Sunday"));
    }
    private ArrayList<Plan>getPlanByDay (String day){
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
        Log.d(TAG,"initview :started");
        monEdit=findViewById(R.id.mondayEdit);
        tuesEdit=findViewById(R.id.tuesdayEdit);
        webEdit=findViewById(R.id.wedEdit);
        thEdit=findViewById(R.id.thursdayEdit);
        friEdit=findViewById(R.id.fridayEdit);
        satEdit=findViewById(R.id.saturdayEdit);
        sunEdit=findViewById(R.id.sundayEdit);

        monRecy=findViewById(R.id.mondayRecyclerview);
        tuRecy=findViewById(R.id.tuesdayRecyclerview);
        wedRecy=findViewById(R.id.wedRecyclerview);
        thRecy=findViewById(R.id.ThursdayRecyclerview);
        friRecy=findViewById(R.id.FridayRecyclerview);
        saRecy=findViewById(R.id.saturdayRecyclerview);
        sunRecy=findViewById(R.id.sundayRecyclerview);

        noPlan=findViewById(R.id.noPlanLayout);

        nestedScrollView=findViewById(R.id.netedScrollview);

        btnAdd=findViewById(R.id.btnAddplan);
    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

//        super.onBackPressed();
    }
}