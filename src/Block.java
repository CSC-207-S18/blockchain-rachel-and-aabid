import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.nio.ByteBuffer;
/**
 * Block class. Blocks store Hash objects, and the class includes methods to create Hash objects given a nonce
 * @author nastelin
 *
 */
public class Block {
	private int blockNum;
	private int data;
	private Hash prevHash;
	private long nonce;
	private Hash thisHash;

	/**
	 * makeHash, a function which creates a valid hash
	 * 
	 * @param num		an int
	 * @param amount	an int
	 * @param prevHash	a Hash
	 * @param nonce		a long
	 * @return	newHash	a Hash
	 * @throws NoSuchAlgorithmException
	 */
	public static Hash makeHash(int num, int amount, Hash prevHash, long nonce)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("sha-256");
		ByteBuffer buffer = ByteBuffer.allocate(16).putInt(num).putInt(amount)
				.putLong(nonce);
		md.update(buffer.array());
		if (prevHash != null) {
			md.update(prevHash.getData());
		}
		byte[] newBytes = md.digest();
		Hash newHash = new Hash(newBytes);
		return newHash;
	}//makeHash

	/**
	 * Block constructor
	 * 
	 * @param num	an int
	 * @param amount	an int
	 * @param prevHash	a Hash
	 * @return none
	 * @throws NoSuchAlgorithmException
	 */
	public Block(int num, int amount, Hash prevHash)
			throws NoSuchAlgorithmException {
		this.blockNum = num;
		this.data = amount;
		this.prevHash = prevHash;
		this.thisHash = makeHash(this.blockNum, this.data, this.prevHash,
				this.nonce);
		while (!this.thisHash.isValid()) {
			this.nonce++;
			this.thisHash = makeHash(this.blockNum, this.data, this.prevHash,
					this.nonce);
		}// while
	}// Block

	/**
	 * Block constructor
	 * 
	 * @param num	an int
	 * @param amount	an int
	 * @param prevHash	a Hash
	 * @param nonce	a long
	 * @return none
	 * @throws NoSuchAlgorithmException
	 */
	public Block(int num, int amount, Hash prevHash, long nonce)
			throws NoSuchAlgorithmException {
		this.blockNum = num;
		this.data = amount;
		this.prevHash = prevHash;
		this.nonce = nonce;
		this.thisHash = makeHash(this.blockNum, this.data, this.prevHash,
				this.nonce);
		while (!this.thisHash.isValid()){
			this.nonce++;
			this.thisHash = makeHash(this.blockNum, this.data, this.prevHash,
					this.nonce);
		}
	}// Block

	/**
	 * getNum, a getter for blockNum variable
	 * 
	 * @param none
	 * @return the blockNum for the object it was called on
	 */
	public int getNum() {
		return this.blockNum;
	}// getNum

	/**
	 * getAmount, a getter for the data variable
	 * 
	 * @param none
	 * @return data		an int
	 */
	public int getAmount() {
		return this.data;
	}// getAmount

	/**
	 * setHash, a setter for the thisHash variable
	 * 
	 * @param newHash	a Hash
	 * @return none
	 */
	public void setPrevHash(Hash newHash){
		this.prevHash = newHash;
	}

	/**
	 * getNonce, a getter for the nonce variable
	 * 
	 * @param none
	 * @return nonce	a long
	 */
	public long getNonce() {
		return this.nonce;
	}// getNonce

	/**
	 * getPrevHash, a getter for the prevHash variable
	 * 
	 * @param none
	 * @return hash		a Hash
	 */
	public Hash getPrevHash() {
		return this.prevHash;
	}// getPrevHash

	/**
	 * getHash, a getter for the thisHash variable
	 * 
	 * @param none
	 * @return hash 	a Hash
	 */
	public Hash getHash() {
		return this.thisHash;
	}// getHash

	/**
	 * toString, a method which creates a String that describes the given Block
	 * 
	 * @param none
	 * @return toReturn		a String
	 */
	public String toString() {
		String toReturn = new String();
		toReturn += ("Block " + this.blockNum + " (Amount: " + this.data
				+ ", Nonce: " + this.nonce + ", prevHash: " + this.prevHash
				+ ", hash: " + this.thisHash + ")");
		return toReturn;
	}// toString

}// Block class
