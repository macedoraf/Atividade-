package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.repository.preferences;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.AuthenticateType;

public interface PreferencesHelper {

    void setUserLoggedIn(AuthenticateType authenticateType);

    AuthenticateType getUserLoggedIn();

    void setUserId(long userId);

    long getUserId();

    void setUsername(String username);

    String getUsername();

    void setUserPassword(String password);

    String getUserPassword();

    void setUserNome(String nome);

    String getUserNome();
}
