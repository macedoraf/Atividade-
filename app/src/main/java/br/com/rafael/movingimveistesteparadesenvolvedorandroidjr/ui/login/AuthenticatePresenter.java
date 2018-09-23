package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.login;

import javax.inject.Inject;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.base.BasePresenter;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.AuthenticateType;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.Usuario;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.repository.database.dao.UsuarioDAO;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.repository.preferences.PreferencesHelper;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.repository.preferences.PreferencesHelperImpl;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class AuthenticatePresenter extends BasePresenter<AuthenticateView> {

    @Inject
    UsuarioDAO usuarioDAO;
    @Inject
    PreferencesHelperImpl preferencesHelper;

    private Disposable disposable;


    public AuthenticatePresenter(AuthenticateView view) {
        super(view);
    }


    /**
     * OBS: Não consegui recuperar o erro do banco e mostrar na tela usuario inválido.
     * @param username
     * @param senha
     */
    public void validateEmailAndSenha(String username, String senha){
        disposable = usuarioDAO.findByUsernameAndSenha(username,senha)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Usuario>() {
                               @Override
                               public void accept(Usuario usuario) throws Exception {
                                view.validateAutentication(usuario);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                view.onError(throwable);
                            }
                        },
                        new Action() {
                            @Override
                            public void run() throws Exception {
                                view.hideLoading();
                            }
                        });


    }

    public void burnUserIntoPreferences(Usuario usuario, AuthenticateType authenticateType){
        preferencesHelper.setUserLoggedIn(authenticateType);
        preferencesHelper.setUserId(usuario.getId());
        preferencesHelper.setUsername(usuario.getUsername());
        preferencesHelper.setUserPassword(usuario.getPassword());
        preferencesHelper.setUserNome(usuario.getNome());
    }

    public void isUserLoggedIn(){
        view.isUserLogged(preferencesHelper.getUserLoggedIn());

    }

    @Override
    public void onDestroy() {
        disposable.dispose();
        super.onDestroy();

    }
}
