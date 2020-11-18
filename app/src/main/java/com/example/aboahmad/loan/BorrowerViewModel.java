package com.example.aboahmad.loan;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;



public class BorrowerViewModel extends AndroidViewModel {
    private BorrowerRepository repository;
    private LiveData<List<Borrower>> allBorrowers;
    public BorrowerViewModel(@NonNull Application application) {
        super(application);
        repository=new BorrowerRepository(application);
        allBorrowers=repository.getAllBorrowers();

    }
    public void insert(Borrower borrower){
        repository.insert(borrower);
    }
    public void update(Borrower borrower){
        repository.update(borrower);
    }
    public void delete(Borrower borrower){
        repository.delete(borrower);
    }
    public void deleteAll(){
        repository.deleteAll();
    }

    public LiveData<List<Borrower>> getAllBorrowers() {
        return allBorrowers;
    }
}
