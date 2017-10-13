package cameratool.lsl.com.cameraexample;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * 相机预览
 * Created by lsl on 2017/10/13.
 */

public class CameraSufaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera;

    public CameraSufaceView(Context context, Camera camera) {
        super(context);
        this.mCamera = camera;
        //设置属性
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            //设置预览
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if (mSurfaceHolder.getSurface() == null) {
            return;
        }
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mCamera.setDisplayOrientation(90); //旋转预览画面
        //设置对焦

        Camera.Parameters param = mCamera.getParameters();
        param.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        param.setSceneMode(Camera.Parameters.SCENE_MODE_BARCODE);
        mCamera.cancelAutoFocus();

        try {
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mCamera.release();
        mCamera = null;
    }
}
