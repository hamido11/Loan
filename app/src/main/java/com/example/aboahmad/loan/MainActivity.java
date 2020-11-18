package com.example.aboahmad.loan;


import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;



public class MainActivity extends AppCompatActivity {
    public static final int ADD_BORROWER_REQUEST=1;
    public static final int EDIT_BORROWER_REQUEST=2;
    BorrowerViewModel borrowerViewModel;
    RecyclerView recyclerView;
    FloatingActionButton button_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        button_add=findViewById(R.id.btn_add);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        final LoanAdapter adapter=new LoanAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        borrowerViewModel= ViewModelProviders.of(this).get(BorrowerViewModel.class);
        borrowerViewModel.getAllBorrowers().observe(this, new Observer<List<Borrower>>() {
            @Override
            public void onChanged(@Nullable List<Borrower> borrowers) {
                adapter.setBorrowerList(borrowers);

            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent=new Intent(MainActivity.this,AddBorrowerActivity.class);
             startActivityForResult(intent,ADD_BORROWER_REQUEST);
         }
     });
     adapter.setOnBorrowerClockListener(new LoanAdapter.onBorrowerClickListener() {
         @Override
         public void onBorrowerClick(Borrower borrower) {
             Intent editIntent=new Intent(MainActivity.this,EditActivity.class);
             editIntent.putExtra("edit",borrower);
             editIntent.putExtra("id",borrower.getId());
             startActivityForResult(editIntent,EDIT_BORROWER_REQUEST);
         }
     });
     new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
             ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
         @Override
         public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
             return false;
         }

         @Override
         public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
             AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this).
                     setTitle("حذف هذا العنصر نهائيا")
                     .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                          adapter.notifyDataSetChanged();
                         }
                     }).setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             borrowerViewModel.delete(adapter.getBorrowerAt(viewHolder.getAdapterPosition()));
                             Toast.makeText(MainActivity.this, "تم حذف بيانات هذا الشخص", Toast.LENGTH_LONG).show();
                         }
                     });
             builder.create().show();

         }
     }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if(requestCode == ADD_BORROWER_REQUEST && resultCode == RESULT_OK){
           String name=data.getStringExtra("name");
           String description=data.getStringExtra("desc");
           Borrower borrower=new Borrower(name,description);
           borrowerViewModel.insert(borrower);
           Toast.makeText(this, "تم اضافة عنصر جديد", Toast.LENGTH_LONG).show();
       }else if (requestCode == EDIT_BORROWER_REQUEST && resultCode == RESULT_OK){
           int id=data.getIntExtra("id",-1);
           Borrower borrower=data.getParcelableExtra("edited_borrower");
           if(id != -1){
               borrower.setId(id);
           }
           borrowerViewModel.update(borrower);
           Toast.makeText(this, "تم تعديل البيانات", Toast.LENGTH_LONG).show();
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment selectedFrag=null;
        switch (item.getItemId()){

            case R.id.information:
            selectedFrag=new InstructionFragment();

            break;

            case R.id.exit:
                finish();
            return true;

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,selectedFrag)
                .addToBackStack(null).commit();
        return true;
    }
}
