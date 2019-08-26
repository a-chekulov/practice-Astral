package com.achek.learnhttp.di;

import com.achek.learnhttp.view.activites.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = DataModule.class)
public interface AppComponent {
    void injectMainActivity(MainActivity mainActivity);
}
