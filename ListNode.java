public class ListNode<Any>{

    Any i;
    ListNode<Any> next;

    public ListNode(Any item){
        i = item;
        next = null;
    }


    public ListNode(Any item, ListNode<Any> _next){
        i = item;
        next = _next;
    }

    // Access Methods
    public Any getItem(){
        return i; // Fetches node data.
    }

    public ListNode<Any> getNext(){
        return next; // Get the next node references
    }

    // Setter Methods

    public void setItem(Any item){
        i = item;
    }

    public void setNext(ListNode<Any> _next){
        next = _next;
    }
}