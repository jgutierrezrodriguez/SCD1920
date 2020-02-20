/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio.pkg4;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author jgr97
 */
public class Participant implements Runnable{
    
    private Videoconference video;
    
    private String name;

    public Participant(Videoconference video, String name) {
        this.video = video;
        this.name = name;
    }
    
    @Override
    public void run() {
        Long duration=(long)(Math.random()*10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        video.arrive(name);
    }

}
