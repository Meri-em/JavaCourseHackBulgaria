package com.corejava.cars;

public class Car {
	
		@Override
		public String toString(){
			return getClass().getSimpleName() + "@" + this.hashCode();
		}
}
