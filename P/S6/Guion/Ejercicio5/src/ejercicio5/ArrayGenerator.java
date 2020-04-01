/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import java.util.Random;

/**
 *
 * @author jgr97
 */
public class ArrayGenerator {
    public int[] generateArray(int size) {
		int array[]=new int[size];
		Random random=new Random();
		for (int i=0; i<size; i++){
			array[i]=random.nextInt(10);
		}
		return array;
	}

}
