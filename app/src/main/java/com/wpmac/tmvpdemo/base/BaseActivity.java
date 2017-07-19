package com.wpmac.tmvpdemo.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wpmac.premisson.XPermissionUtils;


/**
 * @fileName BaseActivity.java
 * @description Activity基类
 * @version 1.0
 */
public abstract class BaseActivity extends RxAppCompatActivity {
	/** Appliction基类对象 **/
	//protected BaseApplication mApplication;
	protected Typeface mTypeface ;
	protected String pageName = "";
	public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);

	}

	/**
	 * 操作模式
	 * @return
	 */
	protected Boolean isMobile(){
		return BasePreference.getInstance().isMobile();
	}


    /** 获取当前Activity **/
	protected Activity getActivity()
	{
		return this;
	}
	
	/** 获取当前Context **/
	protected Context getContext()
	{
		return this;
	}
	
	/** 短暂显示Toast提示(来自res) **/
	protected void showShortToast(int resId) {
		Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
	}

	/** 短暂显示Toast提示(来自String) **/
	protected void showShortToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	/** 长时间显示Toast提示(来自res) **/
	protected void showLongToast(int resId) {
		Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
	}

	/** 长时间显示Toast提示(来自String) **/
	protected void showLongToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
		
	

	}
	

	

	/** Debug输出Log日志 **/
	protected void showLogDebug(String tag, String msg) {
		L.d(tag, msg);
	}

	/** Error输出Log日志 **/
	protected void showLogError(String tag, String msg) {
		L.e(tag, msg);
	}




	

	
	/** 含有标题和内容的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message).show();
		return alertDialog;
	}

	/** 含有标题、内容、两个按钮的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message,
                                          String positiveText,
                                          DialogInterface.OnClickListener onPositiveClickListener,
                                          String negativeText,
                                          DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}

	/** 含有标题、内容、图标、两个按钮的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message,
                                          int icon, String positiveText,
                                          DialogInterface.OnClickListener onPositiveClickListener,
                                          String negativeText,
                                          DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message).setIcon(icon)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;

	}

    /**
     *  权限
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
			grantResults) {
        try {
            XPermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}

//	/** 带有右进右出动画的退出 **/
//	public void finish() {
//		super.finish();
//		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//	}

	/** 默认退出 **/
	protected void defaultFinish() {
		super.finish();
	}

	/**
	 * 隐藏软键盘
	 */
	protected void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
			if (getCurrentFocus() != null)
				imm.hideSoftInputFromWindow(getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
	
	//获取屏幕宽度
	public int getDisplayMetricsWidth() {
		int i = getActivity().getWindowManager().getDefaultDisplay().getWidth();
		int j = getActivity().getWindowManager().getDefaultDisplay().getHeight();
		return Math.min(i, j);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onPause() {
		super.onPause();

	}

	protected void exitSys(){
		AppManager.getAppManager().AppExit();
	}


	protected void showFaiedList(String message, Context mContext) {

		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		AlertDialog alert = builder
				.setTitle("以下单号未提交成功：")
				.setMessage(message)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).create();
		alert.show();
	}

	protected void showSimpleAlertDialog(String title, String message, Context context) {

		AlertDialog.Builder  builder = new AlertDialog.Builder(context);
		AlertDialog alert = builder
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).create();
		alert.show();
	}

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

//        ToastWidget.createDialog(getApplicationContext(),event.getKeyCode()+"").show();
        return super.dispatchKeyEvent(event);
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event. KEYCODE_HOME) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
