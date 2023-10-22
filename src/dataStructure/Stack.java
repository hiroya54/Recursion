package dataStructure;
class Item{
    public int data;
    public Item next;

    public Item(int data){
        this.data = data;
        this.next = null;
    }
}

class Stack {
	
	public Item head;
	
	public Stack() {
		this.head=null;
	}
	
	public void push(int data) {
		Item tmp = this.head;
		this.head=new Item(data);
		this.head.next=tmp;
	}
	
	public Integer pop() {
		if(this.head==null) return null;
		Item tmp = this.head;
		this.head=this.head.next;
		return tmp.data;
	}
	
	public Integer peek() {	
		return this.head==null ? null : this.head.data;
	}
	
	 public static int[] reverse(int[] arr) {
		 
		 Stack stack = new Stack();
		 for(int i=0;i<arr.length;i++) {
			 stack.push(arr[i]);
		 }
		 int[] res = new int[arr.length];
		 for(int i=0;i<arr.length;i++) {
			 res[i] = stack.pop();
		 }
		 
		 return res;
	 }
	     
}

