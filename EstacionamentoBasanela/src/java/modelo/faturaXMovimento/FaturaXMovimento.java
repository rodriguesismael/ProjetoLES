/*
 * Classe FaturaXMovimento
 */
package modelo.faturaXMovimento;

import modelo.fatura.Fatura;
import modelo.movimento.Movimento;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class FaturaXMovimento {

    private Fatura fatura;
    private Movimento movimento;

    public Fatura getFatura() {
        return fatura;
    }

    public void setFatura(Fatura fatura) {
        this.fatura = fatura;
    }

    public Movimento getMovimento() {
        return movimento;
    }

    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
    }

}
