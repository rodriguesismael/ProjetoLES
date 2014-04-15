/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.clienteXveiculo;

import modelo.cliente.Cliente;
import modelo.veiculo.Veiculo;

/**
 *
 * @author Ismael
 */
public class ClienteXveiculo {
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
