package br.com.rafael.movingimveistesteparadesenvolvedorandroidjr.model;

/**
 * Enum para saber por onde o usuario se autenticou
 */
public enum AuthenticateType {
    NOT_LOGGED(0),LOCAL(1),FACEBOOK(2),GOOGLE_PLUS(3)
    ;
    private final int id;

    AuthenticateType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
