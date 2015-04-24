package com.corejava.collections;
import java.util.Stack;;

public class BracketExpression {
	private String expression;
	
	public BracketExpression(String expression){
		this.expression = expression;
	}
	
	public boolean isCorrect(){
		Stack<Character> stack = new Stack<>();
		char[] charExpression = this.expression.toCharArray();
		for(char bracket: charExpression){
			if(bracket == '(')
				stack.push(bracket);
			else 
				if(!stack.isEmpty()){
					stack.pop();
				}
				else 
				    return false;
		}
		if(stack.isEmpty())
		    return true;
		return false;
	}
}
