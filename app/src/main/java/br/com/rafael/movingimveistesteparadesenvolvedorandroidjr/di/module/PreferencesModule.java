package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.di.module;

import android.content.Context;

import javax.inject.Singleton;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.repository.preferences.PreferencesHelperImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Aqui é o modulo de Preferencias,
 * eu crio uma Interface Helper para me devolver os metodos que quero e dou bind apartir do HelperImpl
 */
@Module
public class PreferencesModule {

    Context context;

    public PreferencesModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public PreferencesHelperImpl providePreferencesHelper(){
        return new PreferencesHelperImpl(context);
    }






}
