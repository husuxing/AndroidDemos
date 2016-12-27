package cn.izis.zxing.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.Hashtable;

import cn.izis.zxing.view.MipcaActivityCapture;


/**
 * 生成和扫描二维码
 * 
 * @author lxf
 */
public class QRCodeUtil {
	/**
	 * 生成QRCode（二维码）
	 * 
	 * @param url 二维码信息
	 * @return 二维码图片
	 * @throws WriterException
	 */
	public static Bitmap createQRCode(String url) throws WriterException {

		if (url == null || url.equals("")) {
			return null;
		}

		// 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
		BitMatrix matrix = new MultiFormatWriter().encode(url,
				BarcodeFormat.QR_CODE, 400, 400);

		int width = matrix.getWidth();
		int height = matrix.getHeight();

		// 二维矩阵转为一维像素数组,也就是一直横着排了
		int[] pixels = new int[width * height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = 0xff000000;
				}

			}
		}

		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	/**
	 * 生成二维码
	 * 
	 * @param string
	 *            二维码中包含的文本信息
	 * @param mBitmap
	 *            logo图片
	 * @param format
	 *            编码格式
	 * @return Bitmap 位图
	 * @throws WriterException
	 */
	public Bitmap createCode(String string, Bitmap mBitmap, BarcodeFormat format)
			throws WriterException {
		int IMAGE_HALFWIDTH = 40;//中间的logo图片大小
		Matrix m = new Matrix();
		float sx = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getWidth();
		float sy = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getHeight();
		m.setScale(sx, sy);// 设置缩放信息
		// 将logo图片按martix设置的信息缩放
		mBitmap = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(),
				mBitmap.getHeight(), m, false);
		MultiFormatWriter writer = new MultiFormatWriter();
		Hashtable<EncodeHintType, String> hst = new Hashtable<EncodeHintType, String>();
		hst.put(EncodeHintType.CHARACTER_SET, "UTF-8");// 设置字符编码
		BitMatrix matrix = writer.encode(string, format, 400, 400, hst);// 生成二维码矩阵信息
		int width = matrix.getWidth();// 矩阵高度
		int height = matrix.getHeight();// 矩阵宽度
		int halfW = width / 2;
		int halfH = height / 2;
		int[] pixels = new int[width * height];// 定义数组长度为矩阵高度*矩阵宽度，用于记录矩阵中像素信息
		for (int y = 0; y < height; y++) {// 从行开始迭代矩阵
			for (int x = 0; x < width; x++) {// 迭代列
				if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH
						&& y > halfH - IMAGE_HALFWIDTH
						&& y < halfH + IMAGE_HALFWIDTH) {// 该位置用于存放图片信息
				// 记录图片每个像素信息
					pixels[y * width + x] = mBitmap.getPixel(x - halfW
							+ IMAGE_HALFWIDTH, y - halfH + IMAGE_HALFWIDTH);
				} else {
					if (matrix.get(x, y)) {// 如果有黑块点，记录信息
						pixels[y * width + x] = 0xff000000;// 记录黑块信息
					}
				}

			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		// 通过像素数组生成bitmap
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	/**
	 * 跳转到一个新页面扫描二维码
	 * 
	 * @param activity
	 *            当前页面
	 * @param requestCode
	 *            请求码
	 */
	public static void ScanQRCode(Activity activity, int requestCode) {
		Intent intent = new Intent();
		intent.setClass(activity, MipcaActivityCapture.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		activity.startActivityForResult(intent, requestCode);
	}
}
