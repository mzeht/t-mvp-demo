package com.wpmac.tmvpdemo.base.widget;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by wpmac on 16/1/18.
 */
public class ToastWidget {


    public static Toast createDialog(Context mContext, String message)
    {
        Toast toast = Toast.makeText(mContext, message,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER , 0, 80);
        toast.show();

        return toast;

    }
}
