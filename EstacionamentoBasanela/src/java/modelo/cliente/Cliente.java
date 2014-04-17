/*
 * Classe Cliente
 */
package modelo.cliente;

import java.util.List;
import modelo.cidade.Cidade;
import modelo.estado.Estado;
import modelo.veiculo.Veiculo;

/**
 *
 * @author Ismael
 */
public class Cliente {

    private int codCliente;
    private int periodo;
    private String nome;
    private String endereco;
    private String telefone;
    private String celular;
    private Estado estado;
    private Cidade cidade;
    private List<Veiculo> listaVeiculo;

    /**
     * o atributo periodo está no diagrama do banco mas não no de classes **
     */
    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public List<Veiculo> getListaVeiculo() {
        return listaVeiculo;
    }

    public void setListaVeiculo(List<Veiculo> listaVeiculo) {
        this.listaVeiculo = listaVeiculo;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}
