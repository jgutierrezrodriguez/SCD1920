/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jgr97
 */
public class ProductListGenerator {
    /**
	 * This method generates the list of products
	 * @param size the size of the product list
	 * @return the generated list of products
	 */
	public List<Product> generate (int size) {
		List<Product> ret=new ArrayList<>();
		
		for (int i=0; i<size; i++){
			Product product=new Product();
			product.setName("Product "+i);
			product.setPrice(10);
			ret.add(product);
		}
		
		return ret;
	}

}
