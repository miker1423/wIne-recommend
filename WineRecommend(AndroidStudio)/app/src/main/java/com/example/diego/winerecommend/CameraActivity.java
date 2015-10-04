package com.example.diego.winerecommend;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    public static String[] MENU;
    private Bitmap imageTaken;
    private String[] menu;
    private ImageView imageTakenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        dispatchTakePictureIntent();
    }

    public void openCamera(View view){
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    public void sendData(View view){
        //Send imageTaken
        imageTakenView = (ImageView)findViewById(R.id.imageView2);
        Button button1 = (Button)findViewById(R.id.analyze);
        Button button2 = (Button)findViewById(R.id.tryAnother);
        imageTakenView.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        MENU = new String[]{"Pollo","Pescado","Res","Pasta"};

        Intent intent = new Intent(this, ViewMenu.class);
        startActivity(intent);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageTaken = (Bitmap) extras.get("data");
            imageTakenView = (ImageView)findViewById(R.id.imageView2);
            imageTakenView.setImageBitmap(imageTaken);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

    }
}
