package com.wpmac.tmvpdemo;

import android.Manifest;
import android.os.Bundle;

import com.app.annotation.aspect.Permission;
import com.app.annotation.aspect.TimeLog;
import com.wpmac.tmvpdemo.base.BaseActivity;

public class MainActivity extends BaseActivity {



//    @TimeLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //方法运行时间注解
        runTimeLogtest();
        //权限申请注解
        permissonterst();
    }

    @TimeLog
    @Permission({Manifest
            .permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA})
    private void permissonterst() {
    }

    @TimeLog
    private void runTimeLogtest() {
    }
}
