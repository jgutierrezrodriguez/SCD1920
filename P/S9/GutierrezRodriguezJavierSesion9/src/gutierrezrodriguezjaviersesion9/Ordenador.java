/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion9;

import static gutierrezrodriguezjaviersesion9.Constantes.COMPONENTES;
import static gutierrezrodriguezjaviersesion9.Constantes.aleatorio;
import java.util.Arrays;

/**
 *
 * @author pedroj
 */
public class Ordenador {
    private String iD;
    private final Componente[] componentes;
    
    // Constantes
    private final int VARIACION = 3;
    private final int TIEMPO_MONTAJE = 3;

    /**
     * Crea un ordenador sin elementos de vendedor asignados
     */
    public Ordenador() {
        this.componentes = new Componente[COMPONENTES.length];
        for( Componente vendedor : componentes)
            vendedor = null;
        
        this.iD = null;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public Componente[] getComponentes() {
        return componentes;
    }
    
    public int tiempoMontaje() {
        return aleatorio.nextInt(VARIACION) + TIEMPO_MONTAJE;
    }
    
    /**
     * Asigna el nuevo vendedor al ordenador
     * @param vendedor para añadir al ordenador
     * @return true si se a añadido false si ya tenía ese vendedor
     */
    public boolean addComponente( Componente vendedor) {
        boolean resultado = false;
        
        if( componentes[vendedor.getComponente().ordinal()] == null ) {
            componentes[vendedor.getComponente().ordinal()] = vendedor;
            resultado = true;
        }
        
        return resultado;
    }

    @Override
    public String toString() {
        String resultado = null;
        
        if ( ordenadorCompleto() )
            resultado = "Ordenador[" + iD + "]{" + 
                            "componentes=" + Arrays.toString(componentes) + '}';
        else
            resultado = "Ordenador[" + iD + "]{NO_COMPLETO}";
        
        return resultado; 
    }
    
    public boolean ordenadorCompleto() {
        boolean completo = true;
        int i = 0;
        
        while( (i < componentes.length) &&  completo ) 
            if ( componentes[i] != null )
                i++;
            else 
                completo = false;
        
        return componentes.length == i;
    }
}
