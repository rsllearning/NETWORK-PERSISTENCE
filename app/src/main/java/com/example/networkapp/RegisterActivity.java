package com.example.networkapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.networkapp.SQL.DatabaseHelper;
import com.example.networkapp.model.User;

public class RegisterActivity extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null)
             setContentView(R.layout.activity_register);

        button=findViewById(R.id.appCompatButtonRegister);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText1=findViewById(R.id.textInputEditTextName);
                editText2=findViewById(R.id.textInputEditTextEmail_2);
                editText3=findViewById(R.id.textInputEditTextPassword_2);
                editText4=findViewById(R.id.textInputEditTextConfirmPassword);
                String name=editText1.getText().toString();
                String email=editText2.getText().toString();
                String passward=editText3.getText().toString();
                String confirm_pass=editText4.getText().toString();
                DatabaseHelper databaseHelper=new DatabaseHelper(RegisterActivity.this);
                if(email.equals("") || name.equals("")|| passward.equals("") || confirm_pass.equals(""))
                    Toast.makeText(RegisterActivity.this, "Input is empty, please enter some data", Toast.LENGTH_SHORT).show();
                else {
                    if (!databaseHelper.checkUser(email)) {
                        if (passward.equals(confirm_pass)) {
                            User user = new User(name, email, passward);
                            // adding user to data base

                        databaseHelper.addUser(user);
                        // adding user to shared prefrences in file "user_data"
//                        SharedPreferences shrd=getSharedPreferences("user_data",MODE_PRIVATE);
//                        SharedPreferences.Editor editor= shrd.edit();
//                        editor.putString("user_name",email);
//                        editor.putString("password",passward);
//                        editor.apply();
//                        // putting value of username and password to login window
//                        EditText login_username=findViewById(R.id.textInputEditTextEmail);
//                        EditText login_password=findViewById(R.id.textInputEditTextPassword);
//                        login_username.setText(shrd.getString("user_name",null));
//                        login_password.setText(shrd.getString("password",null));

                            Toast.makeText(RegisterActivity.this, "Bom! Successfully registered.", Toast.LENGTH_SHORT).show();
                            emptyInputEditText();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Password mismatch, kindly type same pass.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "This email is already registered, please register with other email id.", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
        //textView for login window
        textView=findViewById(R.id.appCompatTextViewLoginLink);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    private void emptyInputEditText() {
        editText1.setText(null);
        editText2.setText(null);
        editText3.setText(null);
        editText4.setText(null);

    }
}
