package com.example.aboahmad.loan;



import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;



@Dao
public interface BorrowerDao {

    @Insert
    void insert(Borrower borrower);

    @Update
    void update(Borrower borrower);

    @Delete
    void delete(Borrower borrower);

    @Query("DELETE from loan_table")
        void deleteAll();

    @Query("SELECT * from loan_table ORDER BY id ASC")
    LiveData<List<Borrower>> getAllBorrowers();
    }

