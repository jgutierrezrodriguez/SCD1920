/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezjaviersesion1;

/**
 *
 * @author jgr97
 */
public class Casa {
    
    private static final int FILAS=4;
    private final int nHabitaciones;
    private final int id;
    private final Sensor [][] matrizSensores;

    public Casa(int nHabitaciones, int id) {
        this.nHabitaciones = nHabitaciones;
        this.id = id;
        
        matrizSensores = new Sensor[FILAS][nHabitaciones];
        
    }

    @Override
    public String toString() {
        String resultado="Casa: id: "+ id + "Habitaciones:";
        
        for(int i=0; i<nHabitaciones;i++){
            resultado = resultado + " h" + i +":";
            for(int j=1;j<FILAS;j++){
                if(matrizSensores[j][i].getTiposensor()!=null){
                    resultado = resultado + matrizSensores[i][j].toString();
                }
            }
            
        }
        
        return resultado;
    }
    
    public boolean asignarSensor(Sensor s){
        int tipo=0;
        if(s.getTiposensor()==GutierrezJavierSesion1.TipoSensor.CALOR)
            tipo=0;
        else if (s.getTiposensor()==GutierrezJavierSesion1.TipoSensor.MOVIMIENTO)
            tipo=1;
        else if (s.getTiposensor()==GutierrezJavierSesion1.TipoSensor.SONIDO)
            tipo=2;
        else if (s.getTiposensor()==GutierrezJavierSesion1.TipoSensor.TEMPERATURA)
            tipo=3;

        for (int i = 0; i < nHabitaciones; i++) {
            if(matrizSensores[tipo][i].getId()==0){
                matrizSensores[tipo][i]=s;
                return true;
            }
        }
        
        return false;
            
    }

    public int getnHabitaciones() {
        return nHabitaciones;
    }

    public int getId() {
        return id;
    }
    
    
    
}
