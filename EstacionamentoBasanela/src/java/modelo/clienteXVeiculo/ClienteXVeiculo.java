/*
 * Classe ClienteXVeiculo
 */
package modelo.clienteXVeiculo;

import modelo.cliente.Cliente;
import modelo.veiculo.Veiculo;

/**
 *
 * @author Ismael
 */
public class ClienteXVeiculo {

    private Cliente cliente;
    private Veiculo veiculo;

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
}
