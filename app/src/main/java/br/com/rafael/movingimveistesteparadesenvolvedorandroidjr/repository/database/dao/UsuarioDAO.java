package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.repository.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.Usuario;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UsuarioDAO {

    @Insert(onConflict = REPLACE)
    void insert(Usuario usuario);

    @Delete
    void delete(Usuario usuario);

    @Update
    void update(Usuario usuario);

    @Query("SELECT * FROM Usuario")
    Flowable<List<Usuario>> selectAll();

    @Query("SELECT * FROM Usuario WHERE username = :email AND password = :senha")
    Maybe<Usuario> findByUsernameAndSenha(String email, String senha);
}
