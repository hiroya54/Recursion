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