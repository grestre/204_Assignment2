import java.util.*;
public class MyStack <T> implements StackInterface<T>{
	private T[] stack; 
	int size; 
	int topIndex, bottomIndex; 
	
	public MyStack(int size) {
		stack = (T[]) new Object[size];
		topIndex = -1; 
		bottomIndex = 0; 
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
		return stack.length == size; 
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty())
			throw new StackUnderflowException("Stack is empty"); 
		else {
			T indexTop = stack[topIndex]; 
			size--; 
			topIndex = (topIndex - 1) % stack.length; 
			return indexTop; 
		}
	}

	@Override
	public T top() throws StackUnderflowException {
		if(isEmpty())
			throw new StackUnderflowException("Stack is empty"); 
		else {
			T indexTop = stack[topIndex]; 
			return indexTop; 
		}
	}

	@Override
	public int size() {
		return size; 
	}

	@Override
	public boolean push(T e) throws StackOverflowException {
		boolean status; 
		if(isFull()) {
			status = false; 
			throw new StackOverflowException("Stack is full");  
		}
		else {
			topIndex = (topIndex + 1) % stack.length; 
			stack[topIndex] = e; 
			size++;
			status = true; 
		}
		return status; 
	}
	@Override
	public String toString() {
		String str= "";
		for(int i = 0; i < size; i++)
			str += stack[i]; 
		return str; 
	}

	@Override
	public String toString(String delimiter) {
		String str = ""; 
		for(int i = 0; i < size; i++)
			str += delimiter + stack[i]; 
		StringBuilder sb = new StringBuilder(str); 
		sb.deleteCharAt(0); 
		return sb.toString(); 
	}

	@Override
	public void fill(ArrayList<T> list) {
		for(Object stackList : list)
			try {
				push((T) stackList); 
			}catch(StackOverflowException e) {
				
			}
	}
	
}
