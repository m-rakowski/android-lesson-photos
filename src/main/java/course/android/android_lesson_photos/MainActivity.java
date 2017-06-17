package course.android.android_lesson_photos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.io.File;


public class MainActivity extends Activity {
    File destination;
    static final int REQUEST_VIDEO_CAPTURE = 1;

    private static final int PICK_Camera_IMAGE = 2;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void takePictureAction(View view) {
        destination = new File(Environment
                .getExternalStorageDirectory(), "image.jpg");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(destination));
        startActivityForResult(intent, PICK_Camera_IMAGE);
    }


    public void takeVideoAction(View view) {
        destination = new File(Environment
                .getExternalStorageDirectory(), "video.mp4");
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(destination));
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }

        // Check that the SDCard is mounted
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraVideo");


        // Create the storage directory(MyCameraVideo) if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraVideo", "Failed to create directory MyCameraVideo.");
            }
        }
    }


}