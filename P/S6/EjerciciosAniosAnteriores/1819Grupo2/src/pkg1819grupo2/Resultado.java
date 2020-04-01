/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo2;

import static pkg1819grupo2.Main.IMPAR;
import static pkg1819grupo2.Main.PAR;
import static pkg1819grupo2.Main.RESULTADOS;

/**
 *
 * @author pedroj
 */
public class Resultado {
    private final int[] resultado;

    public Resultado() {
        this.resultado = new int[RESULTADOS];
        for ( int i = 0; i < RESULTADOS; i++ )
            resultado[i] = 0;
    }
    
    public int getResultado(int tipo) {
        return resultado[tipo];
    }
    
    public void setResultado(int tipo, int valor) {
        resultado[tipo] = valor;
    }
    
    public void incrementar(int tipo) {
        resultado[tipo]++;
    }
    
    public void incrementar(int tipo, int valor) {
        resultado[tipo] += valor;
    }

    @Override
    public String toString() {
        return "TotalPares = " + resultado[PAR] +
               ", TotalImpares = " + resultado[IMPAR];
    }
}