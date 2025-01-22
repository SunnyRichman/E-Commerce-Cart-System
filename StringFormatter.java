public class StringFormatter {
	
	private String str;
	
	public StringFormatter() {
		
	}
	
	public String format(String _str,int max) {
		this.str = _str;
		if(this.str.length()<max) {
			for(int i=this.str.length();i<max;i++) {
				this.str += " ";
			}
			return this.str;
		}else {
			return this.str;
		}
	}
}
