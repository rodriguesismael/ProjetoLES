/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.estado;
import modelo.cidade.*;
import java.util.List;
/**
 *
 * @author Ismael
 */
public class Estado {
    private int codEstado;
    private String uf,descricao;
    private List<Cidade> listaCidade;

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
