package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.listagemUsuario.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.R;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.Usuario;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.listagemUsuario.ListaUsuarioView;

public class UsuarioHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView lblId;
    private final TextView lblNome;
    private final TextView lblUsername;
    private final TextView lblSenha;
    private final ListaUsuarioView view;

    public UsuarioHolder(@NonNull View itemView, ListaUsuarioView view) {
        super(itemView);
        this.view = view;
        lblId = itemView.findViewById(R.id.lblId);
        lblNome = itemView.findViewById(R.id.lblNome);
        lblUsername = itemView.findViewById(R.id.lblUsername);
        lblSenha = itemView.findViewById(R.id.lblSenha);
        itemView.findViewById(R.id.cardView).setOnClickListener(this);
    }

    public void bind(Usuario usuario){
        lblId.setText(String.format("ID: %d", usuario.getId()));
        lblNome.setText(String.format("Nome: %s", usuario.getNome()));
        lblUsername.setText(String.format("Username: %s", usuario.getUsername()));
        lblSenha.setText(String.format("Senha:%s", usuario.getPassword()));


    }

    @Override
    public void onClick(View view) {
        this.view.onClickUpdateSenha();
    }
}
