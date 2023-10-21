package dataStructure;
//単方向リスト
class SinglyLinkedList{
    public Node<Integer> head;

    public SinglyLinkedList(Node<Integer> head){
        this.head = head;
    }
    
    public static Node<Integer> getLinkedList(int[] arr){
        // 関数を完成させてください
    	Node<Integer> current = new Node<>(arr[0]);
    	SinglyLinkedList list = new SinglyLinkedList(current);
        for(int i=1;i<arr.length;i++){
        	Node<Integer> node = new Node<>(arr[i]);
            current.next=node;
            current = current.next;      
        }

        return list.head;
    }
    //偶数番目のノードを2倍にして要素の後ろに追加する
    public Node<Integer> doubleEvenNumber(Node<Integer> head){
    	
    	Node<Integer> iterator = head;
    	int i=0;
    	while(iterator!=null) {
    		Node<Integer> current = iterator;
			iterator = iterator.next;
    		if(i%2==0) {
    			//関数化できそう
    			Node<Integer>  newNode = new Node<>(current.data*2);
    			Node<Integer> tmp = current.next;
    			current.next = newNode;
    			newNode.next = tmp;
    		}
    		i++;
    	}
    	
    	return head;
    }
    
    //先頭にノードを挿入する
    public Node<Integer> insertAtHead(Node<Integer> head, int data){
    	Node<Integer> node = new Node<>(data);
    	node.next = head;
    	this.head = node;
    	return this.head;
    }
    
    //末尾にノードを挿入
    public Node<Integer> insertAtTail(Node<Integer> head, int data){
    	Node<Integer> node = new Node<>(data);
    	Node<Integer> current = head;
    	while(current.next!=null) {
    		current=current.next;
    	}
    	
    	current.next = node;
    	
    	return head;
    }
    
    public void insertAtNext(Node<Integer> current,int data){
    	Node<Integer> node = new Node<>(data);
    	current.next = node;
    }
    //ソートされた２つの片方向リストを連結させる
    public static Node<Integer> mergeTwoSortedLinkedLists(Node<Integer> head1, Node<Integer> head2){
    	Node<Integer> iterator1 = head1;
    	Node<Integer> iterator2 = head2;
    	Node<Integer> current = new Node<>(null);
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
    
    public static Node<Integer> removeNthNode(Node<Integer> head, int n){
    	if(n==0) return head;
    	
    	Node<Integer> dummyNode = new Node<>(n);
    	dummyNode.next = head;
    	
    	Node<Integer> fastIterator = dummyNode;
    	Node<Integer> slowIterator = dummyNode;
    	
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
    public static Node<Integer> reverseLinkedList(Node<Integer> head){
    	//iteratorを定義
    	Node<Integer> iterator1 = head;
    	Node<Integer> iterator2 = null;
    	Node<Integer> iterator3 = null;;
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
    
    public static int linkedListLength(Node<Integer> head){
        int len = 0;
        Node<Integer> iterator=head;
        while(iterator!=null) {
        	iterator=iterator.next;
        	len++;
        }
        return len;
    }
    
    public static int linkedListLastValue(Node<Integer> head){
        Node<Integer> iterator = head;
        while(iterator.next!=null) {
        	iterator=iterator.next;
        }
        return iterator.data;
    }
    
    
    public static Node<Integer> deleteTail(Node<Integer> head){
    	Node<Integer> iterator = head;
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
    
    public static int findMinNum(Node<Integer> head){
        Node<Integer> iterator = head;
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
    
    public static int linkedListSearch(Node<Integer> head, int data){
        Node<Integer> iterator = head;
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
    
    public static Node<Integer> insertAtPosition(Node<Integer> head, int position, int data){
    	Node<Integer> iterator = head;
    	for(int i=0;i<position;i++) {
    		if(iterator.next==null) return head;
    		iterator=iterator.next;
    	}
    	Node<Integer> newNode = new Node<>(data);
    	newNode.next=iterator.next;
    	iterator.next=newNode;
    	
    	return head;
    }
    
    public static Node<Integer> insertNodeInSorted(Node<Integer> head, int data){
        Node<Integer> iterator = head;
        Node<Integer> newNode = new Node<>(data);
        
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
    public static int findMergeNode(Node<Integer> headA, Node<Integer> headB){
        Node<Integer> iterator1 = headA;
        Node<Integer> iterator2 = headB;
        
        while(iterator1!=null) {
        	while(iterator2!=null){
        		if(iterator1.data.equals(iterator2.data)) {
        			Node<Integer> candidate1=iterator1;
        	        Node<Integer> candidate2=iterator2;
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
    
    public static Node<Integer> reproduceByN(Node<Integer> head, int n){
        // 関数を完成させてください
    	int len = linkedListLength(head);
    	Node<Integer> current=head;
    	//currentを末尾まで移動
    	while(current.next!=null) {
    		current=current.next;
    	}
    	
    	Node<Integer> iterator=head;
    	
    	for(int i=0;i<n-1;i++) {
    		for(int j=0;j<len;j++) {
    			Node<Integer> newNode = new Node<>(iterator.data);
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
		Node<Integer> current = SinglyLinkedList.getLinkedList(arr);
		SinglyLinkedList list = new SinglyLinkedList(current);
		while(current!=null) {
			System.out.println(current.data);
			current = current.next;
		}
		Node<Integer> newHead = list.insertAtHead(list.head, 100);
		
		current = newHead;
		while(current!=null) {
			System.out.println(current.data);
			current = current.next;
		}
		
	}
}