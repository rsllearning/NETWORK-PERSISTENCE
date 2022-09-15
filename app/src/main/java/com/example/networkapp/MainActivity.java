package com.example.networkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.networkapp.SQL.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        if(savedInstanceState==null)
               setContentView(R.layout.activity_main);

        editText1=findViewById(R.id.textInputEditTextEmail);
        editText2=findViewById(R.id.textInputEditTextPassword);
        if(!(editText1.getText().toString().equals("") || editText2.getText().toString().equals(""))) {
            if (new DatabaseHelper(MainActivity.this).
                    checkUser(editText1.getText().toString(), editText2.getText().toString())) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        }

        button=findViewById(R.id.appCompatButtonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText1=findViewById(R.id.textInputEditTextEmail);
                editText2=findViewById(R.id.textInputEditTextPassword);
                String user_name=editText1.getText().toString();
                String pass=editText2.getText().toString();
                if(editText1.getText().toString().equals("") || editText2.getText().toString().equals(""))
                      return;
                DatabaseHelper databaseHelper=new DatabaseHelper(MainActivity.this);
                // checking whether user exist in data base or not
                if(databaseHelper.checkUser(user_name,pass)){
                    // store data in SharedPrefrences for keep login in,in future.

                    Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "You are not register, please register first", Toast.LENGTH_LONG).show();
                }
            }
        });
        //
        textView=findViewById(R.id.textViewLinkRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}