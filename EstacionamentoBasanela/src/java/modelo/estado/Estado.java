/*
 * Classe Estado
 */
package modelo.estado;

import modelo.cidade.Cidade;
import java.util.List;

/**
 *
 * @author Ismael
 */
public class Estado {

    private int codEstado;
    private String uf;
    private String descricao;
    private List<Cidade> listaCidade;

    public List<Cidade> getListaCidade() {
        return listaCidade;
    }

    public void setListaCidade(List<Cidade> listaCidade) {
        this.listaCidade = listaCidade;
    }

    public int getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(int codEstado) {
        this.codEstado = codEstado;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
