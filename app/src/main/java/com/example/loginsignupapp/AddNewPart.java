package com.example.loginsignupapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.io.IOException;

public class AddNewPart extends AppCompatActivity {
    private EditText etBrand, etAddSize, etAddModelYear;
    private Spinner spPartCategorey;
    private ImageView ivPhoto;
    private FirebaseServices fbs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_part);
        conectComponent();

    }
    private void conectComponent(){
        etBrand = findViewById(R.id.etBrandNameAddPart);
        etAddSize = findViewById(R.id.etSizeAddPart);
        etAddModelYear = findViewById(R.id.etModelYearAddPart);
        spPartCategorey = findViewById(R.id.etSpinnerAddNewPart);
        ivPhoto = findViewById(R.id.etivPhotoAddPart);
        fbs = FirebaseServices.getInstance();
        spPartCategorey.setAdapter(new ArrayAdapter<psCategory>(this, android.R.layout.simple_spinner_item, psCategory.values()));
    }
    public void add(View view) {
        // check if any field is empty
        String brand,addsize,modelyear ,category, photo;
        brand = etBrand.getText().toString();
        addsize = etAddSize.getText().toString();
        modelyear = etAddModelYear.getText().toString();

        category = spPartCategorey.getSelectedItem().toString();
        if (ivPhoto.getDrawable() == null)
            photo = "no_image";
        else photo = ivPhoto.getDrawable().toString();

        if (brand.trim().isEmpty() || addsize.trim().isEmpty() || modelyear.trim().isEmpty() ||
                category.trim().isEmpty() || photo.trim().isEmpty())
            {
                Toast.makeText(this," fields are empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Part rest  = new Part(brand, addsize, modelyear, psCategory.valueOf(category));
        fbs.getFire().collection("restaurants")
                .add(rest)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void selectPhoto(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),40);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 40) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}



}