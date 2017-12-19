package com.express.olaplayapp.utils;

import com.express.olaplayapp.api.APIClient;
import com.express.olaplayapp.api.SongsAPI;

/**
 * Created by Aabhas_Jain on 12/5/2017.
 */

public class ApiHelper {

    private static SongsAPI mSongsApi = null;
    public static SongsAPI getAPInterface() {

        if(mSongsApi == null) {
            mSongsApi = APIClient.getInstance().create(SongsAPI.class);
        }

        return mSongsApi;
    }
}
