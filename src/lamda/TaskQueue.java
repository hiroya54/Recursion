package lamda;

import java.util.HashMap;
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
		
		EventQueue myEventQueueSystem = new EventQueue();
		
		Supplier<String> math = () ->"You will study math today";
		Supplier<String> music = () ->"You will study music today";
		Supplier<String> squat = () ->"You will work out squat 6 times today";
		Supplier<String> pushUp = () ->"You will work out squat 20 Push-up today";
		
		myEventQueueSystem.push("Study", math);
		myEventQueueSystem.push("Study", music);
		myEventQueueSystem.push("WorkOut", squat);
		myEventQueueSystem.push("WorkOut", pushUp);
		
		myEventQueueSystem.dispatch("Study");
		myEventQueueSystem.dispatch("Study");
		myEventQueueSystem.dispatch("Study");
		myEventQueueSystem.dispatch("WorkOut");
		myEventQueueSystem.dispatch("WorkOut");
		myEventQueueSystem.dispatch("Something");
	}
}

class EventQueue{
	HashMap<String, Queue> queues;
	
	public EventQueue() {
		this.queues = new HashMap<>();
	}
	
	void push(String event, Supplier<String> task) {
		if(queues.containsKey(event)) {
			queues.get(event).enqueue(task);
		}else {
			queues.put(event, new Queue());
			queues.get(event).enqueue(task);
		}
	}
	
	boolean eventExists(String event) {
		if(queues.containsKey(event) && queues.get(event).peekFront()!=null) return true;
		else return false;
	}
	
	void run(String event) {
		if(eventExists(event)) {
			System.out.println(queues.get(event).peekFront().get());		
		}
	}
	void dispatch(String event) {
		if(eventExists(event)) System.out.println(queues.get(event).dequeue().get());
		else System.out.println("Event is none!");
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