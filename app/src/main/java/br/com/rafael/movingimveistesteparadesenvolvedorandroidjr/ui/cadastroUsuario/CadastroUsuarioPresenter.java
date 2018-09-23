package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.cadastroUsuario;

import javax.inject.Inject;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.base.BasePresenter;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.Usuario;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.repository.database.dao.UsuarioDAO;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter de cadastro
 */
public class CadastroUsuarioPresenter extends BasePresenter<Cadastroview> {

    @Inject
    UsuarioDAO usuarioDAO;

    public CadastroUsuarioPresenter(Cadastroview view) {
        super(view);
    }


    public void cadastraUsuario(final Usuario usuario){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
            usuarioDAO.insert(usuario);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            view.hideLoading();
            }

            @Override
            public void onComplete() {
            view.onSuccess();
            }

            @Override
            public void onError(Throwable e) {
                view.onError(e);
            }
        });

    }

}
