package com.liuhezhineng.ximengsifa.module.home;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.liuhezhineng.ximengsifa.R;
import com.liuhezhineng.ximengsifa.adapter.AnnexAdapter;
import com.liuhezhineng.ximengsifa.adapter.CommentAdapter;
import com.liuhezhineng.ximengsifa.base.BaseActivity;
import com.liuhezhineng.ximengsifa.bean.ArticleBean;
import com.liuhezhineng.ximengsifa.bean.ArticleDataBean;
import com.liuhezhineng.ximengsifa.bean.ArticleDetailsBean;
import com.liuhezhineng.ximengsifa.bean.BaseBean;
import com.liuhezhineng.ximengsifa.bean.article.CommentBean;
import com.liuhezhineng.ximengsifa.constant.Constant;
import com.liuhezhineng.ximengsifa.constant.NetConstant;
import com.liuhezhineng.ximengsifa.model.DialogCallBack;
import com.liuhezhineng.ximengsifa.model.DialogStringCallBack;
import com.liuhezhineng.ximengsifa.model.MyStringCallback;
import com.liuhezhineng.ximengsifa.module.mine.login.LoginActivity;
import com.liuhezhineng.ximengsifa.provider.FileProviderUtils;
import com.liuhezhineng.ximengsifa.utils.FileUtils;
import com.liuhezhineng.ximengsifa.utils.UserHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.FileProvider;
import com.yanzhenjie.permission.Permission;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author AIqinfeng
 * @date 2018/4/22
 * @description 文章详情
 */

public class ArticleDetailsActivity extends BaseActivity {

	protected AnnexAdapter mAdapter;
	protected ArrayList<String> mAnnexList;
	@BindView(R.id.top_bar)
	QMUITopBar mTopBar;
	@BindView(R.id.tv_article_title)
	TextView mTvArticleTitle;
	@BindView(R.id.tv_article_from)
	TextView mTvArticleFrom;
	@BindView(R.id.tv_article_date)
	TextView mTvArticleDate;
	@BindView(R.id.tv_article_content)
	TextView mTvArticleContent;
	@BindView(R.id.empty_view)
	QMUIEmptyView mEmptyView;
	@BindView(R.id.iv_evaluate)
	ImageView mIvEvaluate;
	@BindView(R.id.tv_evaluate_count)
	TextView mTvEvaluateCount;
	@BindView(R.id.et_evaluate)
	EditText mEtEvaluate;
	@BindView(R.id.tv_evaluate)
	TextView mTvEvaluate;

	ArticleBean mBean;
	ArticleDataBean articleData;
	int width;
	int height;
	@BindView(R.id.rv_comments)
	RecyclerView mRvComments;
	@BindView(R.id.tv_views)
	TextView mTvViews;

	// article iamge
	private String picName = "";
	private String picPath = "";

	private NotificationManager mNotifyManager;
	private NotificationCompat.Builder mBuilder;
	private ArrayList<CommentBean> mCommentList;
	private CommentAdapter mCommentAdapter;

	public static void actionStart(Context context, ArticleBean bean) {
		Intent intent = new Intent(context, ArticleDetailsActivity.class);
		intent.putExtra(Constant.BEAN, bean);
		context.startActivity(intent);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_article_details;
	}

