package dataStructure;
class Item{
    int data;
    Item prev;
    Item next;

    public Item(int data){
        this.data=data;
        this.prev=null;
        this.next=null;
    }
}

class DoublyLinkedList{
    Item head;
    Item tail;

    public DoublyLinkedList(int[] arr){
        Item iterator = null;
        for(int i=0;i<arr.length;i++){
            Item node = new Item(arr[i]);

            if(i==arr.length-1){
                this.tail=node;
                node.prev=iterator;
            }

            if(i==0){
                this.head=node;
                iterator=node;
            }else{
                iterator.next=node;
                node.prev=iterator;
                iterator=node;
            }
        }
    }
}



class MyClass{
    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,6,7};
        DoublyLinkedList numList = new DoublyLinkedList(arr);
        System.out.println(numList.head.data);
        System.out.println(numList.head.next.data);
        System.out.println(numList.head.next.prev.data);
        System.out.println(numList.tail.data);
        System.out.println(numList.tail.prev.data);
        System.out.println(numList.tail.prev.prev.data);

    }
}