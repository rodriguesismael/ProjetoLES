/*
 * Classe Fatura
 */
package modelo.fatura;

import modelo.preco.Preco;

/**
 *
 * @author Ismael
 */
public class Fatura {

    private int codFatura;
    private String dataVencimento;
    private String dataPagamento;
    private boolean status;
    private Preco preco;
    private double diferenca;

    public int getCodFatura() {
        return codFatura;
    }

    public void setCodFatura(int codFatura) {
        this.codFatura = codFatura;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Preco getPreco() {
        return preco;
    }

    public void setPreco(Preco preco) {
        this.preco = preco;
    }

    public double getDiferenca() {
        return diferenca;
    }

    public void setDiferenca(double diferenca) {
        this.diferenca = diferenca;
    }
}
