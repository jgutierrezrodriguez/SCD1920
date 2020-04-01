/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo4;

import static pkg1819grupo4.Main.NULO;

/**
 *
 * @author pedroj
 */
public class Resultado {
    private final int valor;
    private int aparicion;

    public Resultado(int valor) {
        this.valor = valor;
        this.aparicion = NULO;
    }

    public int getValor() {
        return valor;
    }

    public int getAparicion() {
        return aparicion;
    }
    
    public void setAparicion(int aparicion) {
        this.aparicion = aparicion;
    }
    
    public void incrementar() {
        this.aparicion++;
    }
    
    public void incrementar(int valor) {
        this.aparicion += valor;
    }

    @Override
    public String toString() {
        return "ValorBuscado= " + valor + ", Apariciones= " + aparicion;
    }
}