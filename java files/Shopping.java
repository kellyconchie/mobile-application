package com.example.testingproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Shopping  extends AppCompatActivity {
    Button add_btn;
    EditText et_amount;
    CalendarView cal;
    TextView date_textView, expense_list, view_Expenses, pound_textView, shopping_text, date_selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // References to layout content
        cal = findViewById(R.id.cal);
        date_textView = findViewById(R.id.date_textView);
        et_amount = findViewById(R.id.et_amount);
        add_btn = findViewById(R.id.add_btn);
        view_Expenses = findViewById(R.id.view_Expenses);
        expense_list = findViewById(R.id.expense_list);
        pound_textView = findViewById(R.id.pound_textView);
        shopping_text = findViewById(R.id.shopping_text);
        date_selected = findViewById(R.id.date_selected);



        String todays_date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        date_textView.setText(todays_date);

        // This following bit of code displays the calendars date once clicked as well as changing the Date text
        cal.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String date = dayOfMonth + "/" + (month + 1) + "/" + year;
            date_selected.setText("Date selected: ");
            date_textView.setText(date);
        });



        // With the following bit of code I am stating that if the button is clicked it should check that an amount as been inputted and
        // it also submits the information to the database
        // I am setting the id number as -1 as default value
        // I am getting the users input as the amount and converting the double to a string
        // I am also getting the category by a textView
        // The exception will catch the error and return a toast message instead of completely crashing

        add_btn.setOnClickListener(v -> {

            ExpensesModel expensesModel;
            try {
                expensesModel = new ExpensesModel(-1, date_textView.getText().toString(), Double.parseDouble(et_amount.getText().toString()), shopping_text.getText().toString());
                Toast.makeText(Shopping.this, expensesModel.toString(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                expensesModel = new ExpensesModel(-1, "error", 0, "error");
                Toast.makeText(Shopping.this, "Error creating entry", Toast.LENGTH_SHORT).show();

            }

            DBHelper dataBaseHelper = new DBHelper(Shopping.this);
            boolean success = dataBaseHelper.addOne(expensesModel);



        });


        // With the following bit of code i am going to open up a new activity once the textView is clicked

        view_Expenses.setOnClickListener(v -> {
            Intent expenseShopping = new Intent(Shopping.this, Total.class);
            startActivity(expenseShopping);
        });



    }
}
