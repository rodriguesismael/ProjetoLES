/*
 * Classe Modelo
 */
package modelo.modelo;

import modelo.marca.Marca;

/**
 *
 * @author Ismael
 */
public class Modelo {

    private int codModelo;
    private Marca marca;
    private String descricao;

    public int getCodModelo() {
        return codModelo;
    }

    public void setCodModelo(int codModelo) {
        this.codModelo = codModelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
