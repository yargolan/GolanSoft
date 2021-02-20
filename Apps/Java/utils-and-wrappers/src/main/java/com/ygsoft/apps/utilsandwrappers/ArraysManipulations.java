package com.ygsoft.apps.utilsandwrappers;


public class ArraysManipulations {
	
	private String[] arrayToManipulate;
	
	
	public ArraysManipulations(String[] arrayToManipulate) {
		this.arrayToManipulate = arrayToManipulate;
	}

	
	
	/**
	 * Removes the first element of the array.
	 * @return The reduced sized array.
	 */
	public String[] shift () {

		if (arrayToManipulate == null || arrayToManipulate.length == 0) {
			return null;
		}

		String[] manipulatedArray = new String[this.arrayToManipulate.length - 1];
		System.arraycopy(this.arrayToManipulate, 1, arrayToManipulate, 0, this.arrayToManipulate.length - 1);
		return manipulatedArray;
	}
	
	
	
	/**
	 * Add a new value at the beginning of the current array.
	 * @param newValue The new value to add as first item in the array.
	 * @return the new array with the new value at the BEGINNING of the array.
	 */
	public String[] unshift (String newValue) {

		String[] newArray;

		if (arrayToManipulate == null || arrayToManipulate.length == 0) {
			newArray = new String[1];
			newArray[0] = newValue;
		}
		else {
			newArray = new String[this.arrayToManipulate.length + 1];
			newArray[0] = newValue;
			System.arraycopy(this.arrayToManipulate, 0, newArray, 1, this.arrayToManipulate.length);
		}

		return newArray;
	}



	/**
	 * @param newValue The new value to put into the current array
	 * @return The new array with the new value at the END of the array
	 */
	public String[] push (String newValue) {

		String[] newArray;

		if (arrayToManipulate == null || arrayToManipulate.length == 0) {
			newArray = new String[1];
			newArray[0] = newValue;
		}
		else {
			newArray = new String[this.arrayToManipulate.length + 1];
			int i;
			for (i = 0; i < arrayToManipulate.length; i++) {
				newArray[i] = newArray[i];
			}
			newArray[i] = newValue;
		}

		return newArray;
	}



	/**
	 * Removes the last element of the array.
	 * @return The reduced sized array.
	 */
	public String[] pop () {

		if (arrayToManipulate == null || arrayToManipulate.length == 0) {
			return null;
		}

		String[] manipulatedArray = new String[this.arrayToManipulate.length - 1];
		System.arraycopy(this.arrayToManipulate, 1, arrayToManipulate, 0, this.arrayToManipulate.length - 1);
		return manipulatedArray;
	}
}



