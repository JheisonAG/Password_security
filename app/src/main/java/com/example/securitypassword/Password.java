package com.example.securitypassword;

public class Password {
    private final String servicio;
    private final String usuario;
    private final String password;

    public Password(String servicio, String usuario, String password) {
        this.servicio = servicio;
        this.usuario = usuario;
        this.password = password;
    }

    public String getServicio() { return servicio; }
    public String getUsuario()  { return usuario;  }
    public String getPassword() { return password; }
}
