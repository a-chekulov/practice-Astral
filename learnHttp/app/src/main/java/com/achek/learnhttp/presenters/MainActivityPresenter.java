package com.achek.learnhttp.presenters;

import android.app.Activity;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import com.achek.learnhttp.repository.model.Message;
import com.achek.learnhttp.repository.Utils.Utils;
import com.achek.learnhttp.view.MainActivityView;
import com.achek.learnhttp.repository.network.MessagesApi;
import com.achek.learnhttp.view.activites.MainActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityPresenter {

    private WeakReference<MainActivityView> mainActivityViewWeakReference;

    public MainActivityPresenter(WeakReference<MainActivityView> weakReference) {
        mainActivityViewWeakReference = weakReference;
    }

    public void getMessages() {

        mainActivityViewWeakReference.get().startLoading();

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
                    mainActivityViewWeakReference.get().endLoading();
                    mainActivityViewWeakReference.get().setMassage(messages);
                } else {
                    mainActivityViewWeakReference.get().endLoading();
                    mainActivityViewWeakReference.get().showError("Response code:" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                mainActivityViewWeakReference.get().endLoading();
                mainActivityViewWeakReference.get().showError("failure " + t);
            }
        });
    }
}