package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.navigator;

import javax.inject.Inject;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.base.BasePresenter;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.AuthenticateType;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.repository.preferences.PreferencesHelperImpl;

/**
 * Presenter da activity Navigator
 */
public class NavigatorPresenter extends BasePresenter<NavigatorView> {

    @Inject
    PreferencesHelperImpl preferencesHelper;

    public NavigatorPresenter(NavigatorView view) {
        super(view);
    }

    public void logoutUser(){
        preferencesHelper.setUserLoggedIn(AuthenticateType.NOT_LOGGED);
        view.logout();
    }
}
