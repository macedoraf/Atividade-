package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.listagemUsuario;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.R;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.base.BaseFragment;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.Usuario;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.listagemUsuario.adapter.UsuarioAdapter;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.navigator.NavigatorActivity;

public class ListarUsuarioFragment extends BaseFragment implements ListaUsuarioView {
    private RecyclerView recyclerView;
    private NavigatorActivity mActivity;
    private ProgressBar progressBar;
    private ListarUsuarioPresenter presenter;
    private List<Usuario> usuarioList;
    private UsuarioAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (NavigatorActivity) getActivity();
        usuarioList = new ArrayList<>();
        presenter = new ListarUsuarioPresenter(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_usuarios,container,false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.toolbar.setTitle(R.string.lista_de_usuarios);
        setupRecyclerView();
        setupAdapter();

    }


    private void setupAdapter() {
        adapter = new UsuarioAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
    }


    @Override
    public void update(List<Usuario> usuarioList) {
        this.usuarioList.clear();
        this.usuarioList.addAll(usuarioList);
        this.hideLoading();
    }

    @Override
    public List<Usuario> getListaUsuarios() {
        return usuarioList;
    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(Throwable err) {
    err.printStackTrace();
        Toast.makeText(mActivity, err.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickUpdateSenha(final Usuario usuario, final int position) {
        LinearLayout view = new LinearLayout(getContext());
        final EditText edtNovaSenha = new EditText(getContext());
        edtNovaSenha.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        view.addView(edtNovaSenha);
        AlertDialog alertDialog = new AlertDialog.Builder(mActivity)
                .setTitle("Alterar senha")
                .setMessage("Digite sua nova senha")
                .setView(view)
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!edtNovaSenha.getText().toString().isEmpty()){
                            usuario.setPassword(edtNovaSenha.getText().toString());
                        }
                        presenter.updateUserPassword(usuario,position);
                    }
                })
                .setNegativeButton("Cancelar",null)
                .create();

        alertDialog.show();
    }


    @Override
    public void updateAdapter(int position) {
        adapter.notifyItemChanged(position);
    }

    @Override
    public void updateAdapter() {
        adapter.notifyDataSetChanged();
    }




}
