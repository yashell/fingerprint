package com.example.fingerprint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nestia.biometriclib.BiometricPromptManager;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private BiometricPromptManager mManager;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mManager = BiometricPromptManager.from(this);


        mTextView = findViewById(R.id.text_view);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SDK version is "+ Build.VERSION.SDK_INT);
        stringBuilder.append("\n");
        stringBuilder.append("isHardwareDetected : "+mManager.isHardwareDetected());
        stringBuilder.append("\n");
        stringBuilder.append("hasEnrolledFingerprints : "+mManager.hasEnrolledFingerprints());
        stringBuilder.append("\n");
        stringBuilder.append("isKeyguardSecure : "+mManager.isKeyguardSecure());
        stringBuilder.append("\n");
        mTextView.setText(stringBuilder.toString());

        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mManager.isBiometricPromptEnable()) {
                    mManager.authenticate(new BiometricPromptManager.OnBiometricIdentifyCallback() {
                        @Override
                        public void onUsePassword() {
                            Toast.makeText(MainActivity.this, "onUsePassword", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSucceeded() {

                            Toast.makeText(MainActivity.this, "onSucceeded", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailed() {

                            Toast.makeText(MainActivity.this, "onFailed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(int code, String reason) {

                            Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancel() {

                            Toast.makeText(MainActivity.this, "onCancel", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "此手机不支持此功能", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
