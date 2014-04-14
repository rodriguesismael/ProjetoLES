/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.fatura;
import modelo.movimento.Movimento;
import java.util.Date;       
/**
 *
 * @author Ismael
 */
public class Fatura {
    private int codFatura;
    private Date dataVencimento, dataPagamento;
    private boolean status;
    private Movimento movimento;

    public int getCodFatura() {
        return codFatura;
    }

    public void setCodFatura(int codFatura) {
        this.codFatura = codFatura;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Movimento getMovimento() {
        return movimento;
    }

    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
    }
    
}
