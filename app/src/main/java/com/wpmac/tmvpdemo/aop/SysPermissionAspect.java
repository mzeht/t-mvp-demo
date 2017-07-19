package com.wpmac.tmvpdemo.aop;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.app.annotation.aspect.Permission;
import com.wpmac.premisson.DialogUtil;
import com.wpmac.premisson.PermissionHelper;
import com.wpmac.premisson.RequestCode;
import com.wpmac.premisson.XPermissionUtils;
import com.wpmac.tmvpdemo.base.AppManager;
import com.wpmac.tmvpdemo.base.widget.ToastWidget;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by baixiaokang on 17/1/31.
 * 申请系统权限切片，根据注解值申请所需运行权限
 */
@Aspect
public class SysPermissionAspect {

    @Around("execution(@com.app.annotation.aspect.Permission * *(..)) && @annotation(permission)")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, Permission permission) throws Throwable {
//        AppCompatActivity ac = (AppCompatActivity) App.getAppContext().getCurActivity();
        final Activity activity = AppManager.getAppManager().currentActivity();
        XPermissionUtils.requestPermissions(activity, RequestCode.MORE, permission.value()
                , new XPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() throws Throwable {
                        if (PermissionHelper.isCameraEnable()) {
                            ToastWidget.createDialog(activity,"获取相机，外部存储权限成功").show();

                            joinPoint.proceed();//执行原方法

                        } else {
                            DialogUtil.showPermissionManagerDialog(activity, "相机");
                        }
                    }

                    @Override
                    public void onPermissionDenied(String[] deniedPermissions) {
                        StringBuilder sBuider = new StringBuilder();
                        for (String deniedPermission : deniedPermissions) {
                            if (deniedPermission.equals(Manifest.permission.CAMERA)) {
                                sBuider.append("相机");
                                sBuider.append(",");
                            }
                            if (deniedPermission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                                sBuider.append("读取存储");
                                sBuider.append(",");
                            }
                            if (deniedPermission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                sBuider.append("写入存储");
                                sBuider.append(",");
                            }
                        }

                        if (sBuider.length() > 0) sBuider.deleteCharAt(sBuider.length() - 1);
                        Toast.makeText(activity, "获取" + sBuider.toString() + "权限失败", Toast.LENGTH_SHORT).show();
                        if (XPermissionUtils.hasAlwaysDeniedPermission(activity, deniedPermissions)) {
                            DialogUtil.showPermissionManagerDialog(activity, sBuider.toString());
                        }

                    }
                }, new XPermissionUtils.RationaleHandler() {

                    @Override
                    protected void showRationale() {
                        new AlertDialog.Builder(activity)
                                .setTitle("温馨提示")
                                .setMessage("我们需要相机权限,存储权限才能正常使用该功能")
                                .setNegativeButton("取消", null)
                                .setPositiveButton("验证权限", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestPermissionsAgain();
                                    }
                                }).show();
                    }
                });

//        new AlertDialog.Builder(ac)
//                .setTitle("提示")
//                .setMessage("为了应用可以正常使用，请您点击确认申请权限。")
//                .setNegativeButton("取消", null)
//                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        MPermissionUtils.requestPermissionsResult(ac, 1, permission.value()
//                                , new MPermissionUtils.OnPermissionListener() {
//                                    @Override
//                                    public void onPermissionGranted() {
//                                        try {
//                                            joinPoint.proceed();//获得权限，执行原方法
//                                        } catch (Throwable e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onPermissionDenied() {
//                                        MPermissionUtils.showTipsDialog(ac);
//                                    }
//                                });
//                    }
//                })
//                .create()
//                .show();
    }
}


