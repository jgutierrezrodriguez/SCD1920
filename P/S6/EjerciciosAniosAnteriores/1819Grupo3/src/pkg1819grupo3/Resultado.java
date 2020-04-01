/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819grupo3;



/**
 *
 * @author pedroj & javiermq
 */
public class Resultado {
    private int sum;
    private int total;

    public Resultado() {
       sum=0;
       total=0;
    }
    

    public void incSum(int inc_sum){
        this.sum+=inc_sum;
        this.total++;
    }
    
    public float getMedia(){
        if(total==0) return 0f;
        return (float)sum/(float)total;
    }
  
    @Override
    public String toString() {
        return "Sum = " + sum +
               ", total = " + total+
                ", media = " + getMedia();
    }
}