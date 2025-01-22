/*
 * Name: Raweerot Bhasidhchirapiroch
 * ID: 6588132
 * Section: 1
 * */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Product {
	
	private int pageNumber;
	private int selectedPage;
	private ArrayList<String> lines;
	private ArrayList<Item> allProducts;
	private ArrayList<Item> filter;
	private Cart cart;
	private Checkout checkout;

	
	public Product() {
		this.pageNumber = 0;
		this.selectedPage = 0;
		this.lines = new ArrayList<String>();
		this.allProducts = new ArrayList<Item>();
		this.filter = new ArrayList<Item>();
		this.cart = new Cart();
	}
	
	private void stockSummary() {
		File f = new File("Stock.csv");
		FileOutputStream fs = null;
		OutputStreamWriter ow = null;
		BufferedWriter bw = null;
		
		try {
			 fs = new FileOutputStream(f);
			 ow = new OutputStreamWriter(fs);
			 bw = new BufferedWriter(ow);
			 ArrayList<String> line = new ArrayList<String>();
			 line.add("ID,name,description,price,stock\n");
			 for(int i=0;i<this.allProducts.size();i++) {
				 Item iGet = (Item)this.allProducts.get(i);
				 line.add(iGet.getID()+","+iGet.getName()+","+iGet.getDescription()+","+iGet.getPrice()+","+iGet.getQuantity()+"\n");
			 }
			 
			 for(int i=0;i<line.size();i++) {
				 bw.write((String)line.get(i));;
			 }
			 
			 bw.close();
		 }catch(IOException io) {
			 System.out.print("IO Error\n");
		 }
	}
	
	public void addAllProducts() {
		 File f = new File("Stock.csv");
		 FileInputStream fs = null;
		 InputStreamReader ir = null;
		 BufferedReader br = null;
		 try {
			 fs = new FileInputStream(f);
			 ir = new InputStreamReader(fs);
			 br = new BufferedReader(ir);
			 
			 int i = 0;
			 String Line="";
			 while((Line = br.readLine())!=null) {
				 lines.add(Line.trim());
				 i++;
			 }
			 
			 for(i=1;i<lines.size();i++) {
				 String[] line = ((String) lines.get(i)).split(",");
				 allProducts.add(new Item(line[0],line[1],line[2],Integer.parseInt(line[3]),Integer.parseInt((String)line[4])));
			 }
		 }catch(IOException io) {
			 System.out.print("IO Error\n");
		 }
	 }
	
	public void addProduct(){
		Scanner sc = null;	Scanner in = null; Scanner in1 = null;
		try {
			sc = new Scanner(System.in);	in = new Scanner(System.in); in1 = new Scanner(System.in);
			System.out.print("Please insert the name of product you want to add: ");
			String name = in1.nextLine();
			for(int i=0;i<this.allProducts.size();i++){
				String check = ((Item)this.allProducts.get(i)).getName();
				if(name.equalsIgnoreCase(check)){
					System.out.print("This product is already in the stock. How many quantity do you wabt to add: ");
					((Item)this.allProducts.get(i)).addQuantity(sc.nextInt());
					displayProducts(10, 1);
					break;
				}else{
					System.out.print("Please insert the item you want to add in form of\n<ID>,<Name>,<Description>,<Price>,Quantity: ");
					String input = in.nextLine();	String[] inputs = input.split(",");
					Item element = new Item(inputs[0],inputs[1],inputs[2],Integer.parseInt(inputs[3]),Integer.parseInt(inputs[4]));
					System.out.print("Do you want to add at last or between the products: \n1: at last.\n2: between the products.");
					switch(sc.nextInt()) {
						case 1:
							allProducts.add(element);
							break;
						case 2:
							System.out.print("Please insert the number: ");
							allProducts.add(element,sc.nextInt());
					}
				}
			}
			
		}catch(InputMismatchException im) {
			System.out.println("\nInvalid Input\n");
			addProduct();
		}catch(NoSuchElementException nse){
			System.out.println("\nInvalid Input\n");
			addProduct();
		 }catch(IllegalStateException ise){
			System.out.println("\nInvalid Input\n");
			addProduct();
		 }finally{ sc.close(); in.close(); }
		
	}

	
	public void setProduct(int index, Item product) {
		allProducts.set(product, index);
	}
	public void clearProducts() {
		allProducts.clear();
	}
	 
	public void removeProduct(int index) {
		allProducts.remove(index);
	}
	
	public int getNumberOfProducts() {
		return this.allProducts.size();
	}
	 
	 public void displayProducts(int maxProductPerPage, int _selectedPage) {
		 Scanner sc = null;
		 Scanner in = null;
		 
		 int maxProduct=maxProductPerPage;
		 this.selectedPage = _selectedPage;
		 this.pageNumber = 0;
		 
		 if (this.allProducts.size()%maxProductPerPage == 0) {
			 this.pageNumber = this.allProducts.size()/maxProductPerPage;
		 }else {
			 this.pageNumber = (this.allProducts.size()/maxProductPerPage)+1;
		 }
		 System.out.println("\nProducts per page: "+maxProductPerPage+"\n");
		 for(int i=(this.selectedPage-1)*maxProductPerPage;i<maxProductPerPage*(this.selectedPage) && i<this.allProducts.size();i++) {
			 System.out.println("=---------------------------------------------------------------------------------------------------------=");
			 System.out.println("| ["+(i+1)+"] "+((Item) this.allProducts.get(i)).log()+" |");
			 System.out.println("=---------------------------------------------------------------------------------------------------------=\n");
		 }
		 System.out.println("\nPage: ["+selectedPage+"] from ["+pageNumber+"]");
		 System.out.print("\n(1: go to the first page  2: go to the previous page  3: go to...  4: go to the next page  5: go to the last page\n6: Change the number of max product per page  7: go to filter mode  8: manage your cart  9: go to checkout page  ): ");
		 
		 try{
				sc = new Scanner(System.in);
				in = new Scanner(System.in);
				int choice = sc.nextInt();
				switch(choice) {
			 		case 1:
						if(this.selectedPage == 1) {
							displayProducts(maxProduct,this.selectedPage);
						}else {
							this.selectedPage=1;
							displayProducts(maxProduct,this.selectedPage);
						}
						break;
					case 2:
						if(this.selectedPage == 1) {
							displayProducts(maxProduct,this.selectedPage);
						}else {
							this.selectedPage -= 1;
							displayProducts(maxProduct,this.selectedPage);
						}
						break;
					case 3:
						System.out.println("Please insert page number you want to go: ");
						this.selectedPage = sc.nextInt();
						while(this.selectedPage < 1 || this.selectedPage > this.pageNumber) {
							System.out.println("Invalid page number. Please insert page number you want to go again: ");
							this.selectedPage = sc.nextInt();
						}
							displayProducts(maxProduct,this.selectedPage);
						break;
					case 4:
						if(this.selectedPage == this.pageNumber) {
							displayProducts(maxProduct,this.selectedPage);
						}else {
							this.selectedPage += 1;
							displayProducts(maxProduct,this.selectedPage);
						}
						break;
					case 5:
						if(this.selectedPage == this.pageNumber) {
							displayProducts(maxProduct,this.selectedPage);
						}else {
							this.selectedPage = this.pageNumber;
							displayProducts(maxProduct,this.selectedPage);
						}
						break;
					case 6:
						System.out.print("\nPlease insert new number of products per page: ");
						int max = sc.nextInt();
						while(max<=0) {
							System.out.print("\nInvalid max number of products per page. Please insert new number of products per page: ");
							max = sc.nextInt();
						}
						displayProducts(max,1);
						break;
					case 7:
						filter();
						break;

					case 8:
						Scanner case8 = null;
						Scanner STR = null;
						try {
							case8 = new Scanner(System.in);
							STR = new Scanner(System.in);
							
							boolean add = true;
							
							while(add) {
								
								System.out.print("\nWhat would you like to do next\n\n( 1: pick some items, 2: remove some items from cart, 3: go back to review the shelf, 4: review your cart, 5: go to checkout page ): ");
								
								switch(case8.nextInt()) {
									case 1:
										System.out.print("Please insert the ID of wanted product: ");
										String ID = STR.nextLine();
										boolean found = false;
										int index=0;
										for(int i=0;i<this.allProducts.size();i++) {
											String id = ((Item)this.allProducts.get(i)).getID();
											if(ID.equals(id)) {
												found = true;
												index = i;
												break;
											}
										}

										if(!found) {
											System.out.print("There is no this product in our store. Please look for another product\n");
											displayProducts(10, 1);
										}else {
											Item iSearch = (Item)this.allProducts.get(index);
											if(iSearch.getQuantity() == 0) {
												System.out.println("This product was sold out. Please look for another product");
											}else {
												System.out.print("How many unit(s) do you need to add: ");
												int qty = case8.nextInt();
												boolean exceed = false;
												if(qty > iSearch.getQuantity() || qty < 1) {
													exceed = true;
												}
												while(exceed) {
													System.out.print("Invalid quantity. This product has "+iSearch.getQuantity()+" unit(s)\n");
													System.out.print("How many unit(s) do you need to add: ");	
													qty = case8.nextInt();
													if(qty >= 1 && qty <= iSearch.getQuantity()) {
														exceed = false;
													}
												}
												Item iAdd = (Item)this.allProducts.get(index);
												this.cart.add2cart(new Item(iAdd.getID(),iAdd.getName(),iAdd.getDescription(),iAdd.getPrice(),qty));
												((Item)this.allProducts.get(index)).addQuantity(-1*qty);
											}
										}
										continue;
									case 2:
										int removeSearch = 0;
										
										if(removeSearch < 0 || removeSearch > this.cart.numItemsInCart()-1) {
											this.cart.reviewCart();
										}else {
											System.out.print("Please insert the number of product(no.) you want to remove: ");
											removeSearch = case8.nextInt();
											removeSearch--;
											Item iremove = (Item)this.cart.getProductFromCart(removeSearch);
											System.out.print("How many unit(s) do you need to remove: ");
											int qty = case8.nextInt();
											
											if(qty > iremove.getQuantity() || qty < 1) {
												boolean exceed = true;
												while(exceed) {
													System.out.print("Invalid quantity. This product has "+iremove.getQuantity()+" unit(s)\n");
													System.out.print("How many unit(s) do you need to remove: ");	
													qty = case8.nextInt();
													if(qty >= 1 && qty <= iremove.getQuantity()) {
														break;
													}
												}
											}
											this.cart.removeFromCart(iremove,qty);
											for(int i=0;i<this.allProducts.size();i++) {
												String id = ((Item)this.allProducts.get(i)).getID();
												if(iremove.getID().equals(id)) {
													((Item)this.allProducts.get(i)).addQuantity(qty);
													break;
												}
											}
										}
										continue;
									case 3:
										displayProducts(10,1);
										add = false;
										break;
									
									case 4:
										this.cart.reviewCart();
										continue;
										
									case 5:
										if(this.cart.numItemsInCart() == 0) {
											this.cart.reviewCart();
											continue;
										}else {
											String name=""; String email=""; String address=""; String postalCode="";
											System.out.print("Please insert your name: "); name = in.nextLine();
											System.out.print("Please insert your email: "); email = in.nextLine();
											System.out.print("Please insert your address: "); address = in.nextLine();
											System.out.print("Please insert your postal code: "); postalCode = in.nextLine();
											this.checkout = new Checkout(name,email,address,postalCode,this.cart);
											this.checkout.paymentInfo();
											stockSummary();
											add = false;
										}
										
										break;
										
									default:
										displayProducts(10,1);
										add = false;
										break;
								}
							}	
							
						}catch(Exception ex) {
							displayProducts(10,1);
						}finally {
							case8.close();
							STR.close();
						}
						
						break;
					case 9:
						if(this.cart.numItemsInCart() == 0) {
							this.cart.reviewCart();
							displayProducts(10,1);
						}else {
							String name=""; String email=""; String address=""; String postalCode="";
							System.out.print("Please insert your name: "); name = in.nextLine();
							System.out.print("Please insert your email: "); email = in.nextLine();
							System.out.print("Please insert your address: "); address = in.nextLine();
							System.out.print("Please insert your postal code: "); postalCode = in.nextLine();
							this.checkout = new Checkout(name,email,address,postalCode,this.cart);
							this.checkout.paymentInfo();
							stockSummary();
						}
						
						break;
					default:
						System.out.println("Invalid choice\n");
						displayProducts(10, 1);
				}
			}catch(Exception ex){
				displayProducts(10,1);
		 	}finally {
				sc.close();
		 	}
			
		
	 }
	 
	 public void filter(){
		 Scanner sc = null;
		 try {
			 sc = new Scanner(System.in);
			 System.out.print("1: Search by price range or 2: Search by Ministry : ");
			 int choice = sc.nextInt();
			 int lowPrice; int highPrice;
			 lowPrice = highPrice = 0;
			 switch(choice) {
			 	case 1:
			 		System.out.print("Insert the low price: "); lowPrice = sc.nextInt();
			 		System.out.print("Insert the high price: "); highPrice = sc.nextInt();
			 		while(lowPrice > highPrice) {
			 			System.out.println("You insert an invalid price interval");
			 			System.out.print("Insert the low price: "); lowPrice = sc.nextInt();
				 		System.out.print("Insert the high price: "); highPrice = sc.nextInt();
			 		}
			 		for (int i = 0; i < this.allProducts.size(); i++) {
			 		    int itemPrice = ((Item) this.allProducts.get(i)).getPrice();
			 		    if (itemPrice >= lowPrice && itemPrice <= highPrice) {
			 		        filter.add((Item) this.allProducts.get(i)); 
			 		    }
			 		}
			 		
			 		break;
			 	case 2:
			 		System.out.print("Please fill the Ministry you want to find: ");
			 		Scanner in = new Scanner(System.in);
			 		String userMinistry = in.nextLine();
			 		for (int i = 0; i < this.allProducts.size(); i++) {
			 		    String itemMinistry = ((Item) this.allProducts.get(i)).getDescription();
			 		    if (itemMinistry.equalsIgnoreCase(userMinistry)) {
			 		        filter.add(this.allProducts.get(i));
			 		    }
			 		}
			 		break;
				default:
					filter();
					break;
			 }
			displayfilter(10,1);
		 }catch(Exception ex){
			filter();
		 }finally {
			sc.close();
		 }
		 
	 }
	 
	 public void displayfilter(int maxProductPerPage, int _selectedPage) {
		 int maxProduct=maxProductPerPage;
		 this.selectedPage = _selectedPage;
		 this.pageNumber = 0;
		 if (this.allProducts.size()%maxProductPerPage == 0) {
			 this.pageNumber = this.filter.size()/maxProductPerPage;
		 }else {
			 this.pageNumber = (this.filter.size()/maxProductPerPage)+1;
		 }

		 if(this.filter.size()==0){
			System.out.println("\nNo result found\n");
			System.out.print("Would you like to ( 1: Clear Filter/ 2: Go back to default mode ): ");
			Scanner Y_N = null;
			try{
				Y_N = new Scanner(System.in);
				switch(Y_N.nextInt()) {
					case 1:
						filter();
						break;
					case 2:
						displayProducts(10, 1);
						break;
					default:
						filter();
						break;
				}
			}catch(Exception ex) {
				filter();
			}finally{
				Y_N.close();
			}
		 }else{
			 System.out.println("\nProducts per page: "+maxProductPerPage+"\n");
			 for(int i=(this.selectedPage-1)*maxProductPerPage;i<maxProductPerPage*(this.selectedPage) && i<this.filter.size();i++) {
				 System.out.println("=---------------------------------------------------------------------------------------------------------=");
				 System.out.println("| ["+(i+1)+"]"+((Item) this.filter.get(i)).log()+" |");
				 System.out.println("=---------------------------------------------------------------------------------------------------------=\n");
			 }
			 System.out.println(this.filter.size()+" results found");
			 System.out.println("Page: ["+selectedPage+"] from ["+pageNumber+"]");
		 System.out.print("(0: go to checkout page  1: go to the first page  2: go to the previous page  3: go to...  4: go to the next page  5: go to the last page\n6: Change the number of max product per page  7: clear filter  8: go back to default mode  9: manage your cart  10: go to checkout): ");
		 Scanner sc = null;
		 Scanner in = null;
		 try {
			sc = new Scanner(System.in);
			in = new Scanner(System.in);
			int choice = sc.nextInt(); 
			switch(choice) {
				case 0:
					break;
				case 1:
					if(this.selectedPage == 1) {
						displayfilter(maxProduct,this.selectedPage);
					}else {
						this.selectedPage=1;
						displayfilter(maxProduct,this.selectedPage);
					}
					break;
				case 2:
					if(this.selectedPage == 1) {
						displayfilter(maxProduct,this.selectedPage);
					}else {
						this.selectedPage -= 1;
						displayfilter(maxProduct,this.selectedPage);
					}
					break;
				case 3:
					System.out.print("Please insert page number you want to go: ");
					this.selectedPage = sc.nextInt();
					while(this.selectedPage < 1 || this.selectedPage > this.pageNumber) {
						System.out.print("Invalid page number. Please insert page number you want to go again: ");
						this.selectedPage = sc.nextInt();
					}
					displayfilter(maxProduct,this.selectedPage);
					break;
				case 4:
					if(this.selectedPage == this.pageNumber) {
						displayfilter(maxProduct,this.selectedPage);
					}else {
						this.selectedPage += 1;
						displayfilter(maxProduct,this.selectedPage);
					}
					break;
				case 5:
					if(this.selectedPage == this.pageNumber) {
						displayfilter(maxProduct,this.selectedPage);
					}else {
						this.selectedPage = this.pageNumber;
						displayfilter(maxProduct,this.selectedPage);
					}
					break;
				case 6:
					System.out.print("Please insert new number of products per page: ");
					int max = sc.nextInt();
					displayfilter(max,1);
					break;
				case 7:
					this.filter.clear();
					filter();
					break;
				case 8:
					this.filter.clear();
					displayProducts(10,1);
					break;
				case 9:
					Scanner case8 = null;
					Scanner STR = null;
					try {
						case8 = new Scanner(System.in);
						STR = new Scanner(System.in);
						
						boolean add = true;
						
						while(add) {
							
							System.out.print("\nWhat would you like to do next\n\n( 1: pick some items, 2: remove some items from cart, 3: go back to review the shelf, 4: review your cart, 5: go to checkout page ): ");
							
							switch(case8.nextInt()) {
								case 1:
									System.out.print("Please insert the ID of wanted product: ");
									String ID = STR.nextLine();
									boolean found = false;
									int index=0;
									for(int i=0;i<this.allProducts.size();i++) {
										String id = ((Item)this.allProducts.get(i)).getID();
										if(ID.equals(id)) {
											found = true;
											index = i;
											break;
										}
									}

									if(found == false) {
										System.out.print("There is no this product in our store. Please look for another product\n");
										displayfilter(10, 1);
									}else {
										Item iSearch = (Item)this.allProducts.get(index);
										System.out.print("How many unit(s) do you need to add: ");
										int qty = case8.nextInt();
										
										if(iSearch.getQuantity() == 0 || qty > iSearch.getQuantity()) {
											if(iSearch.getQuantity() == 0) {
												System.out.println("This product was sold out. Please look for another product");
											}else {
												boolean exceed = true;
												while(exceed) {
													System.out.print("This product has only "+iSearch.getQuantity()+" unit(s)\n");
													System.out.print("How many unit(s) do you need to add: ");	
													qty = case8.nextInt();
													if(qty >= 1 && qty <= iSearch.getQuantity()) {
														break;
													}
												}
												Item iAdd = (Item)this.allProducts.get(index);
												this.cart.add2cart(new Item(iAdd.getID(),iAdd.getName(),iAdd.getDescription(),iAdd.getPrice(),qty));
												((Item)this.allProducts.get(index)).addQuantity(-1*qty);
											}
										}else {
											Item iAdd = (Item)this.allProducts.get(index);
											this.cart.add2cart(new Item(iAdd.getID(),iAdd.getName(),iAdd.getDescription(),iAdd.getPrice(),qty));
											((Item)this.allProducts.get(index)).addQuantity(-1*qty);
										}
									}
									continue;
								case 2:
									System.out.print("Please insert the number of product(no.) you want to remove: ");
									int removeSearch = case8.nextInt();
									removeSearch--;
									if(removeSearch < 0 || removeSearch > this.cart.numItemsInCart()-1) {
										System.out.print("There is no this product in your cart.\n");
										this.cart.reviewCart();
									}else {
										Item iremove = (Item)this.cart.getProductFromCart(removeSearch);
										System.out.print("How many unit(s) do you need to remove: ");
										int qty = case8.nextInt();
										
										if(qty > iremove.getQuantity()) {
											boolean exceed = true;
											while(exceed) {
												System.out.print("This product has only "+iremove.getQuantity()+" unit(s)\n");
												System.out.print("How many unit(s) do you need to remove: ");	
												qty = case8.nextInt();
												if(qty >= 1 && qty <= iremove.getQuantity()) {
													break;
												}
											}
										}
										this.cart.removeFromCart(iremove,qty);
										for(int i=0;i<this.allProducts.size();i++) {
											String id = ((Item)this.allProducts.get(i)).getID();
											if(iremove.getID().equals(id)) {
												((Item)this.allProducts.get(i)).addQuantity(qty);
												break;
											}
										}
									}
									continue;
								case 3:
									displayfilter(10,1);
									add = false;
									break;
								
								case 4:
									this.cart.reviewCart();
									continue;
								case 5:									
									if(this.cart.numItemsInCart() == 0) {
										this.cart.reviewCart();
										continue;
									}else {
										String name=""; String email=""; String address=""; String postalCode="";
										System.out.print("Please insert your name: "); name = in.nextLine();
										System.out.print("Please insert your email: "); email = in.nextLine();
										System.out.print("Please insert your address: "); address = in.nextLine();
										System.out.print("Please insert your postal code: "); postalCode = in.nextLine();
										this.checkout = new Checkout(name,email,address,postalCode,this.cart);
										this.checkout.paymentInfo();
										stockSummary();
										add = false;
									}
									break;
								default:
									displayProducts(10,1);
									add = false;
									break;
							}
						}	
						
					}catch(Exception ex) {
						displayProducts(10,1);
					}finally {
						case8.close();
						STR.close();
					}
					
					break;
				case 10:
					if(this.cart.numItemsInCart() == 0) {
						this.cart.reviewCart();
						displayProducts(10,1);
					}else {
						String name=""; String email=""; String address=""; String postalCode="";
						System.out.print("Please insert your name: "); name = in.nextLine();
						System.out.print("Please insert your email: "); email = in.nextLine();
						System.out.print("Please insert your address: "); address = in.nextLine();
						System.out.print("Please insert your postal code: "); postalCode = in.nextLine();
						this.checkout = new Checkout(name,email,address,postalCode,this.cart);
						this.checkout.paymentInfo();
						stockSummary();
					}
					
					break;
				default:
					displayfilter(10,1);
					break;
			}
		}catch(Exception ex){
			displayfilter(10,1);
		}finally {
			sc.close();
		}
	  }
		  
	 } 
}