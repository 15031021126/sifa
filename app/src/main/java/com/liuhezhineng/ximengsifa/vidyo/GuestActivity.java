package com.liuhezhineng.ximengsifa.vidyo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.liuhezhineng.ximengsifa.R;

public class GuestActivity extends Activity {

    private Uri mUri;

    EditText etRoomLink;

    EditText etDisplayName;

    EditText etPin;

    void doGuestJoinConference() {

        String displayName = etDisplayName.getText().toString().trim();
        String pin = etPin.getText().toString().trim();

        mUri = Uri.parse(etRoomLink.getText().toString());

        key.roomUrl = mUri.getHost();
        key.roomKey = mUri.getQueryParameter("key");
        key.displayName = displayName;
        key.pin = pin;

        Log.i("qingfeng", "doGuestJoinConference: " + key.content());

        setResult(RESULT_OK);

        finish();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guest);

        etRoomLink = (EditText) findViewById(R.id.et_portal_url);
        etRoomLink.setText("http://api.byshang.cn/flex.html?roomdirect.html&key=z0MMu9Kaou");
//        etRoomLink.setText("http://videocc.hrbb.com.cn/flex.html?roomdirect.html&key=I0NbfK6jpY");

        etDisplayName = (EditText) findViewById(R.id.et_portal_guest);
        etDisplayName.setText("Tester");

        etPin = (EditText) findViewById(R.id.et_pin_guest);

        Button button = (Button) findViewById(R.id.btn_next_guest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGuestJoinConference();
            }
        });

        Button login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(etRoomLink.getText().toString());
                key.roomUrl = uri.getHost();
                key.roomKey = uri.getQueryParameter("key");
                key.displayName = etDisplayName.getText().toString();
                key.pin = etPin.getText().toString();

                startActivity(new Intent(GuestActivity.this, ConferenceActivity.class));

            }
        });

        Intent intent = getIntent();
        mUri = intent.getParcelableExtra("uri");
    }
}
