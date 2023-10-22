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
