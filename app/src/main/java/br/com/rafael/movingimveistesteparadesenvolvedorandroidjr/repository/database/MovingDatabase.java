package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.repository.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.Usuario;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.repository.database.dao.UsuarioDAO;

@Database(entities = {Usuario.class}, version = 1)
public abstract class MovingDatabase extends RoomDatabase {

    public abstract UsuarioDAO usuarioDao();
}
