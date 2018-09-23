package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.listagemUsuario;

import java.util.List;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.base.BaseView;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.Usuario;

public interface ListaUsuarioView extends BaseView {

    void update(List<Usuario> usuarioList);

    List<Usuario> getListaUsuarios();

    void showLoading();

    void hideLoading();

    void onError(Throwable err);

    void onClickUpdateSenha(Usuario usuario, int position);

    void updateAdapter(int position);

    void updateAdapter();
}
