public class Item {
	
	private String ID;
	private String name;
	private String description;
	private int price;
	private int quantity;
	private StringFormatter sf;
	
	
	
	public Item(String _ID, String _name, String _description, int _price, int _quantity) {
		this.ID = _ID;
		this.name = _name;
		this.description = _description;
		this.price = _price;
		this.quantity = _quantity;
		this.sf = new StringFormatter();
	}
	 
	public Item() {
	
	}

	 public String log() {
		return ID+":\t"+sf.format(name, 20)+"\t"+sf.format(description, 30)+"\t"+Integer.toString(this.price)+" Baht per unit\t"+Integer.toString(this.quantity)+" unit(s)"; 
	 }

	 public String getID(){
		return this.ID;
	 }

	 public String getName(){
		return this.name;
	 }
	 
	 public int getPrice() {
		 return this.price;
	 }
	 
	 public String getDescription() {
		 return this.description;
	 }
	 
	 public int getQuantity() {
		 return this.quantity;
	 }

	 public void addQuantity(int _quantity){
		this.quantity += _quantity;
	 }
	 
}
