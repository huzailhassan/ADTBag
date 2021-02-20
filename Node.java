class Node<T> {
    
    private T data;
    private Node<T> next;
    
    Node(T data){
        this(data, null);
    }
    
    Node(T data, Node<T> nextNode){
        this.data = data;
        next = nextNode;
    }
    
    T getData() {
        return data;
    }
    
    void setData(T data) {
        this.data = data;
    }
    
    Node<T> getNext() {
        return next;
    }
    
    void setNext(Node<T> nextNode) {
        this.next = nextNode;
    }
}
