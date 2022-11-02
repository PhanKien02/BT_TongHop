package reotrfit;
import com.google.gson.Gson;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitService {
    private Retrofit retrofit;

    public RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.1.134:8080/")
                .baseUrl("http://192.168.10.134:8080/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }
    public Retrofit getRetrofit() {
        return retrofit;
    }
}
