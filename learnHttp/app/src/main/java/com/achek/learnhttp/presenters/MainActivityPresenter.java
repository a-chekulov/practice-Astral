package com.achek.learnhttp.presenters;

import android.content.Context;
import android.view.View;

import com.achek.learnhttp.activites.MainActivity;
import com.achek.learnhttp.model.Message;
import com.achek.learnhttp.model.Utils;
import com.achek.learnhttp.view.MainActivityView;
import com.achek.learnhttp.view.MessagesApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityPresenter {

    private MainActivityView view;

    public MainActivityPresenter(MainActivityView view) {
        this.view = view;
    }

    public void sendRequest() {

        view.startLoading();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MessagesApi messagesApi = retrofit.create(MessagesApi.class);

        final Call<List<Message>> messages = messagesApi.messages();
        messages.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful()) {
                    String messages = "count: " + response.body().size() + "\n";
                    for (int i = 0; i < response.body().size(); i++) {
                        Message item = response.body().get(i);
                        messages += item.getId() + " " + Utils.getDataTimeInMillis(item.getTime()) + " " + "\n";
                    }
                    view.endLoading();
                    view.setMassage(messages);
                } else {
                    view.endLoading();
                    view.showError("Response code:" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                view.endLoading();
                view.showError("failure " + t);
            }
        });
    }
}