package dataStructure;

import java.util.ArrayList;

class Stack {
	
	public Node<Integer> head;
	
	public Stack() {
		this.head=null;
	}
	
	public void push(int data) {
		Node<Integer> tmp = this.head;
		this.head=new Node<Integer>(data);
		this.head.next=tmp;
	}
	
	public Integer pop() {
		if(this.head==null) return null;
		Node<Integer> tmp = this.head;
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
	 
	 public static String diceStreakGamble(int[] player1, int[] player2, int[] player3, int[] player4){
	    
	        Stack s1 =new Stack();
	        Stack s2 =new Stack();
	        Stack s3 =new Stack();
	        Stack s4 =new Stack();

	        int prize1 = s1.getPrize(player1,s1);
	        int prize2 = s2.getPrize(player2,s2);
	        int prize3 = s3.getPrize(player3,s3);
	        int prize4 = s4.getPrize(player4,s4);
	        
	        int winnerIdx = 1;
	        int winnerPrize = prize1;
	        Stack winnerStack = s1;
	        if(winnerPrize<prize2){
	            winnerIdx = 2;
	            winnerPrize=prize2;
	            winnerStack=s2;
	        }
	        if(winnerPrize<prize3){
	            winnerIdx = 3;
	            winnerPrize=prize3;
	            winnerStack=s3;
	        }
	        if(winnerPrize<prize4){
	            winnerIdx = 4;
	            winnerPrize=prize4;
	            winnerStack=s4;
	        }

	        String res = "Winner: Player " +winnerIdx+" won $"+ winnerPrize + " by rolling [";
	        while(winnerStack.peek()!=null){
	            res=res+winnerStack.pop();
	            if(winnerStack.peek()!=null){
	                res+=",";
	            }else{
	                res+="]";
	                break;
	            }
	        }

	        return res;
	    
	    }
	    public int getPrize(int[] player,Stack s){
	        int len = player.length;
	        s.push(player[len]);

	        int prize=4;
	        
	         for(int i=len-2;i>=0;i--){
	            if(player[i]<=s.peek()){
	                s.push(player[i]);
	                prize+=4;
	            }else{
	                break;
	            }
	         } 
	         return prize;
	    }
	     
}

