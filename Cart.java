/*
 * Name: Raweerot Bhasidhchirapiroch
 * ID: 6588132
 * Section: 1
 * */

import java.text.DecimalFormat;
public class Cart {

	private LinkedList<Item> order;
	private DecimalFormat df;

	public Cart(){
		this.order = new LinkedList<Item>();
		this.df = new DecimalFormat("#,###");
	}
	
	public int findItem(String name) {
		for(int i=0;i<this.order.size();i++) {
			if(name.equalsIgnoreCase(((Item)this.order.get(i)).getName())) {
				return i;
			}
		}
		return -1;
	}
	
	public Item getProductFromCart(int no) {
		return (Item)this.order.get(no);
	}
	
	public LinkedList<Item> getItemList(){
		return this.order;
	}
	
	public void add2cart(Item element){
		
		if(this.order.size() == 0) {
			this.order.add(element);
			System.out.println("Added "+element.getID()+"\t"+element.getName()+" successfully!\n");
			reviewCart();
		}else {
			if(findItem(element.getName()) != -1) {
				((Item)this.order.get(findItem(element.getName()))).addQuantity(element.getQuantity());
				System.out.println("Added "+element.getID()+"\t"+element.getName()+" successfully!\n");
				reviewCart();
			}else {
				this.order.add(element);
				System.out.println("Added "+element.getID()+"\t"+element.getName()+" successfully!\n");
				reviewCart();
			}
		}
		
		
	}
	
	public void removeFromCart(Item element,int qty) {
		((Item)this.order.get(findItem(element.getName()))).addQuantity(-1*qty);
		
		if(element.getQuantity()==0) {
			this.order.remove(element);
			System.out.println("Remove "+element.getID()+"\t"+element.getName()+" successfully!\n");
			reviewCart();
		}else {
			System.out.println("Reduce "+qty+" of "+element.getID()+"\t"+element.getName()+" successfully!\n");
			reviewCart();
		}
		
		
	}
	
	public int numItemsInCart() {
		return this.order.size();
	}
	public int getTotalPrice() {
		int total = 0;
		for(int i=0;i<this.order.size();i++) {
			Item item = (Item)this.order.get(i);
			total += item.getPrice()*item.getQuantity();
		}
		return total;
	}

	public void reviewCart(){
		
		if(this.order.size() == 0) {
			System.out.println("=---------------------------------------------------------------------------------------------------------=");
			System.out.println("|                         Your cart is empty, please pick some products from our store.                   |");
			System.out.println("=---------------------------------------------------------------------------------------------------------=\n");
		}else {
			System.out.println("=---------------------------------------------------------------------------------------------------------=");
			System.out.println("|                                      These are items in your cart                                       |");
			System.out.println("=---------------------------------------------------------------------------------------------------------=\n");
			for(int i=0;i<this.order.size();i++){
				System.out.println("=---------------------------------------------------------------------------------------------------------=");
				System.out.println("| ["+(i+1)+"] "+((Item) this.order.get(i)).log()+" |");
				System.out.println("=---------------------------------------------------------------------------------------------------------=\n");
			}
			System.out.print("\n[Total Price]: "+df.format(getTotalPrice())+" Baht");
		}
			
	}
	
	public void displayCart(){
		if(this.order.size() == 0) {
			System.out.println("=---------------------------------------------------------------------------------------------------------=");
			System.out.println("|                         Your cart is empty, please pick some products from our store.                   |");
			System.out.println("=---------------------------------------------------------------------------------------------------------=\n");
		}else {
			for(int i=0;i<this.order.size();i++){
				System.out.println("=---------------------------------------------------------------------------------------------------------=");
				System.out.println("| ["+(i+1)+"] "+((Item) this.order.get(i)).log()+" |");
				System.out.println("=---------------------------------------------------------------------------------------------------------=\n");
			}
		}
			
	}
}