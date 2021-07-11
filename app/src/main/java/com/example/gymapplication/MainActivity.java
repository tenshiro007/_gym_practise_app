package com.example.gymapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.util.Util;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity" ;
    private Button btnPlan,btnAllActivity,btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        Utils.initTrainings();
        // TODO: 7/4/2021 Create onClicklisteners

        btnAllActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,AllTrainingActivity.class);
                startActivity(intent);
            }
        });
        btnPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PlanActivity.class);
                startActivity(intent);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this)
                        .setTitle("About")
                        .setMessage("Created by Tenshiro \n" +
                                "Visit for more")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO: 7/5/2021  Open website
                                Intent intent=new Intent(MainActivity.this,WebsiteActivity.class);
                                startActivity(intent);
                            }
                        });
                builder.create().show();
            }
        });
    }

    private void initView() {
        Log.d(TAG,"initViews : started");
        btnPlan=findViewById(R.id.btnSeeYourPlan);
        btnAllActivity=findViewById(R.id.btnSeeAllActivity);
        btnAbout=findViewById(R.id.btnAboutUs);
    }
}