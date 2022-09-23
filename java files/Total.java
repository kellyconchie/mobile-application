package com.example.testingproject;



import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import CurrencyConverter.ConverterBuilder;
import CurrencyConverter.ConverterInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Total  extends AppCompatActivity {

    TextView  added_textView, amountAdded, text_c, GBP, conversion, conversionRate, currencyCode;
    Button  convert_btn;
    ListView list_expenses;
    Spinner sp_currency;
    ArrayList<ExpensesModel> arrayList;
    ListExpensesAdapter expensesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //References to the layout
        convert_btn = findViewById(R.id.convert_btn);
        list_expenses = findViewById(R.id.list_expenses);
        added_textView = findViewById(R.id.added_textView);
        amountAdded = findViewById(R.id.amountAdded);
        sp_currency = findViewById(R.id.sp_currency);
        text_c = findViewById(R.id.text_c);
        GBP = findViewById(R.id.GBP);
        conversion = findViewById(R.id.conversion);
        conversionRate = findViewById(R.id.conversionRate);
        currencyCode = findViewById(R.id.currencyCode);
        arrayList = new ArrayList<>();
        PopulateData();


        // Here is where I am declaring to delete an entry
        list_expenses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            final DBHelper dataBaseHelper = new DBHelper(Total.this);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExpensesModel clickedExpense = (ExpensesModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteEntry(clickedExpense);
                Toast.makeText(Total.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        // Here the sum of all the amounts will be shown in a textview
        DBHelper dataBaseHelper = new DBHelper(Total.this);
        int sum = dataBaseHelper.addValues();
        amountAdded.setText(String.valueOf(sum));



        // Populating the spinner with values in the strings.xml file with an adapter
        ArrayAdapter<String> currencyAdapter = new ArrayAdapter<>(Total.this, android.R.layout.simple_list_item_1,
        getResources().getStringArray(R.array.currencies));
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_currency.setAdapter(currencyAdapter);

        // conversion to another currency
        convert_btn.setOnClickListener(v -> {

            // access the builder
            ConverterInterface converterInterface = ConverterBuilder.getRetrofitInstance().create(ConverterInterface.class);
            Call<JsonObject> call = converterInterface.getCurrencyConverter(GBP.getText().toString());
            call.enqueue(new Callback<JsonObject>() {

                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    JsonObject res = response.body();
                    assert res != null;
                    JsonObject rates = res.getAsJsonObject("rates");
                    // This is the amount that is going to be converted
                    double amountAddedTogether = Double.parseDouble(amountAdded.getText().toString());
                    //This is the currency that amount is going to be converted to
                    double toCurrency = Double.parseDouble(rates.get(sp_currency.getSelectedItem().toString()).toString());
                    // The result of the conversion
                    double convertedResult = amountAddedTogether * toCurrency;
                    conversion.setVisibility(View.VISIBLE);
                    conversionRate.setText(String.valueOf(convertedResult));
                    // Retriving the currency that it has been converted to
                    String currency_code = sp_currency.getSelectedItem().toString();
                    currencyCode.setText(currency_code);


                }
                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {


                }
            });
        });



    }
    // Here is where the data will be retrieved from the database and displayed in a listView
    private void PopulateData() {
        DBHelper dataBaseHelper = new DBHelper(Total.this);
        arrayList = dataBaseHelper.getAllExpenses();
        expensesAdapter = new ListExpensesAdapter(this, arrayList);
        list_expenses.setAdapter(expensesAdapter);
        expensesAdapter.notifyDataSetChanged();
    }
}
