package com.test.batterywidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class BatteryWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        // You can add other widget updates using views.setXXX methods here
        // For example:
        // views.setProgressBar(R.id.batteryProgressBarWidget, 100, batteryPercent, false);
        // views.setTextViewText(R.id.batteryPercentageWidget, batteryPercent + "%");

        // Update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}
