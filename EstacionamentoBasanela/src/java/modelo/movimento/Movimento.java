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
    private String data_inicio;
    private String data_termino;
    private Double valor;
    private Veiculo veiculo;
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getCodMovimento() {
        return codMovimento;
    }

    public void setCodMovimento(int codMovimento) {
        this.codMovimento = codMovimento;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_termino() {
        return data_termino;
    }

    public void setData_termino(String data_termino) {
        this.data_termino = data_termino;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
