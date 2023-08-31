package android13.battery.widget;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.appwidget.AppWidgetManager;
import android.view.View;
import android.widget.RemoteViews;

public class BatteryListener extends Service {

    private BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                updateWidgets(context);
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, intentFilter);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void updateWidgets(Context context) {
        int batteryPercentage = getBatteryPercentage(context);
        boolean isCharging = isCharging(context);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] widgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, BatteryWidgetProvider.class));

        for (int appWidgetId : widgetIds) {
            updateWidget(context, appWidgetId, batteryPercentage, isCharging);
        }
    }

    private boolean isCharging(Context context) {
        Intent batteryStatus = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        return status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
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

    private void updateWidget(Context context, int appWidgetId, int batteryLevel, boolean isCharging) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        if (isCharging) {
            views.setViewVisibility(R.id.phoneIco, View.VISIBLE);
            views.setViewVisibility(R.id.chargeIcon, View.VISIBLE);
            views.setTextViewText(R.id.batteryPercentage, "");
        } else {
            views.setViewVisibility(R.id.phoneIco, View.VISIBLE);
            views.setViewVisibility(R.id.chargeIcon, View.GONE);
            views.setTextViewText(R.id.batteryPercentage, batteryLevel + "%");
        }

        views.setProgressBar(R.id.batteryBar, 100, batteryLevel, false);
        AppWidgetManager.getInstance(context).updateAppWidget(appWidgetId, views);
    }



}