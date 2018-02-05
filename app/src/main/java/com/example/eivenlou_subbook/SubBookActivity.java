/* Copyright (c) 2018. Eivenlour David. CMPUT301 - University of Alberta.

    All rights reserved.

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights to
    use, copy, modify, merge, publish, distribute, and/or sell copies of the Software,
    and to permit persons to whom the Software is furnished to do so, provided that
    the above copyright notice(s) and this permission notice appear in all copies of
    the Software and that both the above copyright notice(s) and this permission
    notice appear in supporting documentation.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
    FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT OF THIRD PARTY RIGHTS. IN NO EVENT
    SHALL THE COPYRIGHT HOLDER OR HOLDERS INCLUDED IN THIS NOTICE BE LIABLE FOR
    ANY CLAIM, OR ANY SPECIAL INDIRECT OR CONSEQUENTIAL DAMAGES, OR ANY DAMAGES
    WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
    OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION
    WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.

    Except as contained in this notice, the name of a copyright holder shall not be
    used in advertising or otherwise to promote the sale, use or other dealings in
    this Software without prior written authorization of the copyright holder. */


package com.example.eivenlou_subbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static com.example.eivenlou_subbook.AddSubscriptionActivity.chargePerMonth;
import static com.example.eivenlou_subbook.AddSubscriptionActivity.commentSection;
import static com.example.eivenlou_subbook.AddSubscriptionActivity.startDate;
import static com.example.eivenlou_subbook.AddSubscriptionActivity.subName;

public class SubBookActivity extends AppCompatActivity {

    private static final String FILENAME = "subscriptions.sav";
    private ListView subscriptionList;
    private TextView total;
    private Double totalCharge;


    private ArrayList<Subscription> newList;
    private ArrayAdapter<Subscription> adapter;

    /** Called when the activity is first created */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LifeCycle ---->", "onCreate is called");
        setContentView(R.layout.activity_sub_book);

        subscriptionList = (ListView) findViewById(R.id.subscriptionList);

        total = findViewById(R.id.total);
        totalCharge = Double.valueOf(total.getText().toString());

    }

    /** Called when the user taps the Add new subscription button */
    public void addSubscription (View view) {
        Intent intent = new Intent(this, AddSubscriptionActivity.class);
        startActivityForResult(intent, 1);
    }

    /** Called when the user taps a specific subscription */
    public void viewEditDelete (View view) {
        Intent intent = new Intent(this, ViewEditDeleteActivity.class);
        startActivityForResult(intent, 1);
    }

    /** Called when the Activity result is set */
    @Override
    protected void onActivityResult(int myRequest, int myResult, Intent myIntent) {

        if (myRequest == 1) {
            if (myResult == Activity.RESULT_OK) {
                Bundle bundle = myIntent.getExtras();

                String name = bundle.getString(subName);
                String date = bundle.getString(startDate);
                Double charge = bundle.getDouble(chargePerMonth);
                String comment = bundle.getString(commentSection);

                Subscription subscription = new AddedSubscription(name, date, charge, comment);
                newList.add(subscription);

                totalCharge = totalCharge + charge;
                DecimalFormat rounded = new DecimalFormat("0.00");
                total.setText(String.valueOf(rounded.format(totalCharge)));

                adapter.notifyDataSetChanged();

                saveInFile();

            }
        }
    }


    @Override
    protected void onStart() {

        // TODO Auto-generated method stub
        super.onStart();
        Log.i("LifeCycle --->", "onStart is called");

        loadFromFile();

        adapter = new ArrayAdapter<Subscription>(this, R.layout.list_item, newList);

        subscriptionList.setAdapter(adapter);

        /*subscriptionList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
            {
                Intent intent = new Intent(SubBookActivity.this, ViewEditDeleteActivity.class);

                Subscription currentSub = (Subscription) adapter.getItemAtPosition(position);
                String currentSubName = currentSub.getName();
                String currentDate = currentSub.getDate();
                Double currentCharge = currentSub.getCharge();
                String currentComment = currentSub.getComment();

                Bundle bundle = new Bundle ();

                bundle.putString(subName, currentSubName);
                bundle.putString(startDate, currentDate);
                bundle.putDouble(chargePerMonth, currentCharge);
                bundle.putString(commentSection, currentComment);

                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });*/
    }

    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Taken https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018-01-23
            Type listType = new TypeToken<ArrayList<AddedSubscription>>() {
            }.getType();
            newList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            newList = new ArrayList<Subscription>();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    private void saveInFile() {
        try {

            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(newList, out);
            out.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Lifecycle", "onResume is called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Lifecycle", "onPause is called");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Lifecycle", "onStop is called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Lifecycle", "onRestart is called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Lifecycle", "onDestroy is called");
    }

}
