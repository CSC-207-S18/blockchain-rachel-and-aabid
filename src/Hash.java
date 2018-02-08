
/**
 * 
 */
import java.util.*;

/**
 * @author nastelin
 *
 */
public class Hash {
	private byte[] arr;

	/**
	 * 
	 */
	public Hash(byte[] data) {
		arr = data;
	}// Hash

	public byte[] getData() {
		return this.arr;
	}// getData

	public boolean isValid() {
		for(int i = 0; i < 3; i++) {
			if(this.arr[i] != 0) {
				return false;
			}//if
		}//for
		return true;
	}// isValid

	public String toString() {
		String toReturn = new String();
		int length = this.arr.length;
		for(int i = 0; i < length; i++) {
			int temp = Byte.toUnsignedInt(this.arr[i]);
			toReturn += String.format("%d", temp);
		}//for
		return toReturn;
	}//toString

	public boolean equals(Oject other) {
		
	}//equals

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}// main

}// Hash Class
