package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.login;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.base.BaseView;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.AuthenticateType;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.Usuario;

public interface AuthenticateView extends BaseView {

    void validateAutentication(Usuario usuario);

    void isUserLogged(AuthenticateType type);

    void showLoading();

    void hideLoading();

    void onError(Throwable err);
}
