package cameratool.lsl.com.cameraexample.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import java.io.File;

/**
 * Created by lsl on 17-10-15.
 */

public class ImgUtil {

    /**
     * 获取一个圆形区域
     *
     * @param bitmap
     * @return
     */
    public static Bitmap getCircleBitmap(Bitmap bitmap, int x, int y, int r) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawCircle(x, y, r, paint);
        paint.reset();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return newBitmap;
    }

    /**
     * 获取一个缩略图
     *
     * @param file
     * @return
     */
    public static Bitmap getThumbBitmap(File file) {
        return null;
    }

    /**
     * 翻转图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap setRotate(Bitmap bitmap, float degrees) {
        //图像翻转
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        return newBitmap;
    }


    /**
     * 把图片缩放到指定分辨率
     *
     * @param bitmap
     * @param width
     * @param height
     * @return
     */
    public static Bitmap getScale(Bitmap bitmap, int width, int height) {
        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }
}
