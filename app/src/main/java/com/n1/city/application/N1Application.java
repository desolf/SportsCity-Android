package com.n1.city.application;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.n1.city.model.User;

public class N1Application extends Application {
    private static String PREFERENCE_NAME = "N1-City";

    public FirebaseAuth auth;
    public FirebaseDatabase database;
    public FirebaseStorage storage;

    private User currentUser;

    @Override
    public void onCreate() {
        super.onCreate();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    public User getCurrentUser() {
        if (currentUser == null && auth.getCurrentUser() != null) {
            currentUser = new User();
            currentUser.setUid(auth.getCurrentUser().getUid());
        }

        return currentUser;
    }

    public void logout() {
        auth.signOut();
        currentUser = null;
    }
}
