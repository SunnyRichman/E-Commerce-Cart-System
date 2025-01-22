import java.text.DecimalFormat;

public class Cash extends Payment {
	
	
	private double cash;
	private DecimalFormat df;
	
	public Cash(double amount, double cash) {
		super("CASH", amount);
		this.cash = cash;
		this.df = new DecimalFormat("0.00");
	}
		
	public double getChange() {
		double change = this.cash - amount;
		double dec = change - (int) change;
		if(isValid() == false) change = 0;
		else {
			
			if(dec >= 0 && dec < 0.25) {
				dec = 0;
				change = (int)change + dec;
			}else if(dec >= 0.25 && dec < 0.5) {
				dec = 0.25;
				change = (int)change + dec;
			}else if(dec >= 0.5 && dec < 0.75) {
				dec = 0.50;
				change = (int)change + dec;
			}else if(dec >= 0.75) {
				dec = 0.75;
				change = (int)change + dec;
			}
		}
		return change;
	}
	
	public boolean isValid() {
		if(this.cash < amount) return false;
		else return true;
	}
	
	public String log() {
		return "CASH::"+ this.df.format(amount)+"::"+df.format(this.cash)+"::"+df.format(getChange());
	}
	

	
	
	
}