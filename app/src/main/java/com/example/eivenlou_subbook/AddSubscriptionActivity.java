package com.example.eivenlou_subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddSubscriptionActivity extends AppCompatActivity {

    public static final String subName = "com.example.eivenlou_subbook.NAME";
    public static final String startDate = "com.example.eivenlou_subbook.DATE";
    public static final String chargePerMonth = "com.example.eivenlou_subbook.CHARGE";
    public static final String commentSection = "com.example.eivenlou_subbook.COMMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);
    }

    /** Called when the user taps the Add button */
    public void finalAdd(View view) {
        Intent intent = new Intent(this, SubBookActivity.class);

        EditText subscriptionName = findViewById(R.id.subscriptionName);
        EditText dateStarted = findViewById(R.id.dateStarted);
        EditText monthlyCharge = findViewById(R.id.monthlyCharge);
        EditText comment = findViewById(R.id.comment);

        String name = subscriptionName.getText().toString();
        String date = dateStarted.getText().toString();
        Double charge = Double.valueOf(monthlyCharge.getText().toString());
        String comments = comment.getText().toString();

        Bundle bundle = new Bundle ();

        bundle.putString(subName, name);
        bundle.putString(startDate, date);
        bundle.putDouble(chargePerMonth, charge);
        bundle.putString(commentSection, comments);

        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
