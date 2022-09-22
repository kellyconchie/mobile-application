package com.example.testingproject;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import CurrencyConverter.ConverterBuilder;
import CurrencyConverter.ConverterInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Currency extends AppCompatActivity {

    EditText amount_input;
    Spinner from_currency, to_currency;
    Button btn;
    TextView conversion_text;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

       // References to layout
        from_currency = findViewById(R.id.from_currency);
        to_currency = findViewById(R.id.to_currency);
        amount_input = findViewById(R.id.amount_input);
        btn = findViewById(R.id.conversion_button);
        conversion_text = findViewById(R.id.conversion_text);

      // With the following bit of code I am populating the currencies into both spinners
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(Currency.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.currencies));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from_currency.setAdapter(myAdapter);
        to_currency.setAdapter(myAdapter);


        btn.setOnClickListener(v -> {
            // accessing the builder
            ConverterInterface converterInterface = ConverterBuilder.getRetrofitInstance().create(ConverterInterface.class);
            // Call for API and currency from which I am converting from
            Call<JsonObject> call = converterInterface.getCurrencyConverter(from_currency.getSelectedItem().toString());
            call.enqueue(new Callback<JsonObject>() {

                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        // Amount to be converted
                        double amountInputted = Double.parseDouble(amount_input.getText().toString());
                        // Currency to which the amount will be converted to
                        double toCurrency = Double.parseDouble(rates.get(to_currency.getSelectedItem().toString()).toString());
                        // Final converted result
                        double convertedResult = amountInputted * toCurrency;
                        conversion_text.setText(String.valueOf(convertedResult));

                    }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {


                }
            });
        });
    }


}
