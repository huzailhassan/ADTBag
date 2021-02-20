public class Node<T> {
    
    private T data;
    private Node<T> next;
    
    public Node(T data){
        this(data, null);
    }
    
    public Node(T data, Node<T> nextNode){
        this.data = data;
        next = nextNode;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public Node<T> getNext() {
        return next;
    }
    
    public void setNext(Node<T> nextNode) {
        this.next = nextNode;
    }
}
