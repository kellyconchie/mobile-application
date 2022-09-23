package com.example.testingproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper  extends SQLiteOpenHelper {

    public static final String EXPENSE_TABLE = "EXPENSE_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_DATE = "COLUMN_DATE";
    public static final String COLUMN_AMOUNT = "COLUMN_AMOUNT";
    public static final String COLUMN_CATEGORY = "COLUMN_CATEGORY";


    public DBHelper(@Nullable Context context) {

        super(context, "expenses.db", null, 1);
    }


    // this is called the first time a database is accessed. There should be code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + EXPENSE_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, "  + COLUMN_AMOUNT + " INT ," + COLUMN_CATEGORY + " TEXT )";

        db.execSQL(createTable);

    }
    // This is called if the data version number changes. It prevents previous users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // This method is going to add a record to the database. Its going to expect to see a new ExpenseModel so I declare it in the parameter
    // The default return value is a boolean
    // no need to add ID number when inserting new record as the ID column is auto increment

    public boolean addOne(ExpensesModel expensesModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DATE, expensesModel.getDate());
        cv.put(COLUMN_AMOUNT, expensesModel.getAmount());
        cv.put(COLUMN_CATEGORY, expensesModel.getCategory());

        // The return value for insert will return a long, if positive it was successfully inserted, if negative it failed
        long insert = db.insert(EXPENSE_TABLE, null, cv);
        return insert != -1;
    }
    // Retriving all the sum of all the values under column_amount
    public int addValues() {
        int addedTotal = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM(COLUMN_AMOUNT) FROM EXPENSE_TABLE", null);
        if(c.moveToFirst()) {
            addedTotal = c.getInt(0);
        }
        return addedTotal;
    }

    // This is going to Delete an entry
    public boolean deleteEntry(ExpensesModel expensesModel) {

        // find expenseModel in the database. If we find it we will return true else if we return false
        SQLiteDatabase db = this.getWritableDatabase();
        String queryDelete = "DELETE FROM " + EXPENSE_TABLE + " WHERE " + COLUMN_ID + " = " + expensesModel.getId();


        Cursor cursor = db.rawQuery(queryDelete, null);

        return cursor.moveToFirst();
    }



    // Here I am going to retrieve everything in the database
    public ArrayList<ExpensesModel> getAllExpenses() {
        ArrayList<ExpensesModel> returnList = new ArrayList<>();

        //get data from the database

        String queryString = "SELECT * FROM " + EXPENSE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        // Cursor is our result
        Cursor cursor = db.rawQuery(queryString, null);

        // if there are results I am going to loop through them and create a new expense result for each row and insert into the return list

        if (cursor.moveToFirst()) {
            do {
                int expenseID = cursor.getInt(0);
                String expenseDate = cursor.getString(1);
                double expenseAmount = cursor.getDouble(2);
                String expenseCategory = cursor.getString(3);

                ExpensesModel newExpense = new ExpensesModel(expenseID, expenseDate,expenseAmount,expenseCategory);
                returnList.add(newExpense);

            } while (cursor.moveToNext());

        }
        //close cursor and db when finished

        cursor.close();
        db.close();
        return returnList;
    }





}
