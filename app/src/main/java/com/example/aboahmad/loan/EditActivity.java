package com.example.aboahmad.loan;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
 EditText editName,editDescription;
 Button btn_edit;
 private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editName=findViewById(R.id.name);
        editDescription=findViewById(R.id.description);
        btn_edit=findViewById(R.id.btn_edit);
        ActionBar bar=getSupportActionBar();
        bar.setTitle("تعديل");
        bar.show();
        Borrower borrower=getIntent().getParcelableExtra("edit");
        String name=borrower.getName();
        String description=borrower.getLoan_description();
        id=getIntent().getIntExtra("id",-1);
        editName.setText(name);
        editDescription.setText(description);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameAfetrEdit=editName.getText().toString();
                String descAfterEdit=editDescription.getText().toString();
                Borrower borrower1=new Borrower(nameAfetrEdit,descAfterEdit);
                Intent editIntent=new Intent();
                editIntent.putExtra("id",id);
                editIntent.putExtra("edited_borrower",borrower1);
                setResult(RESULT_OK,editIntent);
                finish();
            }
        });
    }
}
