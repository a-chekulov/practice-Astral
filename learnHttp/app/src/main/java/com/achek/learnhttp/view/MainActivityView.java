package com.achek.learnhttp.view;

public interface MainActivityView {
    void setMassage(String text);
    void startLoading();
    void endLoading();
    void showError(String text);
}
