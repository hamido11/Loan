package com.example.aboahmad.loan;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;



public class BorrowerRepository {

    private BorrowerDao borrowerDao;
    private LiveData<List<Borrower>> allBorrowers;

    public BorrowerRepository(Application application){
        BorrowerDataBase dataBase=BorrowerDataBase.getInstance(application);
        borrowerDao=dataBase.borrowerDao();
        allBorrowers=borrowerDao.getAllBorrowers();
    }
    public void insert(Borrower borrower){
        new InsertBorrowersAsyncTask(borrowerDao).execute(borrower);
    }
    public void update(Borrower borrower){
        new UpdateBorrowersAsyncTask(borrowerDao).execute(borrower);
    }
    public void delete(Borrower  borrower){
        new DeleteBorrowersAsyncTask(borrowerDao).execute(borrower);
    }
    public void deleteAll(){
        new DeleteAllBorrowersAsyncTask(borrowerDao).execute();
    }

    public LiveData<List<Borrower>> getAllBorrowers() {
        return allBorrowers;
    }


    private static class InsertBorrowersAsyncTask extends AsyncTask<Borrower,Void,Void>{
    private BorrowerDao borrowerDao;
    InsertBorrowersAsyncTask(BorrowerDao borrowerDao){
        this.borrowerDao=borrowerDao;
    }

        @Override
        protected Void doInBackground(Borrower... borrowers) {
        borrowerDao.insert(borrowers[0]);
        return null;
        }
    }


    private static class UpdateBorrowersAsyncTask extends AsyncTask<Borrower,Void,Void>{
        private BorrowerDao borrowerDao;
        UpdateBorrowersAsyncTask(BorrowerDao borrowerDao){
            this.borrowerDao=borrowerDao;
        }

        @Override
        protected Void doInBackground(Borrower... borrowers) {
            borrowerDao.update(borrowers[0]);
            return null;
        }
    }

    private static class DeleteBorrowersAsyncTask extends AsyncTask<Borrower,Void,Void>{
        private BorrowerDao borrowerDao;
        DeleteBorrowersAsyncTask(BorrowerDao borrowerDao){
            this.borrowerDao=borrowerDao;
        }

        @Override
        protected Void doInBackground(Borrower... borrowers) {
            borrowerDao.delete(borrowers[0]);
            return null;
        }
    }
    private static class DeleteAllBorrowersAsyncTask extends AsyncTask<Void,Void,Void>{
        private BorrowerDao borrowerDao;
        DeleteAllBorrowersAsyncTask(BorrowerDao borrowerDao){
            this.borrowerDao=borrowerDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            borrowerDao.deleteAll();
            return null;
        }
    }


}
