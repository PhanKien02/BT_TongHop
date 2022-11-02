package reotrfit;

import com.example.app_gk.modules.Fruit;
import com.example.app_gk.modules.FruitRes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FruitService {
    @GET("api/fruits")
    Call<List<Fruit>> getAllFruit() ;

    @DELETE("api/fruits/{id}")
    Call<Void> deleteFruit(@Path("id") Long id);

    @POST("api/fruits")
    Call<FruitRes> saveFruit(@Body Fruit fruit);

    @PUT("api/fruits")
    Call<FruitRes> updateFruit(@Body Fruit fruit);
}
