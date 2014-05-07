/*
 * Classe Movimento
 */
package modelo.movimento;

import modelo.veiculo.Veiculo;
import modelo.cliente.Cliente;

/**
 *
 * @author Ismael
 *
 */
public class Movimento {

    private int codMovimento;
    private Cliente cliente;
    private Veiculo veiculo;
    private String dataInicio;
    private String dataTermino;

    public int getCodMovimento() {
        return codMovimento;
    }

    public void setCodMovimento(int codMovimento) {
        this.codMovimento = codMovimento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }
}
