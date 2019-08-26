package com.achek.learnhttp.repository.network;

import com.achek.learnhttp.repository.Utils.Utils;
import com.achek.learnhttp.repository.async.CallBack;
import com.achek.learnhttp.repository.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RequestMessages {


    public final int ERROR_RESULT_POSITION = 0;
    public final int MESSAGES_RESULT_POSITION = 1;

    private Retrofit retrofit;
    private String messagesString;
    private String errorString;
    private String[] resultString = new String[2];

    public RequestMessages(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public void getMessages(final CallBack iCallBack) {

        MessagesApi messagesApi = retrofit.create(MessagesApi.class);
        final Call<List<Message>> messages = messagesApi.messages();

        messages.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful()) {
                    messagesString = "count: " + response.body().size() + "\n";
                    for (int i = 0; i < response.body().size(); i++) {
                        Message item = response.body().get(i);
                        messagesString += item.getId() + " " + Utils.getDataTimeInMillis(item.getTime()) + " " + "\n";
                    }
                    resultString[MESSAGES_RESULT_POSITION] = messagesString;
                    iCallBack.callingBack(resultString);
                } else {
                    messagesString = "Error! Response code:" + response.code();
                    resultString[MESSAGES_RESULT_POSITION] = messagesString;
                    iCallBack.callingBack(resultString);
                }
            }
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                errorString = "failure " + t;
                resultString[ERROR_RESULT_POSITION] = errorString;
                iCallBack.callingBack(resultString);
            }
        });
    }
}