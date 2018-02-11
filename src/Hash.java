

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
		if (this.arr[0] == 0x0 && this.arr[1] == 0x0 && this.arr[2] == 0x0) {
			return true;
		} else {return false;}
	}// isValid

	public String toString() {
		String toReturn = new String();
		int length = this.arr.length;
		for(int i = 0; i < length; i++) {
			int temp = Byte.toUnsignedInt(this.arr[i]);
			toReturn += String.format("%02x", temp);
		}//for
		return toReturn;
	}//toString


	public boolean equals(Object other) {
		if (other instanceof Hash) {
			Hash o = (Hash) other;
			return Arrays.equals(o.arr, this.arr);
		} else {
			return false;
		}
	}
}// Hash Class
