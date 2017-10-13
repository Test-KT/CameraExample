package cameratool.lsl.com.cameraexample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lsl on 2017/10/13.
 */

public class FileUtils {

    /**
     * 保存图片并对图片进行旋转
     *
     * @param data
     * @param context
     * @throws IOException
     */
    public static void savePic(byte[] data, Context context) throws IOException {
        File pics = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "images");
        if (!pics.exists()) {
            pics.mkdirs();
        }
        File file = new File(pics, System.currentTimeMillis() + ".jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

        //图像翻转
        Matrix matrix = new Matrix();
        matrix.setRotate(90, bitmap.getWidth() / 2, bitmap.getHeight() / 2);

        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);

        bitmap.recycle();
        newBitmap.recycle();

        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getParent())));
    }

    public static void getBitmap() {

    }
}
