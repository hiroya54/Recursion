package dataStructure;

import java.util.ArrayList;

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
	 
	 public static int[] consecutiveWalk(int[] arr) {
		 
		 if(arr.length==0) return new int[0];
		 
		 Stack stack = new Stack();	
		 stack.push(arr[0]);
		 for(int i=0;i<arr.length;i++) {
			 if(stack.peek()>=arr[i]) {
				 while(stack.pop()!=null);
			 }
			 stack.push(arr[i]);
		 }
		 
		 ArrayList<Integer> resList = new ArrayList<>();
		 
		 while(stack.peek()!=null) {
			 resList.add(stack.pop());
		 }
		 
		 int[] res = new int[resList.size()];
		 
		 for(int i=0;i<res.length;i++) {
			 res[i]=resList.get(i);
		 }
		 
		 return res;
	}
	 
	 public static int[] dailyStockPrice(int[] stocks) {
		
		 //stack使ったやり方にアップデートする
		int[] res = new int[stocks.length];
		for(int i=0;i<stocks.length;i++) {
			for(int j=i+1;j<stocks.length;j++) {
				if(stocks[i]<stocks[j]) {
					res[i]=j-i;
					break;
				}
			}
		}
		
		return res;
		 
	 }
	     
}

