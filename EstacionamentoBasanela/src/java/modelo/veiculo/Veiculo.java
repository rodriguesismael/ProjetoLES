/*
 * Classe Veiculo
 */
package modelo.veiculo;

import modelo.marca.Marca;
import modelo.modelo.Modelo;

/**
 *
 * @author Ismael
 */
public class Veiculo {

    private String placa;
    private String tipo;
    private Marca marca;
    private Modelo modelo;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
