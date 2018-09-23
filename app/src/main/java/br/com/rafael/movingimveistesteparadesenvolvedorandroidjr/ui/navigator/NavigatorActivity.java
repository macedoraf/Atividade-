package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.ui.navigator;

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

public class NavigatorActivity extends BaseActivity {

    protected DrawerLayout drawerLayout;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
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
                logout();
                break;
            case R.id.action_mapa:
                montaViewMapas();
                break;

        }
    }

    private void montaViewMapas() {

    }

    private void logout() {
    }

    private void montaViewUsuarios() {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ListarUsuarioFragment listarUsuarioFragment = new ListarUsuarioFragment();
        fragmentTransaction.addToBackStack("Assunto");
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
}
