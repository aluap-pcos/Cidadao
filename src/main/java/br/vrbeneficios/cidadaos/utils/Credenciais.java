package br.vrbeneficios.cidadaos.utils;

import java.time.Instant;
import java.util.Date;

public class Credenciais {
    private static String senha = "carryon321";
    private static String usuario = "root";
    private static String url = "jdbc:mysql://localhost/cidadaos?useTimezone=true&serverTimezone=UTC";

    public static String getSenha() {
        return senha;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static String getUrl() {
        return url;
    }
}
