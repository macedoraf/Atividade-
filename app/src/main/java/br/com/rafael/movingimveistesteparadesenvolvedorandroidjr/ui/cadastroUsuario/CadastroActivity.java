package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.cadastroUsuario;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.R;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.base.BaseActivity;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.Usuario;

/**
 * Activity de cadastro
 */
public class CadastroActivity extends BaseActivity implements View.OnClickListener, Cadastroview, TextWatcher {

    private TextInputEditText edtUsername;
    private TextInputEditText edtNome;
    private TextInputEditText edtSenha;
    private TextInputEditText edtConfirmaSenha;
    private Button btnCadastrar;
    private CadastroUsuarioPresenter presenterCadastroUsuario;
    private Usuario usuario;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        usuario =  new Usuario();
        initViews();
        setupListeners();
        presenterCadastroUsuario = new CadastroUsuarioPresenter(this);

    }

    private void setupListeners() {
        btnCadastrar.setOnClickListener(this);
        edtNome.addTextChangedListener(this);
        edtSenha.addTextChangedListener(this);
        edtConfirmaSenha.addTextChangedListener(this);
        edtUsername.addTextChangedListener(this);
    }

    private void initViews() {
        btnCadastrar = findViewById(R.id.btnCadastrar);
        edtUsername = findViewById(R.id.edtUsername);
        edtSenha = findViewById(R.id.edtSenha);
        edtNome = findViewById(R.id.edtNome);
        edtConfirmaSenha = findViewById(R.id.edtConfirmaSenha);


    }

    @Override
    public void onClick(View view) {
        if(isUsuarioValido())
        presenterCadastroUsuario.cadastraUsuario(usuario);
        else
            Toast.makeText(this, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show();
    }

    private boolean isUsuarioValido() {
        return !usuario.getNome().isEmpty() && !usuario.getPassword().isEmpty() && !usuario.getUsername().isEmpty();
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "Mostra o Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this, "Esconde o Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        this.finish();

    }

    @Override
    public void onError(Throwable err) {
        err.printStackTrace();
        Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(edtSenha != null && edtNome != null && edtUsername != null && edtConfirmaSenha != null){
            usuario.setPassword((isPasswordEqualsConfirmPassword())?edtConfirmaSenha.getText().toString():"");
            usuario.setNome(edtNome.getText().toString());
            usuario.setUsername(edtUsername.getText().toString());
        }

    }

    private boolean isPasswordEqualsConfirmPassword() {
        return edtSenha.getText().toString().equals(edtConfirmaSenha.getText().toString());
    }

    @Override
    protected void onDestroy() {
        presenterCadastroUsuario.onDestroy();
        super.onDestroy();

    }

}
