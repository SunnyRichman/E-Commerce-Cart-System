import java.util.InputMismatchException;

public class ArrayList<Any> implements List<Object>{
	
	private int index = -1;
	private Object[] arr = new Object[0];
	
	public ArrayList() {
		
	}
	
	private Object[] stretchingArr(Object[] oldArr) {
		Object[] newArr = new Object[oldArr.length+1];
		System.arraycopy(oldArr, 0, newArr, 0, oldArr.length);
		return newArr;
	}
	
	private Object[] shrinkingArr(Object[] oldArr, int index) {
		Object[] newArr = new Object[oldArr.length-1];
		if(index == 0) {
			System.arraycopy(oldArr, 1, newArr, 0, oldArr.length-1);
		}else {
			System.arraycopy(oldArr, 0, newArr, 0, oldArr.length-1);
			System.arraycopy(oldArr, index+1, newArr, index, oldArr.length-(index+1));
		}
		
		return newArr;
	}
	
	public void remove(int index) {
		this.arr = shrinkingArr(arr,index);
		this.index--;
	}
	
	public void clear() {
		this.index = -1;
		this.arr = new Object[0];
	}
	
	public int size() {
		return this.arr.length;
	}
	
	public Object get(int index) {
		return this.arr[index];
	}

	public void add(Object element) {
		if(element.getClass().equals(ArrayList.class)) {
			throw new InputMismatchException("Invalid input");
		}else {
			this.arr = stretchingArr(this.arr);
			this.index++;
			this.arr[index] = element;
		}
	}

	public void add(Object element, int index) {
		Object[] tmp = new Object[this.arr.length];
		System.arraycopy(this.arr, 0, tmp, 0, this.arr.length);
		
		
		
		if(index<0 || index>this.arr.length-1) {
			throw new ArrayIndexOutOfBoundsException("Invalid Index");
		}else {
			this.arr = stretchingArr(this.arr);
			if(index == 0) {
				System.arraycopy(tmp, 0, this.arr, 1, tmp.length);
				this.arr[0] = element;
			}else {
				for(int i=0;i<index;i++) {
					this.arr[i] = tmp[i];
				}
				for(int i=index;i<tmp.length;i++) {
					this.arr[i+1] = tmp[i];
				}
				this.arr[index] = element;
			}
			this.index++;
		}
		
	}

	public void set(Object element, int index) {
		if(index < 0 || index > arr.length) {
			throw new ArrayIndexOutOfBoundsException("Invalid Index");
		}
		this.arr[index] = element;
		
	}
}