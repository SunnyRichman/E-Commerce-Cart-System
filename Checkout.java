/*
 * Name: Raweerot Bhasidhchirapiroch
 * ID: 6588132
 * Section: 1
 * */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Checkout{
	private DecimalFormat df;
    private String customerName;
    private String email;
    private String Address;
    private String postalCode;
    private String[] nakhonPrathomCode = new String[]{"73000","73110","73120","73130","73140","73150","73170"};
    private Cart c;
//    private Payment payment;
    private Cash cash;
    private CreditCard creditcard;
    private Scanner sc;

    public Checkout() {
    	
    }
    
    public Checkout(String name, String Email, String Address, String PostalCode, Cart cart){
        this.customerName = name;
        this.email = Email;
        this.Address = Address;
        this.postalCode = PostalCode;
        this.c = cart;
        this.df = new DecimalFormat("#,###");
    }

    public String displayInfo(){
        return "\n[Customer]: "+this.customerName+"\n[E-mail]: "+this.email+"\n[Address]: "+this.Address+"\n[Postal Code]: "+this.postalCode;
    }

    private int getShippingFee(){
        int shippingFee = 0;
        for(int i=0;i<this.nakhonPrathomCode.length;i++){
            if(this.postalCode.equalsIgnoreCase(this.nakhonPrathomCode[i])){
                shippingFee = 37;
                break;
            }
            else{
                shippingFee = 99;
            }
        }
        return shippingFee;
    }
    
    public void paymentInfo() {
    	Scanner line = null;
    	String log = "";
    	System.out.println("\n[Grand Total ( included shipping fee )]: "+df.format(getShippingFee()+this.c.getTotalPrice())+" Baht");
    	try {
    		line = new Scanner(System.in);
    		sc = new Scanner(System.in);
    		System.out.println("Please select the method of payment\n1: Cash\n2: Credit Card");
        	switch(sc.nextInt()) {
	        	case 1:
	        		System.out.print("Please insert your cash: ");
	        		int cash = sc.nextInt();
	        		this.cash = new Cash((getShippingFee()+this.c.getTotalPrice()),cash);
	        		while(!(this.cash.isValid())) {
	        			System.out.println("\n[Grand Total ( included shipping fee )]: "+df.format(getShippingFee()+this.c.getTotalPrice())+" Baht");
	        			System.out.print("Please insert your cash: ");
	        			cash = sc.nextInt();
	        			this.cash = new Cash((getShippingFee()+this.c.getTotalPrice()),cash);
	        		}
	        		log = this.cash.log();
	        		break;
	        	case 2:
	        		double amount = getShippingFee()+this.c.getTotalPrice();
	        		CreditCard.CardType type = CreditCard.CardType.AMERICANEXPRESS;
	        		System.out.print("What is the type of your creditcard\n1: VISA\t2: AMERICANEXPRESS\t3: JCB\t4: MASTERCARD: ");
	        		switch(sc.nextInt()) {
		        		case 1:
		        			type = CreditCard.CardType.VISA;
		        			break;
		        		case 2:
		        			type = CreditCard.CardType.AMERICANEXPRESS;
		        			break;
		        		case 3:
		        			type = CreditCard.CardType.JCB;
		        			break;
		        		case 4:
		        			type = CreditCard.CardType.MASTERCARD;
		        			break;
		        		default:
		        			paymentInfo();
		        			break;
	        		}
	        		System.out.print("Please insert your card number: ");
	        		String cardNumber = line.nextLine();
	        		this.creditcard = new CreditCard(amount,type,cardNumber);
	        		
	        		while(!(this.creditcard.isValid())) {
	        			System.out.print("Please insert your card number: ");
	        			cardNumber = line.nextLine();
	        			this.creditcard = new CreditCard(amount,type,cardNumber);
	        		}
	        		log = this.creditcard.log();
	        		break;
        	}
    	}catch(Exception im){
    		paymentInfo();
    	}finally {
    		sc.close();
    		line.close();
    	}
    	
    	
    	printReceipt();
    	System.out.println("\n[Payment Info]: "+log+"\n");
    	System.out.println("=----------- Thank you for shopping with us. Wish you would come back later. Have a good day! ------------=");
    	
    }
    

    public void printReceipt(){
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy-HHmmss");
    	DecimalFormat df = new DecimalFormat("#,###");
    	LocalDateTime now = LocalDateTime.now();
    	String date = now.format(formatter);
        System.out.println("\n[Receipt]");
        System.out.print("\n[Order No.]: "+date);
        System.out.println(displayInfo());
        System.out.println("\n");
        this.c.displayCart();
        System.out.println("\n[Total Price]: "+df.format(this.c.getTotalPrice())+" Baht");
        System.out.println("\n[Shipping Fee]: "+getShippingFee()+" Baht");
        System.out.println("\n[Grand Total]: "+df.format(getShippingFee()+this.c.getTotalPrice())+" Baht");
        
    }
}