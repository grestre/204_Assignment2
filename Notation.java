import java.util.*;
public class Notation{
	public Notation() {	
	}
	/**
	 * 
	 * @param postfixExpr
	 * @return A postfix evaluated
	 * @throws InvalidNotationFormatException
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException{
		MyStack<Double> valueStack = new MyStack<Double>(100); 
		for(int a = 0; a < postfixExpr.length(); a++) {
			try {
				if(Character.isDigit(postfixExpr.charAt(a))) {
					double val = Character.digit(postfixExpr.charAt(a), 10); 
					valueStack.push(val); 
				}
				else if(postfixExpr.charAt(a) == '+' || postfixExpr.charAt(a) == '-' || postfixExpr.charAt(a) == '*' || postfixExpr.charAt(a) == '/') {
					double num1 = valueStack.pop(); 
					double num2 = valueStack.pop();
					switch(postfixExpr.charAt(a)) {
					case '+': 
						valueStack.push(num2 + num1);
						break; 
					case '*': 
						valueStack.push(num2 * num1); 
						break; 
					case '-': 
						valueStack.push(num2 - num1); 
						break; 
					case '/': 
						valueStack.push(num2 / num1); 
						break; 
					}	
				}
			}catch(InvalidNotationFormatException e) {
				if(valueStack.isEmpty()); 
			}
		}
		return valueStack.pop(); 
	}
	/**
	 * 
	 * @param postfix
	 * @return An infix converted from a postfix
	 * @throws InvalidNotationFormatException
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException{
		MyStack<String> operatorStack = new MyStack<String>(100); 
		try {
			for(int a = 0; a < postfix.length(); a++) {
				if(postfix.charAt(a) == '+' || postfix.charAt(a) == '-' || postfix.charAt(a) == '*' || postfix.charAt(a) == '/') {
					String a1 = operatorStack.pop(); 				
					String a2 = operatorStack.pop();  
					operatorStack.push("(" + a2 + postfix.charAt(a) + a1 + ")"); 
				}
				else if(Character.isDigit(postfix.charAt(a))){
					operatorStack.push(postfix.charAt(a) + ""); 
				}
			}
			}catch(InvalidNotationFormatException e) {
				
			}		
		return operatorStack.pop(); 
	}
	/**
	 * 
	 * @param infix
	 * @return A postfix ocnverted from an infix
	 * @throws InvalidNotationFormatException
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException{
		MyStack<String> operatorStack = new MyStack<String>(100); 
		MyQueue<String> operatorQueue = new MyQueue<String>(100); 
		String newPostfix = ""; 
		try {
			for(int a = 0; a < infix.length(); a++) {
				if(Character.isDigit(infix.charAt(a))) {
					operatorQueue.enqueue(infix.charAt(a) + ""); 
				}
				else if(infix.charAt(a) == '+' || infix.charAt(a) == '-' || infix.charAt(a) == '*' || infix.charAt(a) == '/') {			
					operatorStack.push(infix.charAt(a) + ""); 
				}	
				else if(infix.charAt(a) == '(') {		
				}
				else if(infix.charAt(a) == ')') {
					operatorQueue.enqueue(operatorStack.pop()); 
					if(infix.charAt(a) != ')')
						throw new InvalidNotationFormatException (); 		
				}
			}
			while(!operatorQueue.isEmpty())
				newPostfix += operatorQueue.dequeue(); 
			
		}catch(InvalidNotationFormatException e) {	
		}
		return newPostfix; 
	}
}