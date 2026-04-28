package com.hamad.homework1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
        implements Fragment1.OnNameSentListener,
                   Fragment2.OnUserDetailsSentListener,
                   Fragment3.OnConfirmationChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new Fragment1())
                    .commit();
        }
    }

    @Override
    public void onNameSent(String name) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, Fragment2.newInstance(name))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onUserDetailsSent(String name, String email, String gender,
                                  String age, String university) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, Fragment3.newInstance(name))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onConfirmationChanged(boolean isChecked) {
        int msg = isChecked ? R.string.host_confirmed : R.string.host_unconfirmed;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
