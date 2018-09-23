package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.di.component;

import javax.inject.Singleton;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.di.module.DatabaseModule;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.di.module.PreferencesModule;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.cadastroUsuario.CadastroUsuarioPresenter;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.listagemUsuario.ListarUsuarioPresenter;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.login.AuthenticatePresenter;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.navigator.NavigatorPresenter;
import dagger.Component;

/**
 * Aqui acho que não tem muito oq explicar, é um Componete para injetar as dependecias na presenter
 */
@Singleton
@Component(modules = {DatabaseModule.class, PreferencesModule.class})
public interface PresenterInjector {

    void injectAuthenticate(AuthenticatePresenter authenticatePresenter);

    void injectCadastroPresenter(CadastroUsuarioPresenter authenticatePresenter);

    void injectListaUsuarioPresenter(ListarUsuarioPresenter listarUsuarioPresenter);

    void injectNavigatorPresenter(NavigatorPresenter navigatorPresenter);

    @Component.Builder
    interface Builder{

        PresenterInjector build();

        Builder databaseModule(DatabaseModule databaseModule);

        Builder preferencesModule(PreferencesModule preferencesModule);



    }
}
