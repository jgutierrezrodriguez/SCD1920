/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion9;

import gutierrezrodriguezjaviersesion9.Constantes.TipoComponente;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *
 * @author pedroj
 */
public class Fabricante implements Callable<Componente> {
    private final String iD;
    private final TipoComponente tipoComponente;
    private AtomicIntegerArray inventario;

    public Fabricante(String iD, TipoComponente tipoComponente, AtomicIntegerArray inventario) {
        this.iD = iD;
        this.tipoComponente = tipoComponente;
        this.inventario = inventario;
    }

    @Override
    public Componente call() throws Exception {
        Componente resultado=null;
        
        System.out.println("TAREA-" + iD + " Fabrica su componente " + tipoComponente);
        
        try {
            resultado = prepararComponente();
            
            System.out.println("TAREA-" + iD + " Finaliza el componente " + tipoComponente);
        } catch ( InterruptedException e ) {
            System.out.println("TAREA-" + iD + " Se ha cancelado la fabricación");
        }
        
        return resultado;
    }
    
    private Componente prepararComponente() throws InterruptedException {
        // Fabrica un nuevo componente
        Componente resultado = new Componente(iD+"-"+tipoComponente.ordinal(), tipoComponente);
        if(tipoComponente.ordinal() == 0){
            inventario.compareAndSet(0, 0, tipoComponente.ordinal());
            inventario.getAndIncrement(0);
        }else if(tipoComponente.ordinal() == 1){
            inventario.compareAndSet(1, 1, tipoComponente.ordinal());
            inventario.getAndIncrement(1);
        }else{
            inventario.compareAndSet(2, 2, tipoComponente.ordinal());
            inventario.getAndIncrement(2);
        }
        // Simula la fabricación del componente
        TimeUnit.SECONDS.sleep(resultado.tiempoFabricacion());
        
        return resultado;
    }

    public String getiD() {
        return iD;
    }
}
