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
    	if(head1.data<=head2.data) {
    		current.data=head1.data;
    		iterator1=iterator1.next;
    	}else {
    		current.data=head2.data;
    		iterator2=iterator2.next;
    	}
    	//各リストの先頭ノードを大小比較し、小さい方をソートずみリストの末尾に挿入。同じ場合は両方とも挿入
    	//挿入した側のノードはnextをカレントノードとする
    	//どちらかのカレントノードがnullになった場合、もう一方のノードを全てソートずみリストに追加	
    	while(iterator1!=null || iterator2!=null) {
    		if(iterator1==null) {
    			current.next=iterator2;
    			break;
    		}else if(iterator2==null) {
    			current.next=iterator1;
    			break;
    		}else {
    			if(iterator1.data<iterator2.data) {
    				SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(iterator1.data);
    				current.next=node;
    				current=current.next;
    				iterator1=iterator1.next;
    			}else if(iterator1.data>iterator2.data) {
    				SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(iterator2.data);
    				current.next=node;
    				current=current.next;
    				iterator2=iterator2.next;
    			}else {
    				SinglyLinkedListNode<Integer> node1 = new SinglyLinkedListNode<>(iterator1.data);
    				SinglyLinkedListNode<Integer> node2 = new SinglyLinkedListNode<>(iterator2.data);
    				current.next=node1;
    				current.next.next=node2;
    				current=current.next.next;
    				iterator1=iterator1.next;
    				iterator2=iterator2.next;
    			}
    		}
    	}
    	
    	return list.head;
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