/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion9;

import gutierrezrodriguezjaviersesion9.Constantes.TipoComponente;


/**
 *
 * @author pedroj
 */
public class Componente {
    private final String iD;
    private final TipoComponente componente;

    public Componente(String iD, TipoComponente componente) {
        this.iD = iD;
        this.componente = componente;
    }

    public String getiD() {
        return iD;
    }

    public TipoComponente getComponente() {
        return componente;
    }
    
    public int tiempoFabricacion() {
        return componente.tiempoFabricacion();
    }

    @Override
    public String toString() {
        return "Componente{" + "iD=" + iD + ", componente=" + componente + '}';
    }
}
