package CurrencyConverter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// This code is code that has been provided from retrofit
public class ConverterBuilder {
   private static Retrofit retrofit;
    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.exchangerate-api.com/")
                    // Converter Factory
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}

