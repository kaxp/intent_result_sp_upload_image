package com.example.kapil.intentresultspuploadimage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.kapil.intentresultspuploadimage.databinding.ActivityHomePageBinding;

import java.util.ArrayList;
import java.util.List;

import static com.example.kapil.intentresultspuploadimage.MainActivity.COLOR;
import static com.example.kapil.intentresultspuploadimage.MainActivity.FILE_NAME;
import static com.example.kapil.intentresultspuploadimage.MainActivity.PASSWORD;
import static com.example.kapil.intentresultspuploadimage.MainActivity.USERNAME;

public class HomePage extends AppCompatActivity {

    private SharedPreferences mSP;
    private ActivityHomePageBinding mB;
    private String selectedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mB = DataBindingUtil.setContentView(this, R.layout.activity_home_page);

        mSP = getSharedPreferences(FILE_NAME,MODE_PRIVATE);

        String username = mSP.getString(USERNAME,"");
        String password = mSP.getString(PASSWORD,"");



        setUpSpinner();

    }

    private void setUpSpinner() {


        List<String> colors = new ArrayList<>();
        colors.add("Blue");
        colors.add("Red");

        ArrayAdapter mA = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1, colors);
        mB.spColor.setAdapter(mA);

        mB.spColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedColor = adapterView.getItemAtPosition(i).toString();




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mB != null) {
            mB.unbind();
            mB = null;
        }
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra(COLOR, selectedColor);
        setResult(RESULT_OK,i);
        super.onBackPressed();
    }
}
