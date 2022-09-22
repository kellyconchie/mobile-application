package CurrencyConverter;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ConverterInterface {
    // Here I am adding a GET request as ExchangeRate - API requires one
    // Retrofit has been imported as it turn HTTP API into a Java Interface
    // I am using JsonObject  as this is what the API supports

    // Instead of adding a static base currency, I have named a variable so that the base can be changed
    @GET("v4/latest/{baseCurrency}")
    // the format in which the data is received, in this case I am using Json
    Call<JsonObject> getCurrencyConverter(@Path("baseCurrency") String baseCurrency);
}
