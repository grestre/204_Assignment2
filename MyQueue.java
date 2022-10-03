import java.util.*;

public final class MyQueue<T> implements QueueInterface<T>{
	private T[] queue; 
	int size; 
	int frontIndex, backIndex; 
	
	public MyQueue(int size) {
		queue = (T[]) new Object[size];
		frontIndex = 0; 
		backIndex = -1; 
	}

	@Override
	public boolean isEmpty() {
		boolean status; 
		if(size == 0)
			status = true; 
		else
			status = false; 
		return status; 
	}

	@Override
	public boolean isFull() {
		return size == queue.length; 
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty())
			throw new QueueUnderflowException();  
			
		else {
			T front = queue[frontIndex]; 
			size--; 
			frontIndex = (frontIndex + 1) % queue.length; 
			return front; 
		}
	}
	@Override
	public int size() {
		return size; 
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		
		boolean status; 
		if(isFull()) {
			status = false; 
			throw new QueueOverflowException(); 
		}
		else {
			backIndex = (backIndex + 1)% queue.length; 
			queue[backIndex] = e; 
			size++; 
			status = true; 
		}
		return status; 
	}
	@Override
	public String toString() {
		String str = ""; 
		for(int i = 0; i < size; i++) 
			str += queue[i]; 
		return str; 
		
	}

	@Override
	public String toString(String delimiter) {
		String str = ""; 
		for(int i = 0; i < size; i++) {
			str += delimiter + queue[i];
		}
			StringBuilder sb = new StringBuilder(str); 
			sb.deleteCharAt(0); 
			
		return sb.toString(); 
	}

	@Override
	public void fill(ArrayList<T> list) {		
		for(Object queueList : list)
			try {
				enqueue((T) queueList);
			} catch (QueueOverflowException e) {
			} 
	}
	
}