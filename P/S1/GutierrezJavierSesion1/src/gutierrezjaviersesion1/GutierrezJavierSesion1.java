/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezjaviersesion1;

import java.util.Random;

/**
 *
 * @author pedroj
 */
public class GutierrezJavierSesion1 {
    // Enumerado para el tipo de componente
    public enum TipoSensor {
        MOVIMIENTO(25), SONIDO(50), TEMPERATURA(75), CALOR(100);
        
        private final int valor;

        private TipoSensor(int valor) {
            this.valor = valor;
        }
        
        /**
         * Obtenemos un sensor relacionado con su valor de creación
         * @param valor de creación del sensor
         * @return el TipoSensor con el valor de creación
         */
        public static TipoSensor getSensor(int valor) {
            TipoSensor resultado = null;
            TipoSensor[] sensores = TipoSensor.values();
            int i = 0;
            
            while( (i < sensores.length) && (resultado == null) ) {
                if ( sensores[i].valor >= valor )
                    resultado = sensores[i];
                
                i++;
            }
            
            return resultado;
        } 
    }

    public static final int MAX_CASAS=10;
    public static final int MAX_HABITACIONES=5;
    public static final int MIN_HABITACIONES=3;
    public static final int MAX_SENSORES=20;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Casa [] listacasas = new Casa [MAX_CASAS];
        Random generador = new Random();
        for(int i=0; i<MAX_CASAS; i++){
            listacasas[i]=new Casa(generador.nextInt(2)+MIN_HABITACIONES, i);
        }
        
        int num=0;
        
        while(num<MAX_SENSORES){
            Sensor s = new Sensor(num,TipoSensor.getSensor(generador.nextInt(100)) );
            boolean resultado=false;
            int intentos=0;
            while(resultado==false || intentos<MAX_SENSORES){
                int id_aleatorio = generador.nextInt(MAX_CASAS);
                
                resultado=listacasas[id_aleatorio].asignarSensor(s);
                intentos++;
                
            }
            
            if(resultado=true){
                num++;
            }
            
        }
        
        for(int i = 0; i<MAX_CASAS; i++){
            System.out.printf(listacasas[i].toString());
        }
        
    }
    
}