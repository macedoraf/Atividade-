package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.cadastroUsuario;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.base.BaseView;

public interface Cadastroview extends BaseView {

    void showLoading();

    void hideLoading();

    void onSuccess();

    void onError(Throwable err);
}
