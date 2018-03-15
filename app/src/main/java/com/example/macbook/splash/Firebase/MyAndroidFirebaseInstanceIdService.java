package com.example.macbook.splash.Firebase;

import android.util.Log;

import com.example.macbook.splash.Interfaces.ApiClient;
import com.example.macbook.splash.Interfaces.IParentsApi;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyAndroidFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "MyAndroidFCMIIDService";

    @Override
    public void onTokenRefresh() {
        //Get hold of the registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Log the token
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);

    }
    private void sendRegistrationToServer(String token) {
        //Implement this method if you want to store the token on your server
        IParentsApi iParentsApi = ApiClient.getClient().create(IParentsApi.class);
        iParentsApi.postParentToken(1,token);
    }

}