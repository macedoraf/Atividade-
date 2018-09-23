package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.base;

import android.content.Context;

/**
 * é um contrato para me garantir que eu vou ter um context e eu poder associar, fragments, activitys, dialogs qualquer
 * tipo de tela a minha presenter
 */
public interface BaseView {

    Context getContext();
}
