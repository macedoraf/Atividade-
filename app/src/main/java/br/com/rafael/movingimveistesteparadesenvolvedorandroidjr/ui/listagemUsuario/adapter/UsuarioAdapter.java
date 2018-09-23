package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.listagemUsuario.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.R;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.listagemUsuario.ListaUsuarioView;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.listagemUsuario.adapter.holder.UsuarioHolder;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioHolder> {

    private final ListaUsuarioView listaUsuarioView;

    public UsuarioAdapter(ListaUsuarioView listaUsuarioView) {
        this.listaUsuarioView = listaUsuarioView;
    }

    @NonNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UsuarioHolder(LayoutInflater.from(listaUsuarioView.getContext()).inflate(R.layout.lst_usuario,viewGroup,
                false),listaUsuarioView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder usuarioHolder, int position) {
        usuarioHolder.bind(listaUsuarioView.getListaUsuarios().get(position));
    }

    @Override
    public int getItemCount() {
        return listaUsuarioView.getListaUsuarios().size();
    }
}
