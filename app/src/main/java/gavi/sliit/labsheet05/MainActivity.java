package gavi.sliit.labsheet05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String BROADCAST_ACTION = "";
    private Receiver localListner;
    private Button mBroadcastBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Receiver localListener = new Receiver();
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListener, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListner);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn) {
            BackgroundService.startAction(this.getApplicationContext());
        }
    }

    static class Receiver extends  BroadcastReceiver {
        private TextView textView;
        @Override
        public void onReceive(Context context, Intent intent) {
            String currentText = textView.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nReceived " + message;
            textView.setText(currentText);
        }
    }
}
