package com.example.locapp;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.text.Html;
import android.widget.TextView;

import java.util.Date;

public class MissedCall extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "CallLog";
    private static final int URL_LOADER = 1;

    private TextView callLogsTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setExistTheme(this);
        setContentView(R.layout.activity_misscall);
        callLogsTextView = (TextView) findViewById(R.id.call_logs);
        getLoaderManager().initLoader(URL_LOADER, null, MissedCall.this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderID, Bundle args) {
        switch (loaderID) {
            case URL_LOADER:
                // Returns a new CursorLoader
                return new CursorLoader(
                        this,   // Parent activity context
                        CallLog.Calls.CONTENT_URI,        // Table to query
                        null,     // Projection to return
                        null,            // No selection clause
                        null,            // No selection arguments
                        null             // Default sort order
                );
            default:
                return null;
        }

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor managedCursor) {
        StringBuilder sb = new StringBuilder();

        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);

        sb.append("<table>");

        while (managedCursor.moveToNext()) {
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String dir = null;

            int callTypeCode = Integer.parseInt(callType);
            switch (callTypeCode) {

                case CallLog.Calls.MISSED_TYPE:
                    dir = "Missed";
                    sb.append("<tr>")
                            .append("<td>Phone Number: </td>")
                            .append("<td><strong>")
                            .append(phNumber)
                            .append("</strong></td>");
                    sb.append("</tr>");
                    sb.append("<br/>");
                    sb.append("<tr>")
                            .append("<td>Date & Time:</td>")
                            .append("<td><strong>")
                            .append(callDayTime)
                            .append("</strong></td>");
                    sb.append("</tr>");
                    sb.append("<br/>");
                    sb.append("<tr>");
                    sb.append("<br>");
                    break;
            }
        }
        sb.append("</table>");
        managedCursor.close();
        callLogsTextView.setText(Html.fromHtml(sb.toString()));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}