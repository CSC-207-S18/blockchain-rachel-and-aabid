
import java.util.*;

/**
 * Hash class. Hashes hold arrays of bytes, and they are used in the Block class. 
 * @author nastelin
 * 
 */
public class Hash {
	private byte[] arr;

	/**
	 * Hash, a constructor
	 * 
	 * @param data	an array of bytes
	 * @return none
	 */
	public Hash(byte[] data) {
		arr = data;
	}// Hash

	/**
	 * getData, a getter for the arr variable
	 * 
	 * @param none
	 * @return arr	a byte[]
	 */
	public byte[] getData() {
		return this.arr;
	}// getData

	/**
	 * isValid, a method which checks whether or not a hash is valid
	 * 
	 * @return true	a boolean, if the given hash is valid
	 * @return false	a boolean, if the given has is invalid
	 */
	public boolean isValid() {
		if (this.arr[0] == 0x0 && this.arr[1] == 0x0 && this.arr[2] == 0x0) {
			return true;
		} else {
			return false;
		}
	}// isValid

	/**
	 * toString, a method which creates a String which holds descriptive information about the given 
	 * Hash
	 * 
	 * @param none
	 * @return toReturn		a String
	 */
	public String toString() {
		String toReturn = new String();
		int length = this.arr.length;
		for (int i = 0; i < length; i++) {
			int temp = Byte.toUnsignedInt(this.arr[i]);
			toReturn += String.format("%02x", temp);
		}// for
		return toReturn;
	}// toString

	/**
	 * equals, a method which determines whether or not an object is equal to a given Hash
	 * @param other		an Object
	 * @return true	a boolean, if the object is equal to the given hash
	 * @return false	a boolean, if the object is not equal to the given hash
	 */
	public boolean equals(Object other) {
		if (other instanceof Hash) {
			Hash o = (Hash) other;
			return Arrays.equals(o.arr, this.arr);
		} else {
			return false;
		}// else
	}// equals
}// Hash Class
