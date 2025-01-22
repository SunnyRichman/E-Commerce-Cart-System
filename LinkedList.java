import java.util.NoSuchElementException;
public class LinkedList<Any> implements List<Object> {
	
	private ListNode<Object> front;

	public LinkedList(){
		this.front = null;
	}

	public boolean isEmpty() {
		return this.front == null;
	}
	
	public boolean contains(Object element) {
		for(ListNode<Object> node = this.front;node!=null;node=node.getNext()) {
			if(node.getItem().equals(element)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void add(Object element) {
		if(isEmpty()) {
			this.front = new ListNode<Object>(element);
		}else {
			ListNode<Object> last = front;
			while(last.getNext() != null) {
				last = last.getNext();
			}
			last.setNext(new ListNode<Object>(element));
		}
	}

	@Override
	public void set(Object element, int index) {
		ListNode<Object> node = front;
		for(int i=0;i<index;i++) {
			node = node.getNext();
		}
		node.setItem(element);
	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (index == 0) {
            this.front = (this.front).next;
        } else {
            ListNode<Object> current = this.front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
	}
	
	public void remove(Object element) {
        if (front == null) {
            throw new NoSuchElementException("List is empty");
        }

        if (front.i.equals(element)) {
            front = front.next;
        } else {
            ListNode<Object> current = front;
            while (current.next != null) {
                if (current.next.i.equals(element)) {
                    current.next = current.next.next;
                    return; // Element found and removed
                }
                current = current.next;
            }

            throw new NoSuchElementException("Element not found in the list");
        }
    }

	@Override
	public int size() {
		int size = 0;
		for(ListNode<Object> node = front;node!=null;node=node.getNext()) {
			size++;
		}
		return size;
	}

	@Override
	public Object get(int index) {
		ListNode<Object> node = front;
		for(int i=0;i<index;i++) {
			node = node.getNext();
		}
		return node.getItem();
	}

}
