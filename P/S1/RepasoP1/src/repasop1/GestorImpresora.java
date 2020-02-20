package repasop1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import repasop1.Impresora;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author jgr97
 */
public class GestorImpresora {
    
    private final Impresora[] impresoras;
    private final ArrayList<TrabajoImpresion> vectorTrabajos;
    private final Random generador;

    public GestorImpresora(Impresora[] impresoras, ArrayList<TrabajoImpresion> vectorTrabajos) {
        this.impresoras = impresoras;
        this.vectorTrabajos = vectorTrabajos;
        
        generador = new Random();
    }
    
    public void agregarTrabajo (TrabajoImpresion tra){
        vectorTrabajos.add(tra);

        System.out.print("Se ha añadido el trabajo" + tra + "para su impresion.");
    }
    
    private int seleccionarImpresora(){
        return generador.nextInt(impresoras.length);
    }

    public boolean hayTrabajos(){
        return vectorTrabajos.isEmpty();
    }

    public void realizarTrabajo() throws InterruptedException {
        int idIMpresora;
        TrabajoImpresion siguTrabajoImpresion;

        idIMpresora=seleccionarImpresora();
        siguTrabajoImpresion=vectorTrabajos.remove(1);

        TimeUnit.SECONDS.sleep(siguTrabajoImpresion.getTiempoTrabajo());

        System.out.print("Se ha realizado el trabajo: " + siguTrabajoImpresion + 
        " en la impresora: " + impresoras[idIMpresora]);
    }
}
