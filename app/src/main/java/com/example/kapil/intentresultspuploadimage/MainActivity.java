package com.example.kapil.intentresultspuploadimage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kapil.intentresultspuploadimage.databinding.ActivityMainBinding;
import com.example.kapil.intentresultspuploadimage.models.Login;

public class MainActivity extends AppCompatActivity {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String COLOR = "color";
    public static final String FILE_NAME = "kaxp_sp";
    public static final int REQ_CODE = 100;
    private ActivityMainBinding mB;
    private SharedPreferences mSp;

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mB = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mSp = getSharedPreferences(FILE_NAME, MODE_PRIVATE);


        setListeners();



    }

    private void setListeners() {
        mB.btnSubmit.setOnClickListener(v->{
            String username = mB.inUsername.getText().toString();
            String password = mB.inPassword.getText().toString();

            Login mL = new Login(username, password);

            if(mL.validateLogin()){
                hideKeyboard(this);



                SharedPreferences.Editor mE = mSp.edit();
                mE.putString(USERNAME , username);
                mE.putString(PASSWORD , password);
                mE.apply();

                Intent i = new Intent(MainActivity.this,HomePage.class);
                startActivityForResult(i,REQ_CODE);


//                Snackbar s = Snackbar.make(mB.llMainFrame, "Usernmame - " + username + " password - " + password,Snackbar.LENGTH_SHORT);
//                s.show();
            }else {
                Snackbar s = Snackbar.make(mB.llMainFrame, "Wrong Credentials" + password,Snackbar.LENGTH_SHORT);
                s.show();
            }




        });
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent i) {

        if (requestCode == REQ_CODE){
            if(resultCode == RESULT_OK && i != null){
                String color = i.getStringExtra(COLOR);

                switch (color){
                    case "Blue":
                        mB.llMainFrame.setBackgroundColor(getResources().getColor(R.color.app_blue));
                        break;
                    case "Red":
                        mB.llMainFrame.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        break;
                }


            }
        }


    }
}
