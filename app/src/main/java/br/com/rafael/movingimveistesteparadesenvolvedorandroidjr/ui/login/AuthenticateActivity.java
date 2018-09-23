package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.R;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.base.BaseActivity;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.AuthenticateType;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model.Usuario;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.cadastroUsuario.CadastroActivity;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.navigator.NavigatorActivity;

public class AuthenticateActivity extends BaseActivity implements AuthenticateView, View.OnClickListener {

    private AuthenticatePresenter presenter;

    private TextInputEditText edtLogin;

    private TextInputEditText edtSenha;

    private FrameLayout frameCadastro;

    private Button btnLogin;

    public static final int CADASTRO_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new AuthenticatePresenter(this);

        initViews();
        setupListeners();

    }

    private void setupListeners() {
        btnLogin.setOnClickListener(this);
        frameCadastro.setOnClickListener(this);
    }

    private void initViews() {
        edtLogin = findViewById(R.id.edtLogin);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);
        frameCadastro = findViewById(R.id.frameCadastro);

    }

    @Override
    public void validateAutentication(Usuario usuario) {
        if(usuario != null){
            presenter.burnUserIntoPreferences(usuario,AuthenticateType.LOCAL);
            startNavigatorActivity();

        }
    }

    private void startNavigatorActivity() {
        startActivity(new Intent(this, NavigatorActivity.class));
        this.finish();
    }

    @Override
    public void isUserLogged(AuthenticateType type) {
        if(type != AuthenticateType.NOT_LOGGED){
            startNavigatorActivity();
        }else
            Toast.makeText(this, "Teste", Toast.LENGTH_SHORT).show();
    }

    private void startActivityCadastro(){
        startActivityForResult(new Intent(this, CadastroActivity.class),CADASTRO_CODE);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable err) {
        Toast.makeText(this, "Usuario ou senha inválidos!", Toast.LENGTH_LONG).show();
        err.printStackTrace();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {
        try{
            if(view == frameCadastro){
                startActivityCadastro();
            }else if(view == btnLogin){
                presenter.validateEmailAndSenha(edtLogin.getText().toString(),
                        edtSenha.getText().toString());
            }
        }catch (Exception err){
            err.printStackTrace();
        }


    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();

    }
}
