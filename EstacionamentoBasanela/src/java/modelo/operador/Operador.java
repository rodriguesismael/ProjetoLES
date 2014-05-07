/*
 * Classe Operador
 */
package modelo.operador;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class Operador {

    private int codOperador;
    private String nome;
    private String login;
    private String senha;
    private Boolean administrador;
    private Boolean status;

    public int getCodOperador() {
        return codOperador;
    }

    public void setCodOperador(int codOperador) {
        this.codOperador = codOperador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
