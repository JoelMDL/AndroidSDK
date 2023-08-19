package com.test.batterywidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.RemoteViews;

public class BatteryWidgetProvider extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                updateBatteryWidget(context, AppWidgetManager.getInstance(context), appWidgetId);
            }
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateBatteryWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private void updateBatteryWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        int batteryPercentage = getBatteryPercentage(context);
        views.setProgressBar(R.id.batteryBar, 100, batteryPercentage, false);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private int getBatteryPercentage(Context context) {
        Intent batteryStatus = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        if (scale != 0) {
            return (int) ((level / (float) scale) * 100);
        } else {
            return 0;
        }
    }
}
