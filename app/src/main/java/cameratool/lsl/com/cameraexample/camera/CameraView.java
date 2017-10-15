package cameratool.lsl.com.cameraexample.camera;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

import cameratool.lsl.com.cameraexample.utils.CameraUtil;
import cameratool.lsl.com.cameraexample.utils.FileUtils;

/**
 * Created by lsl on 17-10-15.
 */

public class CameraView extends SurfaceView implements SurfaceHolder.Callback, Camera.AutoFocusCallback {
    private Context mContext;

    private SurfaceHolder mSurfaceHolder;

    private Camera mCamera;


    private Camera.PictureCallback mPictureCallback;

    private Point mPoint;
    private int r;

    public CameraView(Context context) {
        this(context, null, 0);
    }

    public CameraView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mPictureCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                try {
                    FileUtils.savePic(data);
//                    FileUtils.saveCirclePic(data, mPoint, r);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    mCamera.startPreview();
                }
            }
        };
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.w("info--->", "预览布局的宽高:" + w + "---:" + h);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mCamera == null) {
            mCamera = CameraUtil.getCamera();
            try {
                mCamera.setPreviewDisplay(mSurfaceHolder);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mCamera.startPreview();
        setCameraParams();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
        mSurfaceHolder = null;
    }

    @Override
    public void onAutoFocus(boolean success, Camera camera) {

    }


    private void setCameraParams() {
        mCamera.stopPreview();
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setJpegQuality(100);
        parameters.setFocusMode(android.hardware.Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        mCamera.cancelAutoFocus();

        mCamera.setDisplayOrientation(90);
        mCamera.setParameters(parameters);
//        mCamera.autoFocus(this);
        mCamera.startPreview();
    }

    public void takepick(Point point, int r) {
        this.mPoint = point;
        this.r = r;
        if (mCamera != null)
            mCamera.takePicture(null, null, mPictureCallback);
    }


}
