package dataStructure;



class Queue {
	
	public Node<Integer> head;
	public Node<Integer> tail;
	
	public Queue() {
		this.head=null;
		this.tail=null;
	}
	
	public Integer peekFront() {
		if(this.head==null) return null;
		else return this.head.data;
	}
	
	public Integer peekBack() {
		if(this.tail==null) return null;
		else return this.tail.data;
	}
	
	public void enqueue(int data) {
		Node<Integer> newNode = new Node<>(data);
		if(this.head==null) {
			this.head=newNode;
			this.tail=newNode;
		}else {
			this.tail.next=newNode;
			this.tail=newNode;
		}
	}
	
	public Integer dequeue() {
		if(this.head==null) return null;
		else {
			Node<Integer> deqNode = this.head;
			this.head=this.head.next;
			return deqNode.data;
		}
	}
	

}
