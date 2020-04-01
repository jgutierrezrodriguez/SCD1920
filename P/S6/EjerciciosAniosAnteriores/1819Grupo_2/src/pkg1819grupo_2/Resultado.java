/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo_2;

import pkg1819grupo_2.Main.ElementoResultado;
import static pkg1819grupo_2.Main.ElementoResultado.MAYOR;
import static pkg1819grupo_2.Main.ElementoResultado.MENOR;
import static pkg1819grupo_2.Main.NULO;
import static pkg1819grupo_2.Main.ElementoResultado.APARICIONES_MAYOR;
import static pkg1819grupo_2.Main.ElementoResultado.APARICIONES_MENOR;


/**
 *
 * @author pedroj
 */
public class Resultado {
    private final int[] resultado;

    public Resultado(int valor) {
        this.resultado = new int[ElementoResultado.values().length];
        resultado[MAYOR.ordinal()] = valor;
        resultado[APARICIONES_MAYOR.ordinal()] = NULO;
        resultado[MENOR.ordinal()] = valor;
        resultado[APARICIONES_MENOR.ordinal()] = NULO;
    }
    
    public int getResultado(ElementoResultado elm) {
        return resultado[elm.ordinal()];
    }
    
    public void setResultado(ElementoResultado elm, int valor) {
        resultado[elm.ordinal()] = valor;
    }
    
    public void incrementar(ElementoResultado elm) {
        resultado[elm.ordinal()]++;
    }
    
    public void incrementar(ElementoResultado elm, int valor) {
        resultado[elm.ordinal()] += valor;
    }

    @Override
    public String toString() {
        String cadena = "";
        ElementoResultado[] elementos = ElementoResultado.values();
        
        for (ElementoResultado elm : elementos )
            cadena += elm + ":" + resultado[elm.ordinal()] + " ";
        return cadena;
    }
}