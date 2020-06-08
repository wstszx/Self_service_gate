package com.example.self_service_gate.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.TouchUtils;
import com.example.self_service_gate.R;
import com.example.self_service_gate.databinding.FragmentFaceRecognitionBinding;
import com.example.self_service_gate.view.AutoFitTextureView;
import com.example.self_service_gate.view.CameraPreview;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;


public class FaceRecognitionFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private static FaceRecognitionFragment sFragment;
    private FragmentFaceRecognitionBinding mBinding;
    private Camera mCamera;
    private AutoFitTextureView mTexture;
    private FrameLayout mFrameLayout;

    public FaceRecognitionFragment() {
        // Required empty public constructor
    }

    public static FaceRecognitionFragment newInstance() {
        if (sFragment == null) {
            sFragment = new FaceRecognitionFragment();
        }
        return sFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                ToastUtils.showShort("禁止拍照权限会导致无法完成人脸身份验证");
                NavHostFragment.findNavController(FaceRecognitionFragment.this).navigateUp();
            } else {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);
            }
        }
    }

    /**
     * A safe way to get an instance of the Camera object.
     */
    public Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT); // attempt to get a Camera instance
        } catch (Exception e) {
            ToastUtils.showShort("摄像头不可用");
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentFaceRecognitionBinding.inflate(inflater, container, false);
        initView();
        initData();
        return mBinding.getRoot();
    }


    private void initData() {

    }

    private void initView() {
        mFrameLayout = mBinding.framelayout;
        Button btTakePhoto = mBinding.btTakePhoto;
        btTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, mPicture);
            }
        });
        mCamera = getCameraInstance();
        CameraPreview cameraPreview = new CameraPreview(getContext(), mCamera);
        mFrameLayout.addView(cameraPreview);
        mCamera.setFaceDetectionListener(new MyFaceDetectionListener());
        checkPermission();
//        getOutputDirectory();
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
            if (pictureFile == null) {
                Log.d("mylog", "Error creating media file, check storage permissions");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d("mylog", "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d("mylog", "Error accessing file: " + e.getMessage());
            }
        }
    };

    private File getOutputMediaFile(int mediaTypeImage) {
        File pictureFolder = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        if (!pictureFolder.exists()) {
            if (!pictureFolder.mkdir()) {
                return null;
            }
        }
        return pictureFolder;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (MY_PERMISSIONS_REQUEST_CAMERA == requestCode) {
            if (grantResults.length <= 0 ||
                    grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                NavHostFragment.findNavController(FaceRecognitionFragment.this).navigateUp();
            }
        }

    }

     static class MyFaceDetectionListener implements Camera.FaceDetectionListener {

        @Override
        public void onFaceDetection(Camera.Face[] faces, Camera camera) {
            if (faces.length > 0) {
                Log.d("FaceDetection", "face detected: " + faces.length +
                        " Face 1 Location X: " + faces[0].rect.centerX() +
                        "Y: " + faces[0].rect.centerY());
            }
        }
    }

//    private void startCamera() {
//        final ListenableFuture<ProcessCameraProvider> listenableFuture = ProcessCameraProvider.getInstance(getContext());
//        listenableFuture.addListener(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    ProcessCameraProvider processCameraProvider = listenableFuture.get();
//                    Preview preview = new Preview.Builder().build();
//                    CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_FRONT).build();
//                    processCameraProvider.unbindAll();
//                    androidx.camera.core.Camera camera = processCameraProvider.bindToLifecycle(getViewLifecycleOwner(), cameraSelector, preview);
//                } catch (ExecutionException | InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, ContextCompat.getMainExecutor(getContext()));
//    }
}

