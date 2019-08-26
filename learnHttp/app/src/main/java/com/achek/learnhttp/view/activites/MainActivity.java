package com.achek.learnhttp.view.activites;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.achek.learnhttp.R;
import com.achek.learnhttp.di.DaggerAppComponent;
import com.achek.learnhttp.di.DataModule;
import com.achek.learnhttp.presenters.MainActivityPresenter;
import com.achek.learnhttp.repository.network.RequestMessages;
import com.achek.learnhttp.view.MainActivityView;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private TextView tvMessages;
    private Button btnSendRequest;
    private CircularProgressView cpv;
    private MainActivityPresenter presenter;
    private RequestMessages requestMessages;

    @Inject
    protected Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DaggerAppComponent.builder().dataModule(new DataModule()).build().injectMainActivity(this);


        tvMessages = findViewById(R.id.tv_messages);
        cpv = findViewById(R.id.cpv_send_request);
        btnSendRequest = findViewById(R.id.btn_send_response);


        requestMessages = new RequestMessages(retrofit);

        presenter = new MainActivityPresenter(requestMessages);
        presenter.bind(this);

        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getMessages();
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.deBind();
        super.onDestroy();
    }

    @Override
    public void setMassage(String text) {
        tvMessages.setText(text);
    }

    @Override
    public void startLoading() {
        tvMessages.setVisibility(View.INVISIBLE);
        cpv.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        cpv.setVisibility(View.GONE);
        tvMessages.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}

