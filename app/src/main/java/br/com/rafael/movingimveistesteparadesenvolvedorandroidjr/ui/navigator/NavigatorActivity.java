package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.navigator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.R;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.base.BaseActivity;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.listagemUsuario.ListarUsuarioFragment;
import br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.login.AuthenticateActivity;

public class NavigatorActivity extends BaseActivity implements NavigatorView {

    protected DrawerLayout drawerLayout;
    public Toolbar toolbar;
    private NavigatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        presenter = new NavigatorPresenter(this);
        initViews();
        setupListeners();
        setupToolbar();
        setStarterView();
    }

    private void setStarterView() {
        montaViewUsuarios();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setupNavDrawer();
    }

    private void setupListeners() {

    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
    }


    protected void setupNavDrawer() {
        // Drawer Layout
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Ícone do menu do nav drawer
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_name);
            actionBar.setDisplayHomeAsUpEnabled(true);
            drawerLayout = findViewById(R.id.drawer_layout);
            NavigationView navigationView =  findViewById(R.id.nav_view);

            if (navigationView != null && drawerLayout != null) {
                // Atualiza os dados do header do Navigation View

                // Trata o evento de clique no menu.
                navigationView.setNavigationItemSelectedListener(
                        new NavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(MenuItem menuItem) {
                                // Seleciona a linha
                                menuItem.setChecked(true);
                                // Fecha o menu
                                drawerLayout.closeDrawers();
                                // Trata o evento do menu
                                onNavDrawerItemSelected(menuItem);
                                return true;
                            }
                        });
            }
        }
    }

    private void onNavDrawerItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_usuarios:
                montaViewUsuarios();
                break;
            case R.id.action_logout:
                presenter.logoutUser();
                break;
            case R.id.action_mapa:
                montaViewMapas();
                break;

        }
    }

    private void montaViewMapas() {

    }


    private void montaViewUsuarios() {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ListarUsuarioFragment listarUsuarioFragment = new ListarUsuarioFragment();
        fragmentTransaction.replace(R.id.frame, listarUsuarioFragment);
        fragmentTransaction.commit();
        closeDrawer();

    }

    protected void openDrawer() {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    // Fecha o menu lateral
    protected void closeDrawer() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void logout() {
        startActivity(new Intent(this, AuthenticateActivity.class));
        this.finish();
    }
}
