// import java.util.*;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.nio.ByteBuffer;

public class Block {
	private int blockNum; 
	private int data;
	private Hash prevHash;
	private long nonce; 
	private Hash thisHash;
	
	public static Hash makeHash(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("sha-256");
		ByteBuffer buffer = ByteBuffer.allocate(16).putInt(num).putInt(amount).putLong(nonce);
		md.update(buffer.array());
		if (prevHash != null) {
			md.update(prevHash.getData());
		}
		byte[] newBytes = md.digest();
		Hash newHash = new Hash(newBytes);
		return newHash;
	} 

	
	public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
		this.blockNum = num;
		this.data = amount; 
		this.prevHash = prevHash;
		this.thisHash = makeHash(this.blockNum, this.data, this.prevHash, this.nonce);
		while (!this.thisHash.isValid()) {
			this.nonce++;
			this.thisHash = makeHash(this.blockNum, this.data, this.prevHash, this.nonce);
		}
		
		
	}
	public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
		this.blockNum = num;
		this.data = amount;
		this.prevHash = prevHash;
		this.nonce = nonce;
		this.thisHash = makeHash(this.blockNum, this.data, this.prevHash, this.nonce);
	}
	public int getNum(){
		return this.blockNum;
	}
	public int getAmount(){
		return this.data;
	}
	public long getNonce(){
		return this.nonce;
	}
	public Hash getPrevHash(){
		return this.prevHash;
	}
	public Hash getHash(){
		return this.thisHash;
	}
	public String toString(){
		String toReturn = new String();
		toReturn += ("Block " + this.blockNum + " (Amount: " + this.data + ", Nonce: " + this.nonce 
				+ ", prevHash: " + this.prevHash + ", hash: " + this.thisHash + ")");
		return toReturn;
	}
	public static void main(String[] args) throws NoSuchAlgorithmException {
		Hash testHash = makeHash(0, 300, null, 9324351);
		System.out.println(testHash.toString());
		Block testBlock = new Block(1, -150, testHash);
		System.out.println(testBlock.thisHash.toString());
	}
}//Block class

