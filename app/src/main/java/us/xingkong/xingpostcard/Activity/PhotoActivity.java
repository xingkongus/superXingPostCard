package us.xingkong.xingpostcard.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ly.img.android.ui.activities.CameraPreviewActivity;
import ly.img.android.ui.activities.CameraPreviewIntent;
import ly.img.android.ui.activities.PhotoEditorIntent;
import ly.img.android.ui.utilities.PermissionRequest;
import us.xingkong.xingpostcard.R;


public class PhotoActivity extends AppCompatActivity {
    public static int CAMERA_PREVIEW_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CameraPreviewIntent(this)
                .setExportDir(CameraPreviewIntent.Directory.DCIM, "ImgLyExample")
                .setExportPrefix("example_")
                .setEditorIntent(
                        new PhotoEditorIntent(this)
                                .setExportDir(PhotoEditorIntent.Directory.DCIM, "ImgLyExample")
                                .setExportPrefix("result_")
                                .destroySourceAfterSave(true)
                )
                .startActivityForResult(CAMERA_PREVIEW_RESULT);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CAMERA_PREVIEW_RESULT) {
            String path = data.getStringExtra(CameraPreviewActivity.RESULT_IMAGE_PATH);

            Intent intent = new Intent(this, ArtActivity.class);
            intent.putExtra("myPhotoPath",path);
            startActivity(intent);
            Toast.makeText(this, "Image Save on: " + path, Toast.LENGTH_LONG).show();

        }
    }

    //Important for Android 6.0 permisstion request, don't forget this!
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionRequest.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    public void permissionGranted() {

    }


    public void permissionDenied() {
        //The Permission whas rejected by the user, so the Editor was not opened because it can not save the result Image.
        //TODO for you: Show a Hint to the User
    }
}
