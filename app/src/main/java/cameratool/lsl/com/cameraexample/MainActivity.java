package cameratool.lsl.com.cameraexample;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Camera mCamera;
    private CameraSufaceView mCameraSufaceView;
    private CaptureView mCaptureView;
    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] bytes, Camera camera) {
            try {
                FileUtils.savePic(bytes, MainActivity.this);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (mCamera != null) {
                    mCamera.startPreview();
                }
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCamera = CameraUtil.getCamera();
        mCameraSufaceView = new CameraSufaceView(this, mCamera);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.camera_pre);
        frameLayout.addView(mCameraSufaceView);

        mCaptureView = new CaptureView(this);
        mCaptureView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        frameLayout.addView(mCaptureView);
    }

    public void doCamera(View v) {
        if (mCamera != null) {
            mCamera.takePicture(null, null, mPictureCallback);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCamera != null)
            mCamera.release(); //释放资源
    }
}
