/*
 * Name: Raweerot Bhasidhchirapiroch
 * ID: 6588132
 * Section: 1
 * */

public class Application {
	
	public static void main(String[] args) {

		System.out.println("=---------------------------------------------------------------------------------------------------------=");
		System.out.println("|                                                                                                         |");
		System.out.println("|                                Welcome to Thailand Online Shopping Mall!                                |");
		System.out.println("|                                                                                                         |");
		System.out.println("=---------------------------------------------------------------------------------------------------------=\n");
		Product product = new Product();
		product.addAllProducts();	
		product.displayProducts(10,1);
		
	}
}