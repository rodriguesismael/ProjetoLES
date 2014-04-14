/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.movimento;
import modelo.veiculo.Veiculo;
import java.util.Date;
/**
 *
 * @author Ismael
 * 
 * OBS: Achei por bem alterar o tipo de dado das Datas (que estão como string no diagrama de classes)
 */
public class Movimento {
    private int codMovimento;
    private Date data_inicio, data_termino;
    private Double valor;
    private Veiculo veiculo;

    public int getCodMovimento() {
        return codMovimento;
    }

    public void setCodMovimento(int codMovimento) {
        this.codMovimento = codMovimento;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_termino() {
        return data_termino;
    }

    public void setData_termino(Date data_termino) {
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
