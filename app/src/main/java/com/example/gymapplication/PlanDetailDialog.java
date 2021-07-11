package com.example.gymapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import static com.example.gymapplication.TrainingActivity.TRAINING_KEY;

public class PlanDetailDialog extends DialogFragment {

    public interface PassPlanInterface{
        void getPlan(Plan plan);
    }


    private PassPlanInterface planInterface;

    private Button btnDismiss,btnAdd;
    private TextView txtName;
    private EditText editTxtMinutes;
    private Spinner spinnerDay;

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        View view =getActivity().getLayoutInflater().inflate(R.layout.dialog_plan_detail,null);
        initView(view);

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Enter Detail");

        Bundle bundle =getArguments();
        if(null !=bundle){
            Training training=bundle.getParcelable(TRAINING_KEY);
            if(null !=training){
                txtName.setText(training.getName());
                btnDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String day=spinnerDay.getSelectedItem().toString();
                        int minutes=Integer.valueOf(editTxtMinutes.getText().toString());
                        Plan plan=new Plan(training,minutes,day,false);


                        try{
                            planInterface=(PassPlanInterface)getActivity();
                            planInterface.getPlan(plan);
                            dismiss();
                        }catch (ClassCastException e){
                            e.printStackTrace();
                            dismiss();
                        }
                    }
                });
            }
        }
        return builder.create();

//        return super.onCreateDialog(savedInstanceState);
    }

    private void initView(View view) {
        btnAdd=view.findViewById(R.id.btnAdd);
        btnDismiss=view.findViewById(R.id.btnDismiss);
        txtName=view.findViewById(R.id.txtName);
        editTxtMinutes=view.findViewById(R.id.editTxtminutes);
        spinnerDay=view.findViewById(R.id.spinnerDays);

    }
}
