package br.ufsm.csi.poow2.spring_rest_security.model;

public class Funcionario {

    private int id_func;
    private String nome_func;
    private String cargo;
    private String email_func;
    private String senha;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId_func() {
        return id_func;
    }

    public void setId_func(int id_func) {
        this.id_func = id_func;
    }

    public String getNome_func() {
        return nome_func;
    }

    public void setNome_func(String nome_func) {
        this.nome_func = nome_func;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail_func() {
        return email_func;
    }

    public void setEmail_func(String email_func) {
        this.email_func = email_func;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
