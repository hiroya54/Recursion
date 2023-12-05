package lamda;

import java.util.function.Supplier;

class TaskQueue {
	
	Queue queue;
	
	TaskQueue(){
		this.queue=new Queue();
	}
	
	void push(Supplier<String> task) {
		queue.enqueue(task);
	}
	
	boolean taskExist() {
		if(queue.peekFront()==null) return false;
		else return true;
	}
	
	void run() {
		if(queue.peekFront()==null) return;
		else {
			System.out.println(queue.dequeue().get());
		}
	}
	
	public static void main(String[] args) {
		TaskQueue scheduler = new TaskQueue();
		
		Supplier<String> firstTask = () ->"Running the first function!!!";
		Supplier<String> secondTask = () ->"Running the second function~~~";
		Supplier<String> thirdTask = () ->"Running the third function>>>";
		Supplier<String> fourthTask = () ->"Running the fourth function<<<";
		
		scheduler.push(firstTask);
		scheduler.push(secondTask);
		scheduler.push(thirdTask);
		scheduler.push(fourthTask);
		
		while(scheduler.taskExist()) {
			scheduler.run();
		}
	}
}

class Item{
	Supplier<String> data;
	Item next;
	
	Item(){
		this.data=null;
		this.next=null;
	}
}

class Queue{
	Item head;
	Item tail;
	
	Queue(){
		this.head=null;
		this.tail=null;
	}
	
	Supplier<String> peekFront(){
		if(head==null) return null;
		else return this.head.data;
	}
	
	void enqueue(Supplier<String> task) {
		Item item = new Item();
		item.data=task;
		if(head==null) {
			this.head=item;
			this.tail=item;
		}else {
			this.tail.next=item;
			this.tail=item;
		}
	}
	
	Supplier<String> dequeue() {
		if(head==null) return null;
		else if(head.equals(tail)) {
			Item res = head;
			this.head=null;
			this.tail=null;
			return res.data;
		}else {
			Item res = head;
			head = head.next;
			return res.data;
		}
	}
}