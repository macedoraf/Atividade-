package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.base;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.di.component.DaggerPresenterInjector;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.di.component.PresenterInjector;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.di.module.DatabaseModule;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.di.module.PreferencesModule;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.cadastroUsuario.CadastroUsuarioPresenter;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.listagemUsuario.ListarUsuarioPresenter;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.login.AuthenticatePresenter;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.navigator.NavigatorActivity;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.navigator.NavigatorPresenter;

public class  BasePresenter<V extends BaseView>{

    protected final V view;
    private  PresenterInjector injector;

    public BasePresenter(V view) {
        this.view = view;
        this.injector = DaggerPresenterInjector.builder()
                .databaseModule(new DatabaseModule(view.getContext()))
                .preferencesModule(new PreferencesModule(view.getContext()))
                .build();
        inject();

    }

    /**
     * Não descobri um jeito melhor de injetar ainda,
     * tentei fazer com generic não deu certo más penso
     * em trocar ssa forma de injeção para que eu tenha que configurar uma vez
     */
    private void inject() {
       if(this instanceof AuthenticatePresenter){
           injector.injectAuthenticate((AuthenticatePresenter) this);
       }else if (this instanceof CadastroUsuarioPresenter){
           injector.injectCadastroPresenter((CadastroUsuarioPresenter) this);
       }else if(this instanceof ListarUsuarioPresenter){
           injector.injectListaUsuarioPresenter((ListarUsuarioPresenter)this);
       }
       else if(this instanceof NavigatorPresenter){
           injector.injectNavigatorPresenter((NavigatorPresenter)this);
       }



    }

    public void onDestroy(){ }

    public void onStart() {

    }
}
