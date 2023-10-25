package dataStructure;

import java.util.ArrayList;

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
    public static ArrayList<Integer> getMaxWindows(int[] arr, int k){
    	ArrayList<Integer> res = new ArrayList<>();
    	if(k>arr.length) return null;
    	
    	Deque d = new Deque();
    	
    	//Dequeueの初期化
    	for(int i=0;i<k;i++) {
    		while(d.peekBack()!=null && arr[d.peekBack()]<=arr[i]) {
    			d.dequeueBack();
    		}
    		d.enqueueBack(i);
    	}
    	for(int i=k;i<arr.length;i++) {
    		//dequeの先頭は最大値
    		res.add(arr[d.peekFront()]);
    		//window外にある要素は取り除く
    		while(d.peekFront()!=null && d.peekFront()<=i-k) {
    			d.dequeueFront();
    		}
    		while(d.peekBack()!=null && arr[d.peekBack()]<=arr[i]) {
    			d.dequeueBack();
    		}
    		d.enqueueBack(i);
    	}
    	
    	//最後のmax
    	res.add(arr[d.peekFront()]);
    	return res;
    }
    
    public static int[] minWindowArrK(int[] intArr, int k) {
    	if(intArr.length<k) return null;
    	
    	ArrayList<Integer> res = new ArrayList<>();
    	
    	Deque d = new Deque();
    	
    	//dequeueの初期化
    	for(int i=0;i<k;i++) {
    		//新しい値がdequeの末尾より小さい場合は、dequeの末尾を取り除く
    		while(d.peekBack()!=null && intArr[d.peekBack()]>=intArr[i]) {
    			d.dequeueBack();
    		}
    		d.enqueueBack(i);
    	}
    	
    	for(int i=k;i<intArr.length;i++) {
    		//dequeの先頭はウィンドウの最小値
    		res.add(intArr[d.peekFront()]);
    		//window外の要素を取り除く
    		while(d.peekFront()!=null && d.peekFront()<=i-k) {
    			d.dequeueFront();
    		}
    		//新しい値がdequeの末尾以下の場合、dequeの末尾を取り除く
    		while(d.peekBack()!=null && intArr[d.peekBack()]>=intArr[i]) {
    			d.dequeueBack();
    		}
    		d.enqueueBack(i);
    	}
    	res.add(intArr[d.peekFront()]);
    	
    	int[] resArr = new int[res.size()];
    	
    	for(int i=0;i<resArr.length;i++) {
    		resArr[i]=res.get(i);
    	}
    	
    	return resArr;
    }

}
