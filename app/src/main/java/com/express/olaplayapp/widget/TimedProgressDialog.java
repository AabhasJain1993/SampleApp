package com.express.olaplayapp.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

import java.util.concurrent.TimeUnit;

/**
 * Created by Aabhas_Jain on 12/19/2017.
 */

public class TimedProgressDialog extends ProgressDialog {
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            dismiss();
        }
    };

    public TimedProgressDialog(Context context) {
        super(context);
    }

    public TimedProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public void show(long timeout, TimeUnit unit) {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout < 0");
        }
        if (unit == null) {
            throw new IllegalArgumentException("unit == null");
        }
        long millis = unit.toMillis(timeout);
        if (millis > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Timeout too large.");
        }
        if (millis == 0 && timeout > 0) {
            throw new IllegalArgumentException("Timeout too small.");
        }
        super.show();
        handler.postDelayed(runnable, millis);
    }

    @Override
    public void dismiss() {
        handler.removeCallbacks(runnable);
        super.dismiss();
    }
}

