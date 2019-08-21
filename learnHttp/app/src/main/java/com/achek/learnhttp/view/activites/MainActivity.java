package com.achek.learnhttp.view.activites;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.achek.learnhttp.R;
import com.achek.learnhttp.presenters.MainActivityPresenter;
import com.achek.learnhttp.view.MainActivityView;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    TextView tv_messages;
    Button btn_send_request;
    CircularProgressView cpv;
    MainActivityPresenter presenter;
    WeakReference<MainActivityView> weakReferenceActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_messages = findViewById(R.id.tv_messages);
        cpv = findViewById(R.id.cpv_send_request);
        btn_send_request = findViewById(R.id.btn_send_response);

        weakReferenceActivity = new WeakReference<MainActivityView>(this);

        presenter = new MainActivityPresenter(weakReferenceActivity);

        btn_send_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getMessages();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // presenter = null;

    }

    @Override
    public void setMassage(String text) {
        tv_messages.setText(text);
    }

    @Override
    public void startLoading() {
        tv_messages.setVisibility(View.INVISIBLE);
        cpv.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        cpv.setVisibility(View.GONE);
        tv_messages.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}

