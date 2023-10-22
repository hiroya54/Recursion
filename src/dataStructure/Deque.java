package dataStructure;

class Deque {
	public Item head;
	public Item tail;
	
	public Deque() {
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
	
	public void enqueueFront(int data) {
		Item newNode = new Item(data);
		if(head==null) {
			this.head=newNode;
			this.tail=newNode;
		}else {
			newNode.next=this.head;
			this.head.prev=newNode;
			this.head=newNode;
		}
	}
	
	public void enqueueBack(int data) {
		Item newNode = new Item(data);
		if(head==null) {
			this.head=newNode;
			this.tail=newNode;
		}else {
			this.tail.next=newNode;
			newNode.prev=this.tail;
			this.tail=newNode;
		}
	}
	
	public Integer dequeueFront() {
		if(this.head==null) return null;
		else if(this.head.equals(this.tail)){
			Item res = this.head;
			this.head=null;
			this.tail=null;
			return res.data;
		}else {
			int res = this.head.data;
			this.head=this.head.next;
			this.head.prev=null;
			return res;
		}

	}
	
	public Integer dequeueBack() {
		if(this.tail==null) return null;
		else if(this.tail.equals(this.head)){
			int res = this.tail.data;
			this.head=null;
			this.tail=null;
			return res;
		}else {
			int res = this.tail.data;
			this.tail=this.tail.prev;
			this.tail.next=null;
			return res;
		}

	}
	
	public static int getMax(int[] arr){
        Deque d = new Deque();
        
        d.enqueueFront(arr[0]);
        
        for(int i=1;i<arr.length;i++) {
        	if(d.peekFront()<arr[i]) d.enqueueFront(arr[i]);
        	else d.enqueueBack(arr[i]);
        }
        return d.peekFront();
        
    }

}
