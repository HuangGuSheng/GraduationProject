package com.huanggusheng.graduationproject.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Huang on 2016/6/2.
 */
public class PostUtil {
    public static ProgressDialog showSpinnerDialog(Activity activity) {
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setMessage("正在..");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(true);
        dialog.show();
        return dialog;
    }

    public static void toast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static boolean filterException(Context ctx, Exception e) {
        if (e != null) {
            toast(ctx, e.getMessage());
            return false;
        } else {
            return true;
        }
    }
}
