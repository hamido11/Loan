package com.example.aboahmad.loan;

import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBorrowerActivity extends Activity {
    EditText editText_name,editText_description;
    Button button_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_borrower);
        editText_name=findViewById(R.id.edit_name);
        editText_description=findViewById(R.id.edit_description);
        button_save=findViewById(R.id.btn_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItem();
            }
        });

    }



    private void saveItem(){
        String name=editText_name.getText().toString();
        String description=editText_description.getText().toString();
        if(name.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "فضلا قم بادخال البيانات", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent=new Intent();
        intent.putExtra("name",name);
        intent.putExtra("desc",description);
        setResult(RESULT_OK,intent);
        finish();
    }
}
