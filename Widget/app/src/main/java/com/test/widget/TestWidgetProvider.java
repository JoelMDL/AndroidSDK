package com.test.widget;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class TestWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int testWidgetId : appWidgetIds) {

            Intent intent = new Intent(context, MainActivity.class);
            @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.test_widget);
            views.setOnClickPendingIntent(R.id.testBtn, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetIds, views );


        }
    }
}
