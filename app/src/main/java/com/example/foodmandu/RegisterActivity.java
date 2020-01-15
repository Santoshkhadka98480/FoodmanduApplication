package com.example.foodmandu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodmandu.api.UserAPI;
import com.example.foodmandu.model.User;
import com.example.foodmandu.serverresponse.ImageResponse;
import com.example.foodmandu.serverresponse.SignUpResponse;
import com.example.foodmandu.strictmode.StrictModeClass;
import com.example.foodmandu.url.Url;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private CircleImageView imgProfile;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etPhoneNumber;
    private EditText etEmailR;
    private EditText etPasswordR;
    private EditText etCPasswordR;
    private Button btnSignUp;
    String imagePath;
    private String imageName = "";

    private static final Pattern PASSWORD_PATTERN= Pattern.compile("^"+"(?=.*[0-9])"+ "(?=.*[a-z])" +"(?=.*[A-Z])"+".{8,}"+"$");
    private static final Pattern CPASSWORD_PATTERN= Pattern.compile("^"+"(?=.*[0-9])"+ "(?=.*[a-z])" +"(?=.*[A-Z])"+"(?=\\S+$)"+".{8,}"+"$");
    private static final Pattern CONTACT_PATTERN= Pattern.compile("^"+"(?=.*[0-9])"+".{10,}");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        imgProfile = findViewById(R.id.imgProfile);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etEmailR = findViewById(R.id.etEmailR);
        etPasswordR = findViewById(R.id.etPasswordR);
        etCPasswordR = findViewById(R.id.etCPasswordR);

        btnSignUp = findViewById(R.id.btnRegisterR);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowserImage();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    if(etPasswordR.getText().toString().equals(etCPasswordR.getText().toString())){
                        saveImageOnly();
                        signUp();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                        etPasswordR.requestFocus();
                        return;
                    }
                }

            }
        });

    }

    private boolean validate() {
        String email = etEmailR.getText().toString();
        String password = etPasswordR.getText().toString();
        String cpassword = etCPasswordR.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();
        if (TextUtils.isEmpty(etFirstName.getText())) {
            etFirstName.requestFocus();
            etFirstName.setError("Please Enter First Name");
            return false;
        } else if (TextUtils.isEmpty(etLastName.getText())) {
            etLastName.requestFocus();
            etLastName.setError("Please Enter Last Name");
            return false;
        } else if (TextUtils.isEmpty(etPhoneNumber.getText())) {
            etPhoneNumber.requestFocus();
            etPhoneNumber.setError("Please Enter Phone Number");
            return false;
        }
        else if(!CONTACT_PATTERN.matcher(phoneNumber).matches()){
            etPhoneNumber.requestFocus();
            etPhoneNumber.setError("Please enter valid phone number");
            return false;
        }
        else if (TextUtils.isEmpty(etEmailR.getText())) {
            etEmailR.requestFocus();
            etEmailR.setError("Please Enter Email Address");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmailR.requestFocus();
            etEmailR.setError("Please enter valid email address");
            return false;
        }
        else if (TextUtils.isEmpty(etPasswordR.getText())) {
            etPasswordR.requestFocus();
            etPasswordR.setError("Please Enter Password");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            etPasswordR.requestFocus();
            etPasswordR.setError("Password should contain 8 character long with upper,lower case, numbers");
            return false;
        }
        else if (TextUtils.isEmpty(etCPasswordR.getText())) {
            etCPasswordR.requestFocus();
            etCPasswordR.setError("Please Enter Confirm Password");
            return false;
        }
        else if (!CPASSWORD_PATTERN.matcher(cpassword).matches()) {
            etCPasswordR.requestFocus();
            etCPasswordR.setError("Password should contain 8 character long with upper,lower case, numbers");
            return false;
        }
        return true;
    }


    private void signUp() {

        String fname = etFirstName.getText().toString();
        String lname = etLastName.getText().toString();
        String phonenumber = etPhoneNumber.getText().toString();
        String email = etEmailR.getText().toString();
        String password = etPasswordR.getText().toString();

        User users = new User(fname, lname, phonenumber, email, password, imageName);
        UserAPI userAPI = Url.getInstance().create(UserAPI.class);
        Call<SignUpResponse> signUpResponseCall = userAPI.registerUser(users);

        signUpResponseCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                etFirstName.setText("");
                etLastName.setText("");
                etPhoneNumber.setText("");
                etEmailR.setText("");
                etPasswordR.setText("");
                etCPasswordR.setText("");
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void BrowserImage() {
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgProfile.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveImageOnly(){
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);

        UserAPI userAPI = Url.getInstance().create(UserAPI.class);
        Call<ImageResponse> imageResponseCall = userAPI.uploadImage(body);

        StrictModeClass.StrictMode();

        try {
            Response<ImageResponse> imageResponse = imageResponseCall.execute();
            imageName = imageResponse.body().getFilename();
            Toast.makeText(this, "Image inserted", Toast.LENGTH_SHORT).show();

        } catch (IOException e){
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }


        
    }
}
