package android13.battery.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.RemoteViews;

public class BatteryWidgetProvider extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                // No need to call updateWidget here
                // Start the BatteryService to handle battery updates
                Intent serviceIntent = new Intent(context, BatteryListener.class);
                context.startService(serviceIntent);
            }
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // Update battery widget directly from BatteryService
        // Start the BatteryService to handle battery updates
        Intent serviceIntent = new Intent(context, BatteryListener.class);
        context.startService(serviceIntent);
    }
}