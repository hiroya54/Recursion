package dataStructure;

class SinglyLinkedListNode<E>{
    public E data;
    public SinglyLinkedListNode<E> next;

    public SinglyLinkedListNode(E data){
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList<E>{
    public SinglyLinkedListNode<E> head;

    public SinglyLinkedList(SinglyLinkedListNode<E> head){
        this.head = head;
    }
}

class Solution{
    public static SinglyLinkedListNode<Integer> getLinkedList(int[] arr){
        // 関数を完成させてください
    	SinglyLinkedListNode<Integer> current = new SinglyLinkedListNode<>(arr[0]);
    	SinglyLinkedList<Integer> list = new SinglyLinkedList<>(current);
        for(int i=1;i<arr.length;i++){
        	SinglyLinkedListNode<Integer> node = new SinglyLinkedListNode<>(arr[i]);
            current.next=node;
            current = current.next;      
        }

        return list.head;
    }
}

class Main{
	public static void main(String[] args) {
		int[] arr = new int[5];
		for(int i=0;i<5;i++) {
			arr[i] = i;
		}
		SinglyLinkedListNode<Integer> current = Solution.getLinkedList(arr);
		while(current!=null) {
			System.out.println(current.data);
			current = current.next;
		}
	}
}