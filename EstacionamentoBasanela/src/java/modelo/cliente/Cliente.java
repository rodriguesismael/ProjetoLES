/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cliente;
import modelo.estado.*;
import modelo.cidade.*;
import modelo.veiculo.*;
import java.util.List;
/**
 *
 * @author Ismael
 */
public class Cliente {
    private int codCliente,periodo;
    /**o atributo periodo está no diagrama do banco
     * mas não no de classes
     ****/

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
    private String nome,endereco,telefone,celular;
    private Estado estado;
    private Cidade cidade;
    private List<Veiculo> listaVeiculo;

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
