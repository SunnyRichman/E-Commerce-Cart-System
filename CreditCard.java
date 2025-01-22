import java.text.DecimalFormat;

public class CreditCard extends Payment implements CardNumber{
	

	public static enum CardType{ VISA, AMERICANEXPRESS, JCB, MASTERCARD }; 				
		
	private CardType type;
	private String number;
	private DecimalFormat df;
	
	
	public CreditCard(double amount, CardType type, String number) {
		super("CARD", amount);
		this.type = type;
		this.number = number;
		this.df = new DecimalFormat("0.00");
	}

	public boolean isValid() {
		boolean check = false; 
		
		switch(this.type) {
			case VISA:
				// StartsWith is method that be used to check if the first char in string start with specified value. 
				if(this.number.length() == 16 && this.number.startsWith("4")) check = true;
				else check = false;
				break;
			case AMERICANEXPRESS:
				if(this.number.length() == 15 && (this.number.startsWith("34") || this.number.startsWith("37"))) check = true;
				else check = false;
				break;
			case JCB:
				// We need to parse the first 4 numbers to integer to check whether it's in the range or not.
				if(this.number.length() == 16 && (Integer.parseInt(this.number.substring(0, 4)) >= 3528 && Integer.parseInt(this.number.substring(0, 4)) <= 3589)) check = true;
				else check = false;
				break;
			case MASTERCARD:
				if(this.number.length() == 16 && (this.number.startsWith("51") || this.number.startsWith("52"))) check = true;
				else check = false;
				break;
			default:
				check = false;
				break;
		}
		
		return check;
	}

	public String getFormattedCardNumber(){

		String number = "";
		if(!isValid()) {
			number = "invalid card number"; 
		}else {
			if(this.number.length() == 15) {
				number = this.number.substring(0,4)+" "+this.number.substring(4,10)+" "+this.number.substring(10,15);
			}else if(this.number.length() == 16) {
				number = this.number.substring(0,4)+" "+this.number.substring(4,8)+" "+this.number.substring(8,12)+" "+this.number.substring(12,16);
			}
		}
		
		return number;
	}
	
	public String log() {
		return "CARD::"+ this.df.format(amount)+"::"+this.type+"::"+getFormattedCardNumber();
	}


}
