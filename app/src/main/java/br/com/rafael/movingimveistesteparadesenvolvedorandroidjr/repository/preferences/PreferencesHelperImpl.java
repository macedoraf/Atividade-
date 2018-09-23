package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.repository.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.AuthenticateType;

public class PreferencesHelperImpl implements PreferencesHelper {

    private final SharedPreferences sharedPreferences;
    private static final String USER_PREFERENCES_LOGGED = "USER_PREFERENCES_LOGGED";
    private static final String USER_PREFERENCES_ID = "USER_PREFERENCES_ID";
    private static final String USER_PREFERENCES_USERNAME = "USER_PREFERENCES_USERNAME";
    private static final String USER_PREFERENCES_PASSWORD = "USER_PREFERENCES_PASSWORD";
    private static final String USER_PREFERENCES_NOME = "USER_PREFERENCES_NOME";
    private static final String MOVING_PREFS = "MOVING_PREFS";

    public PreferencesHelperImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences(MOVING_PREFS,Context.MODE_PRIVATE);
    }


    @Override
    public void setUserLoggedIn(AuthenticateType authenticateType) {
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(USER_PREFERENCES_LOGGED,authenticateType.getId());
        edit.apply();
    }

    @Override
    public AuthenticateType getUserLoggedIn() {
        final int anInt = sharedPreferences.getInt(USER_PREFERENCES_LOGGED, 0);
        switch (anInt){
            case 0:
                return  AuthenticateType.NOT_LOGGED;
            case 1:
                return  AuthenticateType.LOCAL;
            case 2:
                return  AuthenticateType.FACEBOOK;
            case 3:
                return  AuthenticateType.GOOGLE_PLUS;
            default:
                return  AuthenticateType.NOT_LOGGED;
        }

    }

    @Override
    public void setUserId(long userId) {
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(USER_PREFERENCES_ID,userId);
        edit.apply();
    }

    @Override
    public long getUserId() {
        return sharedPreferences.getLong(USER_PREFERENCES_ID, 0);
    }

    @Override
    public void setUsername(String username) {
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(USER_PREFERENCES_USERNAME,username);
        edit.apply();
    }

    @Override
    public String getUsername() {
        return sharedPreferences.getString(USER_PREFERENCES_USERNAME, "");
    }

    @Override
    public void setUserPassword(String password) {
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(USER_PREFERENCES_PASSWORD,password);
        edit.apply();
    }

    @Override
    public String getUserPassword() {
        return sharedPreferences.getString(USER_PREFERENCES_PASSWORD, "");
    }

    @Override
    public void setUserNome(String nome) {
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(USER_PREFERENCES_NOME,nome);
        edit.apply();
    }

    @Override
    public String getUserNome() {
        return sharedPreferences.getString(USER_PREFERENCES_NOME, "");
    }
}
