/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion4;

import java.util.Random;

/**
 *
 * @author pedroj
 */
public interface Constantes {
    // Generador aleatorio
    public static final Random aleatorio = new Random();
    
    public enum TipoSensor {
        MOVIMIENTO(25,3), SONIDO(50,5), TEMPERATURA(75,2), CALOR(100,4);
        
        private final int valor;
        private final int instalacion; // Tiempo mínimo para la instalación

        private TipoSensor(int valor, int instalacion) {
            this.valor = valor;
            this.instalacion = instalacion;
        }
        
        
        /**
         * Selecciona uno de los TipoSensor aleatoriamente
         * @return 
         *      el tipo de sensor seleccionado
         */
        public static TipoSensor getSensor() {
            int valor = aleatorio.nextInt(D100);
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
        
        /**
         * Calcula el tiempo de instalación para un tipo de sensor
         * @return 
         *      el tiempo de instalación para ese sensor
         */
        public int tiempoInstalacion() {
            return aleatorio.nextInt(VARIACION) + instalacion;
        }
    }
    
    public enum TipoCasa {
        PEQUEÑA(15,2), MEDIANA(75,3), GRANDE(100,5);
        
        private final int valor;
        private final int habitaciones;

        private TipoCasa(int valor, int habitaciones) {
            this.valor = valor;
            this.habitaciones = habitaciones;
        }

        public int getHabitaciones() {
            return habitaciones;
        }
        
        /**
         * Genera un tipo de casa aleatoriamente
         * @return 
         *      el tipo de casa generado
         */
        public static TipoCasa getCasa() {
            int valor = aleatorio.nextInt(D100); 
            TipoCasa resultado = null;
            int i = 0;
            
            while( (i < CASAS.length) && (resultado == null) ) {
                if ( CASAS[i].valor > valor )
                    resultado = CASAS[i];
                
                i++;
            }
            
            return resultado;
            
        }
    }

    // Constantes del problema
    public static final int PROMOTORES = 4;
    public static final int INSTALADORES = 3;
    public static final int D100 = 100; // Simula un dado de 100 caras
    public static final TipoSensor[] SENSORES = TipoSensor.values();
    public static final TipoCasa[] CASAS = TipoCasa.values();
    public static final int TIEMPO_ESPERA = 30; // segundos
    public static final int VARIACION = 3; // Diferencia en habitaciones
    public static final int NUM_SENSORES = 1;
    public static final int NUM_ETAPAS = 5;
}