package com.example.eivenlou_subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;

import static com.example.eivenlou_subbook.AddSubscriptionActivity.chargePerMonth;
import static com.example.eivenlou_subbook.AddSubscriptionActivity.commentSection;
import static com.example.eivenlou_subbook.AddSubscriptionActivity.startDate;
import static com.example.eivenlou_subbook.AddSubscriptionActivity.subName;

public class ViewEditDeleteActivity extends AppCompatActivity {

    /** Called when the activity is first created */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_delete);

        /*Intent myIntent = new Intent(this, SubBookActivity.class);
        Bundle bundle = myIntent.getExtras();

        String name = bundle.getString(subName);
        String date = bundle.getString(startDate);
        Double charge = bundle.getDouble(chargePerMonth);
        String comment = bundle.getString(commentSection);

        EditText currentSubName = findViewById(R.id.subscriptionName);
        EditText currentDate = findViewById(R.id.dateStarted);
        EditText currentCharge = findViewById(R.id.monthlyCharge);
        EditText currentComment = findViewById(R.id.comment);

        currentSubName.setText(String.valueOf(name));
        currentDate.setText(String.valueOf(date));
        currentCharge.setText(String.valueOf(charge));
        currentComment.setText(String.valueOf(comment));

        */

    }

}
