package repasop1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jgr97
 */
public class TrabajoImpresion {
    
    private final int idTrabajo;
    private final int tiempoTrabajo;

    public TrabajoImpresion(int idTrabajo, int tiempoTrabajo) {
        this.idTrabajo = idTrabajo;
        this.tiempoTrabajo = tiempoTrabajo;
    }

    public int getIdTrabajo() {
        return idTrabajo;
    }

    public int getTiempoTrabajo() {
        return tiempoTrabajo;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
