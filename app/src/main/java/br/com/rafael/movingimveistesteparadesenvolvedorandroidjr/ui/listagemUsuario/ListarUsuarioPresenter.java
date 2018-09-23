package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.listagemUsuario;

import android.annotation.SuppressLint;

import java.util.List;

import javax.inject.Inject;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.base.BasePresenter;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.Usuario;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.repository.database.dao.UsuarioDAO;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListarUsuarioPresenter extends BasePresenter<ListaUsuarioView> {

    @Inject
    UsuarioDAO usuarioDAO;

    public ListarUsuarioPresenter(ListaUsuarioView view) {
        super(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onStart() {
        super.onStart();
        view.showLoading();
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


    public void updateUserPassword(final Usuario usuario, final int position){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                usuarioDAO.update(usuario);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                view.showLoading();
            }

            @Override
            public void onComplete() {
                onStart();
                view.updateAdapter(position);
            }

            @Override
            public void onError(Throwable e) {
                view.onError(e);
            }
        });

    }
}
