package dataStructure;

//リストのノード
class SinglyLinkedListNode<E>{
    public E data;
    public SinglyLinkedListNode<E> next;

    public SinglyLinkedListNode(E data){
        this.data = data;
        this.next = null;
    }
}
//単方向リスト
class SinglyLinkedList{
    public SinglyLinkedListNode<Integer> head;

    public SinglyLinkedList(SinglyLinkedListNode<Integer> head){
        this.head = head;
    }
    
    public static SinglyLinkedListNode<Integer> getLinkedList(int[] arr){
        // 関数を完成させてください
    	SinglyLinkedListNode<Integer> current = new SinglyLinkedListNode<>(arr[0]);
    	SinglyLinkedList list = new SinglyLinkedList(current);
        for(int i=1;i<arr.length;i++){
        	SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(arr[i]);
            current.next=node;
            current = current.next;      
        }

        return list.head;
    }
    //偶数番目のノードを2倍にして要素の後ろに追加する
    public SinglyLinkedListNode<Integer> doubleEvenNumber(SinglyLinkedListNode<Integer> head){
    	
    	SinglyLinkedListNode<Integer> iterator = head;
    	int i=0;
    	while(iterator!=null) {
    		SinglyLinkedListNode<Integer> current = iterator;
			iterator = iterator.next;
    		if(i%2==0) {
    			//関数化できそう
    			SinglyLinkedListNode<Integer>  newNode = new SinglyLinkedListNode<>(current.data*2);
    			SinglyLinkedListNode<Integer> tmp = current.next;
    			current.next = newNode;
    			newNode.next = tmp;
    		}
    		i++;
    	}
    	
    	return head;
    }
    
    //先頭にノードを挿入する
    public SinglyLinkedListNode<Integer> insertAtHead(SinglyLinkedListNode<Integer> head, int data){
    	SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(data);
    	node.next = head;
    	this.head = node;
    	return this.head;
    }
    
    //末尾にノードを挿入
    public SinglyLinkedListNode<Integer> insertAtTail(SinglyLinkedListNode<Integer> head, int data){
    	SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(data);
    	SinglyLinkedListNode<Integer> current = head;
    	while(current.next!=null) {
    		current=current.next;
    	}
    	
    	current.next = node;
    	
    	return head;
    }
    
    public void insertAtNext(SinglyLinkedListNode<Integer> current,int data){
    	SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(data);
    	current.next = node;
    }
    //ソートされた２つの片方向リストを連結させる
    public static SinglyLinkedListNode<Integer> mergeTwoSortedLinkedLists(SinglyLinkedListNode<Integer> head1, SinglyLinkedListNode<Integer> head2){
    	SinglyLinkedListNode<Integer> iterator1 = head1;
    	SinglyLinkedListNode<Integer> iterator2 = head2;
    	SinglyLinkedListNode<Integer> current = new SinglyLinkedListNode<>(null);
    	SinglyLinkedList list = new SinglyLinkedList(current);

    	while(iterator1!=null || iterator2!=null) {
    		if(iterator1==null) {
    			current.next=iterator2;
    			break;
    		}else if(iterator2==null) {
    			current.next=iterator1;
    			break;
    		}else {
    			if(iterator1.data<=iterator2.data) {
    				current.next=iterator1;
    				current=current.next;
    				iterator1=iterator1.next;
    			}else {
    				current.next=iterator2;
    				current=current.next;
    				iterator2=iterator2.next;
    			}
    		}
    	}
    	
    	return list.head.next;
    }
    
    public static SinglyLinkedListNode<Integer> removeNthNode(SinglyLinkedListNode<Integer> head, int n){
    	if(n==0) return head;
    	
    	SinglyLinkedListNode<Integer> dummyNode = new SinglyLinkedListNode<>(n);
    	dummyNode.next = head;
    	
    	SinglyLinkedListNode<Integer> fastIterator = dummyNode;
    	SinglyLinkedListNode<Integer> slowIterator = dummyNode;
    	
    	for(int i=0;i<n;i++) {
    		if(fastIterator.next==null) return head;
    		fastIterator = fastIterator.next;
    	}
    	
    	while(fastIterator.next!=null) {
    		fastIterator=fastIterator.next;
    		slowIterator=slowIterator.next;
    	}
    	slowIterator.next=slowIterator.next.next;
    	
    	return dummyNode.next;
    }
    public static SinglyLinkedListNode<Integer> reverseLinkedList(SinglyLinkedListNode<Integer> head){
    	//iteratorを定義
    	SinglyLinkedListNode<Integer> iterator1 = head;
    	SinglyLinkedListNode<Integer> iterator2 = null;
    	SinglyLinkedListNode<Integer> iterator3 = null;;
    	//リスト長が1の場合はheadを返す
    	if(iterator1.next!=null) {
    		iterator2 = iterator1.next;
    	}else {
    		return head;
    	}
    	//リスト長が2の場合は要素を入れ替えて返す
    	if(iterator2.next!=null) {
    		iterator3=iterator2.next;
    	}else {
    		iterator2.next=iterator1;
    		iterator1.next=null;
    		return iterator2;
    	}
    	
    	//逆向きに変換
    	iterator1.next=null;
    	while(iterator3!=null) {
    		iterator2.next=iterator1;
    		iterator1=iterator2;
    		iterator2=iterator3;
    		iterator3=iterator3.next;
    	}
    	iterator2.next=iterator1;
    	
    	return iterator2;
    }
    
