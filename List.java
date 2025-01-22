public interface List<Any> {
	
	public void add(Any element);
	
	public void set(Any element, int index);
	
	public void remove(int index);
	
	public int size();
	
	public Object get(int index);
}
