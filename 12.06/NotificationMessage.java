package com.yehudit.powerwomen;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationMessage extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_message_activity);
        textView = findViewById(R.id.tv_message);
        Bundle bundle = getIntent().getExtras();
        textView.setText(bundle.getString("message"));

    }
}
