package com.achek.learnhttp.presenters;

import androidx.annotation.Nullable;

import com.achek.learnhttp.repository.async.CallBack;
import com.achek.learnhttp.repository.network.RequestMessages;
import com.achek.learnhttp.view.MainActivityView;

public class MainActivityPresenter {

    private MainActivityView mainActivityView;
    private RequestMessages requestMessages;

    public MainActivityPresenter(RequestMessages requestMessages) {
        this.requestMessages = requestMessages;
    }

    public  void bind(MainActivityView view){
        mainActivityView = view;
    }
    public void deBind() {
        mainActivityView = null;
    }

    public void getMessages() {
        mainActivityView.startLoading();

        requestMessages.getMessages(new CallBack() {
            @Override
            public void callingBack(String[] result) {
                @Nullable
                String error = result[requestMessages.ERROR_RESULT_POSITION];
                @Nullable
                String messages = result[requestMessages.MESSAGES_RESULT_POSITION];
                if (mainActivityView != null) {
                    mainActivityView.endLoading();
                    if (error != null && error.length() != 0) mainActivityView.showError(error);
                    if (messages != null && messages.length() != 0) mainActivityView.setMassage(messages);
                }
            }
        });
    }
}