    public static int linkedListLength(SinglyLinkedListNode<Integer> head){
        int len = 0;
        SinglyLinkedListNode<Integer> iterator=head;
        while(iterator!=null) {
        	iterator=iterator.next;
        	len++;
        }
        return len;
    }
    
    public static int linkedListLastValue(SinglyLinkedListNode<Integer> head){
        SinglyLinkedListNode<Integer> iterator = head;
        while(iterator.next!=null) {
        	iterator=iterator.next;
        }
        return iterator.data;
    }
    
    
    public static SinglyLinkedListNode<Integer> deleteTail(SinglyLinkedListNode<Integer> head){
    	SinglyLinkedListNode<Integer> iterator = head;
    	if(iterator.next==null) {
    		head=null;
    	}else {
    		while(iterator.next.next!=null) {
            	iterator=iterator.next;
            }
            iterator.next=null;
    	}
        return head;
    }
    
    public static int findMinNum(SinglyLinkedListNode<Integer> head){
        SinglyLinkedListNode<Integer> iterator = head;
        int min = head.data;
        int minIndex = 0;
        iterator=iterator.next;
        int idx = 1;
        while(iterator!=null) {
        	if(iterator.data <= min) {
        		min=iterator.data;
        		minIndex=idx;
        	}
        	iterator=iterator.next;
        	idx++;
        }
        return minIndex;
    }
    
    public static int linkedListSearch(SinglyLinkedListNode<Integer> head, int data){
        SinglyLinkedListNode<Integer> iterator = head;
        int idx=0;
        while(iterator!=null) {
        	if(iterator.data==data) {
        		return idx;
        	}
        	iterator=iterator.next;
        	idx++;
        }
        return -1;
    }
    
    public static SinglyLinkedListNode<Integer> insertAtPosition(SinglyLinkedListNode<Integer> head, int position, int data){
    	SinglyLinkedListNode<Integer> iterator = head;
    	for(int i=0;i<position;i++) {
    		if(iterator.next==null) return head;
    		iterator=iterator.next;
    	}
    	SinglyLinkedListNode<Integer> newNode = new SinglyLinkedListNode<>(data);
    	newNode.next=iterator.next;
    	iterator.next=newNode;
    	
    	return head;
    }
    
    public static SinglyLinkedListNode<Integer> insertNodeInSorted(SinglyLinkedListNode<Integer> head, int data){
        SinglyLinkedListNode<Integer> iterator = head;
        SinglyLinkedListNode<Integer> newNode = new SinglyLinkedListNode<>(data);
        
        if(iterator.data>data) {
        	newNode.next=iterator;
    		head=newNode;
    		return head;
        }
        
        while(iterator.next!=null) {
        	if(iterator.data<=data && iterator.next.data>=data) {
        		newNode.next=iterator.next;
        		iterator.next=newNode;
        		return head;
        	}
        	iterator=iterator.next;
        }
        
    	iterator.next = newNode;
    	return head;
    }
    public static int findMergeNode(SinglyLinkedListNode<Integer> headA, SinglyLinkedListNode<Integer> headB){
        SinglyLinkedListNode<Integer> iterator1 = headA;
        SinglyLinkedListNode<Integer> iterator2 = headB;
        
        while(iterator1!=null) {
        	while(iterator2!=null){
        		if(iterator1.data.equals(iterator2.data)) {
        			SinglyLinkedListNode<Integer> candidate1=iterator1;
        	        SinglyLinkedListNode<Integer> candidate2=iterator2;
        	        boolean chk = true;
        	        while(candidate1!=null && candidate2!=null) {
        	    	   if(candidate1.data.equals(candidate2.data)) {
           	        	candidate1=candidate1.next;
           	        	candidate2=candidate2.next;
           	        	}else {
           	        		chk=false;
           	        		break;
           	        	}
        	        }
        	        if(chk && candidate1==null && candidate2==null) return iterator1.data;
        	        
                }
        		iterator2=iterator2.next;
        	}
        	iterator1=iterator1.next;
        	iterator2=headB;
        }
        return -1;
    }
    
    public static SinglyLinkedListNode<Integer> reproduceByN(SinglyLinkedListNode<Integer> head, int n){
        // 関数を完成させてください
    	int len = linkedListLength(head);
    	SinglyLinkedListNode<Integer> current=head;
    	//currentを末尾まで移動
    	while(current.next!=null) {
    		current=current.next;
    	}
    	
    	SinglyLinkedListNode<Integer> iterator=head;
    	
    	for(int i=0;i<n-1;i++) {
    		for(int j=0;j<len;j++) {
    			SinglyLinkedListNode<Integer> newNode = new SinglyLinkedListNode<>(iterator.data);
    			current.next=newNode;
    			current=current.next;
    			iterator=iterator.next;
    		}
    		iterator=head;
    	}
    	return head;
    }
    
}

class Main{
	public static void main(String[] args) {
		int[] arr = new int[5];
		for(int i=0;i<5;i++) {
			arr[i] = i;
		}
		SinglyLinkedListNode<Integer> current = SinglyLinkedList.getLinkedList(arr);
		SinglyLinkedList list = new SinglyLinkedList(current);
		while(current!=null) {
			System.out.println(current.data);
			current = current.next;
		}
		SinglyLinkedListNode<Integer> newHead = list.insertAtHead(list.head, 100);
		
		current = newHead;
		while(current!=null) {
			System.out.println(current.data);
			current = current.next;
		}
		
	}
}