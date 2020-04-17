/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gutierrezrodriguezjaviersesion9;

import java.util.Random;

/**
 * Interface con las constantes necesarias para la resolución del ejercico
 * @author pedroj
 */
public interface Constantes {
    // Generador aleatorio
    public static final Random aleatorio = new Random();
    
    // Enumerado para el tipo de componente
    public enum TipoComponente {
        CPU(25,5), MEMORIA(50,3), PERIFERICO(100,6);
        
        private final int valor;
        private final int fabricacion; // tiempo de fabricación

        private TipoComponente(int valor, int fabricacion) {
            this.valor = valor;
            this.fabricacion = fabricacion;
        }
        
        /**
         * Genera aleatoriamente un TipoComponente 
         * @return
         *      componente generado aleatoriamente
         */
        public static TipoComponente getComponente() {
            int valor = aleatorio.nextInt(D100);
            TipoComponente resultado = null;
            int i = 0;
            
            while( (i < COMPONENTES.length) && (resultado == null) ) {
                if ( COMPONENTES[i].valor > valor )
                    resultado = COMPONENTES[i];
                
                i++;
            }
            
            return resultado;
        } 
        
        /**
         * Tiempo necesario para la creación de un componente
         * @return 
         *      tiempo empleado
         */
        public int tiempoFabricacion() {
            return aleatorio.nextInt(VARIACION) + fabricacion;
        }
    }
    
    public enum Estado { COMPLETADO, NO_COMPLETADO }
    
    // Constantes del problema
    public static final int SISTEMA = 5; 
    public static final int FABRICANTES = 8;
    public static final int PROVEEDORES = 3;
    public static final int ESPERA_FINALIZACION = 1; 
    public static final int D100 = 100; // Simula un dado de 100 caras
    public static final TipoComponente[] COMPONENTES = TipoComponente.values() ;
    public static final int VARIACION = 3;
    public static final int INICIO = 0;
    public static final int CREAR_COMPONENTE = 1; // segundos
    public static final int TIEMPO_ESPERA = 1; // minuto
}
