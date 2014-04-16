/*
 * Classe Cidade
 */
package modelo.cidade;

import modelo.estado.Estado;

/**
 *
 * @author Ismael
 */
public class Cidade {

    private int codCidade;
    private Estado estado;
    private String descricao;

    public int getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(int codCidade) {
        this.codCidade = codCidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
