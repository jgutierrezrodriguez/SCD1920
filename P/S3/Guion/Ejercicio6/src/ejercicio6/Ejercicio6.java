/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio6;

import java.util.concurrent.Phaser;

/**
 *
 * @author jgr97
 */
public class Ejercicio6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Phaser phaser=new Phaser(3);

        // Creates 3 FileSearch objects. Each of them search in different directory
        FileSearch system=new FileSearch("C:\\Windows", "log", phaser);
        FileSearch apps=new FileSearch("C:\\Program Files","log",phaser);
        FileSearch documents=new FileSearch("C:\\Documents And Settings","log",phaser);

        // Creates a thread to run the system FileSearch and starts it
        Thread systemThread=new Thread(system,"System");
        systemThread.start();

        // Creates a thread to run the apps FileSearch and starts it
        Thread appsThread=new Thread(apps,"Apps");
        appsThread.start();

        // Creates a thread to run the documents  FileSearch and starts it
        Thread documentsThread=new Thread(documents,"Documents");
        documentsThread.start();
        try {
                systemThread.join();
                appsThread.join();
                documentsThread.join();
        } catch (InterruptedException e) {
                e.printStackTrace();
        }

        System.out.printf("Terminated: %s\n",phaser.isTerminated());

    }
    
}
