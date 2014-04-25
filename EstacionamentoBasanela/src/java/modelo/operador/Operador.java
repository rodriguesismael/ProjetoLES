/*
 * Classe Operador
 */
package modelo.operador;

import modelo.tipoAcesso.TipoAcesso;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class Operador {

    private int codOperador;
    private String nome;
    private String login;
    private String senha;
    private TipoAcesso tipoAcesso;

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

    public TipoAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }
}
