import java.text.DecimalFormat;

public abstract class Payment{

	public double amount;
	public String method;
	private DecimalFormat df;
	
	public Payment(String method, double amount){
		this.amount = amount;
		this.method = method;
		this.df = new DecimalFormat("0.00");
	}
	
	public String getMethod() {
		return method;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public String log() {
		return (isValid()? "[VALID] ":"[VOID] ") + method + "::" + this.df.format(amount);
	}
	
	public abstract boolean isValid();
	
		
	
}
