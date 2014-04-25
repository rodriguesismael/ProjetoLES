/*
 * Classe TipoAcesso
 */
package modelo.tipoAcesso;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class TipoAcesso {

    private int codTipoAcesso;
    private String descricao;

    public int getCodTipoAcesso() {
        return codTipoAcesso;
    }

    public void setCodTipoAcesso(int codTipoAcesso) {
        this.codTipoAcesso = codTipoAcesso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
