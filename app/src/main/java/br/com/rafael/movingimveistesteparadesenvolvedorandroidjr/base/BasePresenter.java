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

/**
 *
 * @param <V> um tipo de objeto que implementa BaseView
 * Eu sempre crio essa classe em meus projetos, aprendi em um tutorial do site Medium,
 *  me economiza muito tempo e é muito facil de dar manutenção.
 */
public class  BasePresenter<V extends BaseView>{

    protected final V view;
    private  PresenterInjector injector;

    /**
     * Aqui eu configuro os modulos do base presentar pra injetar em todas as presentar que eu criar
     * o view é responsavel de me fornecer o context, eu poderia injetar essa dependecia também criando um
     * módulo de Context, más não vî necessidade...
     *
     * @param view
     */
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
     * em trocar essa forma de injeção para que eu tenha que configurar uma vez
     * toda vez que eu crio um presenter eu tenho que colocar outro metodo pra injetar :/
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
