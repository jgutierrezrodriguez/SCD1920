/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio.pkg4;

/**
 *
 * @author jgr97
 */
public class Ejercicio4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Videoconference conference=new Videoconference(10);
        // Creates a thread to run the VideoConference and start it.
        Thread threadConference=new Thread(conference);
        threadConference.start();

        // Creates ten participants, a thread for each one and starts them
        for (int i=0; i<10; i++){
            Participant p=new Participant(conference, "Participant "+i);
            Thread t=new Thread(p);
            t.start();
        }

        
    }
    
}
