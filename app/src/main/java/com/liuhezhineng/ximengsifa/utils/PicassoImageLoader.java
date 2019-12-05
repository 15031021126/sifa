package com.liuhezhineng.ximengsifa.utils;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.lzy.imagepicker.loader.ImageLoader;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import java.io.File;

/**
 * @author AIqinfeng
 * @date 2018/4/15
 */

public class PicassoImageLoader implements ImageLoader {

	public static void displayImage(String path, ImageView imageView) {
		Picasso.get().load(NetConstant.FILE_URL + path)
			.placeholder(R.drawable.icon_01)
			.error(R.drawable.icon_01)
			.into(imageView);
	}

	@Override
	public void displayImage(Activity activity, String path, ImageView imageView, int width,
		int height) {
		Picasso.get().load(Uri.fromFile(new File(path)))
			.placeholder(R.drawable.ic_default_image)
			.error(R.drawable.ic_arrow_back)
			.resize(width, height)
			.centerInside()
			.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
			.into(imageView);
	}

	@Override
	public void displayImagePreview(Activity activity, String path, ImageView imageView, int width,
		int height) {
		Picasso.get().load(Uri.fromFile(new File(path)))
			.resize(width, height)
			.centerInside()
			.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
			.into(imageView);
	}

	@Override
	public void clearMemoryCache() {

	}
}