	@Override
	protected void initView() {
		super.initView();
		mBean = (ArticleBean) getIntent().getSerializableExtra(Constant.BEAN);
		initTopBar(mTopBar, R.string.details);
		mEmptyView.hide();

		if (!TextUtils.isEmpty(mBean.getFiles())) {
			findViewById(R.id.layout_annex).setVisibility(View.VISIBLE);
			initAnnex();
			setCaseFile(mBean.getFiles());
			mTopBar.addRightTextButton("下载附件", R.id.btn_ok).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						downloadFile();
					}
				});
		}

		mCommentList = new ArrayList<>();
		mCommentAdapter = new CommentAdapter(mActivity, mCommentList);
		mRvComments.setAdapter(mCommentAdapter);
		mRvComments.setLayoutManager(new LinearLayoutManager(mActivity));
	}

	private void initAnnex() {
		RecyclerView mRvAnnex = findViewById(R.id.rv_annex);
		mAnnexList = new ArrayList<>();
		mAdapter = new AnnexAdapter(mActivity, mAnnexList);
		mRvAnnex.setAdapter(mAdapter);
	}

	protected void setCaseFile(String caseFile) {
		// caseFile: "", "|xx",
		if (!"".equals(caseFile)) {
			ArrayList<String> list = new ArrayList<>();
			String[] fileArray = caseFile.split("\\|");
			if (fileArray.length <= 1) {
				findViewById(R.id.layout_annex).setVisibility(View.GONE);
			} else {
				findViewById(R.id.layout_annex).setVisibility(View.VISIBLE);
			}
			for (String path : fileArray) {
				if (!TextUtils.isEmpty(path)) {
					list.add(path);
				}
			}
			mAdapter.initData(list);
		}
	}

	private void downloadFile() {
		AndPermission.with(mActivity)
			.runtime()
			.permission(Permission.WRITE_EXTERNAL_STORAGE,
				Permission.READ_EXTERNAL_STORAGE)
			.onGranted(new Action<List<String>>() {
				@Override
				public void onAction(List<String> data) {
					OkGo.<File>get(NetConstant.BASE_URL + mBean.getFiles().substring(1))
						.execute(new FileCallback() {
							@Override
							public void onStart(
								Request<File, ? extends Request> request) {
								super.onStart(request);
								initNotification(EncodeUtils
									.urlDecode(mBean.getFiles().substring(
										mBean.getFiles().lastIndexOf("/") + 1)));
								showLoadingDialog("下载中...");
							}

							@Override
							public void onFinish() {
								super.onFinish();
								dismissLoadingDialog();
							}

							@Override
							public void uploadProgress(Progress progress) {
								super.uploadProgress(progress);
								mBuilder.setProgress(100,
									(int) (progress.currentSize
										/ progress.totalSize), false);
								mNotifyManager.notify();
							}

							@Override
							public void downloadProgress(Progress progress) {
								super.downloadProgress(progress);
							}

							@Override
							public void onSuccess(Response<File> response) {
								File file = response.body();
								if (file != null) {
									ToastUtils.showLong(
										file.getName() + "下载成功,在目录" + file
											.getAbsolutePath()
											+ "下");
									downloadFinished(file);
								}
							}
						});
				}
			})
			.onDenied(new Action<List<String>>() {
				@Override
				public void onAction(List<String> data) {
					ToastUtils.showLong("没有相关权限");
				}
			})
			.start();
	}

	private void initNotification(String fileName) {
		mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mBuilder = new NotificationCompat.Builder(mActivity);
		mBuilder.setContentTitle(fileName)
			.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
			.setSmallIcon(R.mipmap.ic_launcher)
			.setWhen(System.currentTimeMillis())
			.setAutoCancel(false)
			.setProgress(100, 0, false)
			.setContentText("正在下载")
			.setTicker("开始下载");
		mNotifyManager.notify(0, mBuilder.build());
	}

	private void downloadFinished(File file) {
		Intent intent = getFileIntent(file);
		PendingIntent pendingintent = PendingIntent.getActivity(mActivity, 0, intent,
			PendingIntent.FLAG_CANCEL_CURRENT);

		mBuilder.setTicker("下载完成")
			.setContentIntent(pendingintent)
			.setAutoCancel(true)
			.setProgress(100, 100, false)
			.setContentText("在目录" + file.getAbsolutePath() + "下");
		mNotifyManager.notify(0, mBuilder.build());
	}

	public Intent getFileIntent(File file) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		String type = getMIMEType(file);
		FileProviderUtils.setIntentDataAndType(this, intent, type, file, true);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		return intent;
	}

	private String getMIMEType(File f) {
		String type = "";
		String fName = f.getName();
		String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase();
		if ("pdf".equals(end)) {
			type = "application/pdf";
		} else if ("m4a".equals(end) || "mp3".equals(end) || "mid".equals(end) ||
			"xmf".equals(end) || "ogg".equals(end) || "wav".equals(end)) {
			type = "audio/*";
		} else if ("3gp".equals(end) || "mp4".equals(end)) {
			type = "video/*";
		} else if ("jpg".equals(end) || "gif".equals(end) || "png".equals(end) ||
			"jpeg".equals(end) || "bmp".equals(end)) {
			type = "image/*";
		} else if ("apk".equals(end)) {
			type = "application/vnd.android.package-archive";
		} else if ("pptx".equals(end) || "ppt".equals(end)) {
			type = "application/vnd.ms-powerpoint";
		} else if ("docx".equals(end) || "doc".equals(end)) {
			type = "application/vnd.ms-word";
		} else if ("xlsx".equals(end) || "xls".equals(end)) {
			type = "application/vnd.ms-excel";
		} else if ("txt".equals(end)) {
			type = "text/plain";
		} else {
			type = "*/*";
		}
		return type;
	}

	@Override
	protected void initData() {
		super.initData();

		WindowManager wm = (WindowManager) this
			.getSystemService(Context.WINDOW_SERVICE);
		if (wm != null) {
			width = wm.getDefaultDisplay().getWidth();
			height = wm.getDefaultDisplay().getHeight();
		}

		getArticleDetails(mBean.getId());
	}

	int currentThumbsUpCount;
	Boolean isThumbsUp;

	private void getArticleDetails(final String id) {
		final Map<String, String> map = new HashMap<>(16);
		map.put(NetConstant.ARTICLE_ID, id);
		OkGo.<BaseBean<ArticleDetailsBean>>post(NetConstant.GET_ARTICLE_DETAILS)
			.params(NetConstant.QUERY, new JSONObject(map).toString())
			.execute(new DialogCallBack<BaseBean<ArticleDetailsBean>>(mActivity) {
				@Override
				public void onError(Response<BaseBean<ArticleDetailsBean>> response) {
					super.onError(response);
					mEmptyView.show(false, getString(R.string.load_fail), null,
						getString(R.string.click_try_again)
						, new OnClickListener() {
							@Override
							public void onClick(View view) {
								mEmptyView.hide();
								getArticleDetails(id);
							}
						});
				}

				@Override
				public void onSuccess(Response<BaseBean<ArticleDetailsBean>> response) {
					final ArticleDetailsBean articleDetailsBean = response.body().getBody();

					initCommentsData(articleDetailsBean.getCommentList());

					mTvViews.setText(articleDetailsBean.getHits());

					final String thumbsUpCount = articleDetailsBean.getThumbsUpCount();
					mTvEvaluateCount.setText(thumbsUpCount);

					currentThumbsUpCount = Integer.parseInt(thumbsUpCount);
					isThumbsUp = Boolean.parseBoolean(articleDetailsBean.getIsThumbsUp());
					if (isThumbsUp) {
						mIvEvaluate.setBackgroundResource(R.drawable.praise);
					} else {
						mIvEvaluate.setBackgroundResource(R.drawable.normal_praise);
					}
					mIvEvaluate.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							if (!UserHelper.isIsLogin()) {
								LoginActivity.actionStart(mActivity, true);
							} else {
								Map<String, String> map = new HashMap<>(16);
								map.put("commentId", articleDetailsBean.getId());
								map.put("thumbsUp", !isThumbsUp + "");
								String queryStr = new JSONObject(map).toString();
								OkGo.<String>post(NetConstant.THUMBS_UP)
									.params(NetConstant.QUERY, queryStr)
									.execute(new MyStringCallback(mActivity) {
										@Override
										public void onSuccess(Response<String> response) {
											try {
												JSONObject jsonObject = new JSONObject(
													response.body());
												int status = jsonObject.getInt(Constant.STATUS);
												if (status == 0) {
													String body = jsonObject
														.getString(Constant.BODY);
													if (!isThumbsUp) {
														ToastUtils.showShort("点赞成功");
														mTvEvaluateCount.setText(String.valueOf((
															++currentThumbsUpCount)));
														isThumbsUp = true;
														mIvEvaluate.setBackgroundResource(R.drawable.praise);

													} else {
														ToastUtils.showShort("取消点赞成功");
														mTvEvaluateCount.setText(String.valueOf((
															--currentThumbsUpCount)));
														isThumbsUp = false;
														mIvEvaluate.setBackgroundResource(R.drawable.normal_praise);
													}
												} else {
													ToastUtils.showLong(
														jsonObject.getString(Constant.MSG));
												}
											} catch (JSONException e) {
												e.printStackTrace();
											}
										}
									});
							}
						}
					});
					mTvEvaluate.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							if (!UserHelper.isIsLogin()) {
								LoginActivity.actionStart(mActivity, true);
							} else {
								String evaluateStr = mEtEvaluate.getText().toString().trim();
								if (TextUtils.isEmpty(evaluateStr)) {
									ToastUtils.showLong("评论内容不能为空");
									return;
								}
								Map<String, String> evaluateMap = new HashMap<>(16);
								evaluateMap.put("contentId", articleDetailsBean.getId());
								evaluateMap.put("title", evaluateStr);
								evaluateMap.put("content", evaluateStr);
								String queryStr = new JSONObject(evaluateMap).toString();
								OkGo.<String>post(NetConstant.ADD_ARTICLE_EVALUATION)
									.params(NetConstant.QUERY, queryStr)
									.execute(new DialogStringCallBack(mActivity) {
										@Override
										public void onSuccess(Response<String> response) {
											try {
												JSONObject jsonObject = new JSONObject(
													response.body());
												int status = jsonObject.getInt(Constant.STATUS);
												if (status != 0) {
													ToastUtils.showLong(
														jsonObject.getString(Constant.MSG));
												} else {
													mEtEvaluate.setText("");
													ToastUtils.showShort("评论成功,审核通过后即可展示");
												}
											} catch (JSONException e) {
												e.printStackTrace();
											}
										}
									});
							}
						}
					});

					articleData = articleDetailsBean.getArticleData();
					mTvArticleTitle.setText(articleDetailsBean.getTitle());
					mTvArticleDate.setText(articleDetailsBean.getUpdateDate());
					mTvArticleFrom
						.setText(articleDetailsBean.getSiteName());
					mTvArticleContent.setTypeface(Typeface.SERIF);
					if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
						mTvArticleContent.setText(
							Html.fromHtml(articleData.getContent(), Html.FROM_HTML_MODE_COMPACT,
								new NetworkImageGetter(), null));
					} else {
						mTvArticleContent.setText(
							Html.fromHtml(articleData.getContent(), new NetworkImageGetter(),
								null));
					}
					mEmptyView.hide();
				}
			});
	}

	private void initCommentsData(List<CommentBean> list) {
		mCommentAdapter.initData(list);
	}

	/**
	 * 网络图片
	 *
	 * @author Susie
	 */
	private final class NetworkImageGetter implements Html.ImageGetter {

		@Override
		public Drawable getDrawable(final String source) {
			Drawable drawable = null;

			picName = mBean.getId() + source.substring(source.lastIndexOf("/") + 1);
			picPath = FileUtils.getFilePath(mActivity, Environment.DIRECTORY_PICTURES);

			// 封装路径
			File file = new File(picPath, picName);
			// 判断是否以http开头
			if (source.startsWith("http")) {
				// 判断路径是否存在
				if (file.exists()) {
					// 存在即获取drawable
					drawable = Drawable.createFromPath(file.getAbsolutePath());
					if (drawable != null) {
						drawable.setBounds(0,
							0,
							Math.min(drawable.getIntrinsicWidth() * 4, width - 50),
							Math.min(drawable.getIntrinsicHeight() * 4, height));
					}
				} else {
					AndPermission.with(mActivity)
						.runtime()
						.permission(Permission.WRITE_EXTERNAL_STORAGE,
							Permission.READ_EXTERNAL_STORAGE)
						.onGranted(new Action<List<String>>() {
							@Override
							public void onAction(List<String> data) {
								OkGo.<File>get(source)
									.execute(new FileCallback(picPath, picName) {
										@Override
										public void onSuccess(Response<File> response) {
											if (android.os.Build.VERSION.SDK_INT
												>= android.os.Build.VERSION_CODES.N) {
												mTvArticleContent.setText(
													Html.fromHtml(articleData.getContent(),
														Html.FROM_HTML_MODE_COMPACT,
														new NetworkImageGetter(), null));
											} else {
												mTvArticleContent.setText(
													Html.fromHtml(articleData.getContent(),
														new NetworkImageGetter(),
														null));
											}
										}
									});
							}
						})
						.onDenied(new Action<List<String>>() {
							@Override
							public void onAction(List<String> data) {
								ToastUtils.showLong("没有文件相关权限");
							}
						})
						.start();
				}
			}
			return drawable;
		}
	}
}
