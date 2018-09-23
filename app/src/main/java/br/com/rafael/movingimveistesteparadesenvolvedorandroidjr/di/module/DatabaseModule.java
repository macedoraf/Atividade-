package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.repository.database.MovingDatabase;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.repository.database.dao.UsuarioDAO;
import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private MovingDatabase database;

    public DatabaseModule(Context context) {
        database = Room.databaseBuilder(context,MovingDatabase.class,"moving_db").build();
    }

    @Provides
    @Singleton
    public MovingDatabase providesMovingDatabase(){
        return database;
    }


    @Provides
    @Singleton
    public UsuarioDAO provideUsuarioDao(MovingDatabase database){
        return database.usuarioDao();
    }



}
