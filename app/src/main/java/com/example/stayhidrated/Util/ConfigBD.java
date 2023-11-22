package com.example.stayhidrated.Util;

import com.google.firebase.auth.FirebaseAuth;

public class ConfigBD {


    private static FirebaseAuth auth;

    public static FirebaseAuth Firebaseautenticador(){

        if(auth ==null){
            auth = FirebaseAuth.getInstance();

        }
        return auth;
    }
}
