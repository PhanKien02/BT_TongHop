package com.example.app_gk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app_gk.modules.Fruit;
import com.example.app_gk.modules.FruitRes;
import com.example.app_gk.modules.RealPathUtil;
import com.example.app_gk.ui.home.FileRes;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import reotrfit.FruitService;
import reotrfit.RetrofitService;
import reotrfit.UploadService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {
    public final static int PICK_PHOTO_CODE = 1046;
    String urlImage;
    RetrofitService retrofitService = new RetrofitService();
    UploadService uploadService = retrofitService.getRetrofit().create(UploadService.class);
    FruitService fruitService = retrofitService.getRetrofit().create(FruitService.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        EditText title = findViewById(R.id.et_ud_title);
        EditText des = findViewById(R.id.et_ud_des);
        ImageView imgview = findViewById(R.id.img_ud_prv);
        title.setText(intent.getStringExtra("title"));
        des.setText(intent.getStringExtra("des"));
        Long id = intent.getLongExtra("id", 0);
        String URL = intent.getStringExtra("image");
        new DisplayImage(imgview).execute(URL);
        Button btn_upload = findViewById(R.id.btn_ud_upload);
        Button btn_update = findViewById(R.id.btn_ud_update);

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fruit fruit = new Fruit(id,title.getText().toString(),des.getText().toString(),urlImage);
                fruitService.updateFruit(fruit).enqueue(new Callback<FruitRes>() {
                    @Override
                    public void onResponse(Call<FruitRes> call, Response<FruitRes> response) {
                        Response<FruitRes> responses = response;
                    }

                    @Override
                    public void onFailure(Call<FruitRes> call, Throwable t) {

                    }
                });
                Intent myIntern = new Intent(UpdateActivity.this,ListViewActivity.class);
                startActivity(myIntern);
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Bring up gallery to select a photo
            startActivityForResult(intent, PICK_PHOTO_CODE);
        }
    }

    public Bitmap loadFromUri(Uri photoUri) {
        Bitmap image = null;
        try {
            // check version of Android on device
            if (Build.VERSION.SDK_INT > 27) {
                // on newer versions of Android, use the new decodeBitmap method
                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), photoUri);
                image = ImageDecoder.decodeBitmap(source);
            } else {
                // support older versions of Android by using getBitmap
                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((data != null) && requestCode == PICK_PHOTO_CODE) {
            Uri photoUri = data.getData();

            // Load the image located at photoUri into selectedImage
            Bitmap selectedImage = loadFromUri(photoUri);
            uploadFile(photoUri);
            // Load the selected image into a preview
            ImageView ivPreview = (ImageView) findViewById(R.id.img_ud_prv);
            ivPreview.setImageBitmap(selectedImage);
        }
    }

    private void uploadFile(Uri fileUri) {
        String path = RealPathUtil.getRealPath(this, fileUri);
        File file = new File(path);
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(fileUri)),
                        file
                );
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        if (!file.exists()) {
            Toast.makeText(UpdateActivity.this, "CHUA KHÔNG TAI", Toast.LENGTH_SHORT).show();
        } else {
            Call<FileRes> call = uploadService.uploadFile(body);
            call.enqueue(new Callback<FileRes>() {
                @Override
                public void onResponse(Call<FileRes> call, Response<FileRes> response) {
                    String url = response.body().getUrl();
                    urlImage = url;
                    Toast.makeText(UpdateActivity.this, "upload thanh cong", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<FileRes> call, Throwable t) {
                    Toast.makeText(UpdateActivity.this, "upload That bai", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}