/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repasop1;

/**
 *
 * @author jgr97
 */
public class Impresora {
    
    private final int id;

    public Impresora(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Impresora{" + "id=" + id + '}';
    }
}
