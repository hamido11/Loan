package com.example.aboahmad.loan;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;



@Database(entities = {Borrower.class},version =1 ,exportSchema = false)
public abstract class BorrowerDataBase extends RoomDatabase {

    private static BorrowerDataBase instance;
    public  abstract BorrowerDao borrowerDao();

    public static synchronized BorrowerDataBase getInstance(Context context){
        if (instance == null){
            instance= Room.databaseBuilder(context.getApplicationContext()
            ,BorrowerDataBase.class,"loan_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallBack=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new PopulateAsyncTaskDataBase(instance).execute();
            super.onCreate(db);
        }
    };

    private static class PopulateAsyncTaskDataBase extends AsyncTask<Void,Void,Void>{
     private BorrowerDao borrowerDao;
     PopulateAsyncTaskDataBase(BorrowerDataBase dataBase){
         this.borrowerDao=dataBase.borrowerDao();
     }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
