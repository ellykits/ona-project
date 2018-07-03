package com.coder.nerdsoft.projectx.data.storage;

import android.content.SharedPreferences;

import com.coder.nerdsoft.projectx.data.util.DataConstants;
import com.coder.nerdsoft.projectx.data.model.OAuthToken;

import javax.inject.Inject;

public class TokenPersistence {

    private SharedPreferences mPrefs;

    @Inject
    public TokenPersistence(SharedPreferences prefs){
        mPrefs = prefs;
    }

    public void saveToken(OAuthToken token){
        mPrefs.edit().putString(DataConstants.AUTH_TOKEN, token.getAccessToken()).apply();
        mPrefs.edit().putString(DataConstants.REFRESH_TOKEN, token.getRefreshToken()).apply();
    }

    public boolean deleteToken(){

        if (mPrefs.contains(DataConstants.AUTH_TOKEN) &&  mPrefs.contains(DataConstants.REFRESH_TOKEN))  {
            mPrefs.edit().remove(DataConstants.AUTH_TOKEN).apply();
            mPrefs.edit().remove(DataConstants.REFRESH_TOKEN).apply();
            return true;
        }
        return false;
    }

    public OAuthToken getToken(){
        OAuthToken token = new OAuthToken();
        if (mPrefs.contains(DataConstants.AUTH_TOKEN) &&  mPrefs.contains(DataConstants.REFRESH_TOKEN))  {
            token.setAccessToken(mPrefs.getString(DataConstants.AUTH_TOKEN, null));
            token.setRefreshToken(mPrefs.getString(DataConstants.REFRESH_TOKEN, null));
        }
        return token;
    }

}
