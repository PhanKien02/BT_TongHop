package reotrfit;

import com.example.app_gk.ui.home.FileRes;

import java.io.File;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface UploadService {
    @Multipart
    @POST("api/image/upload")
    Call<FileRes> uploadFile(@Part MultipartBody.Part file);
}
