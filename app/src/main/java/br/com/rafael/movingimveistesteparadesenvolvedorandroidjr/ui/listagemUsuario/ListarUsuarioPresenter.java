package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.listagemUsuario;

import java.util.List;

import javax.inject.Inject;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.base.BasePresenter;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.Usuario;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.repository.database.dao.UsuarioDAO;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListarUsuarioPresenter extends BasePresenter<ListaUsuarioView> {

    @Inject
    UsuarioDAO usuarioDAO;

    public ListarUsuarioPresenter(ListaUsuarioView view) {
        super(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        usuarioDAO.selectAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Usuario>>() {
                    @Override
                    public void accept(List<Usuario> usuarios) throws Exception {
                        view.update(usuarios);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.onError(throwable);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        view.hideLoading();
                    }
                });


    }
}
