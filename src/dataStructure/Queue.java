package dataStructure;



class Queue<E> {
	
	public Node<E> head;
	public Node<E> tail;
	
	public Queue() {
		this.head=null;
		this.tail=null;
	}
	
	public E peekFront() {
		if(this.head==null) return null;
		else return this.head.data;
	}
	
	public E peekBack() {
		if(this.tail==null) return null;
		else return this.tail.data;
	}
	
	public void enqueue(E data) {
		Node<E> newNode = new Node<>(data);
		if(this.head==null) {
			this.head=newNode;
			this.tail=newNode;
		}else {
			this.tail.next=newNode;
			this.tail=newNode;
		}
	}
	
	public E dequeue() {
		if(this.head==null) return null;
		else {
			Node<E> deqNode = this.head;
			this.head=this.head.next;
			return deqNode.data;
		}
	}
	

}
