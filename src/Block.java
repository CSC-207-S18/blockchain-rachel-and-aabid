import java.util.*;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class Block {
	private int blockNum; 
	private int data;
	private Hash prevHash;
	private long nonce; 
	private Hash thisHash;
	
	public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
		this.blockNum = num;
		this.data = amount; 
		this.prevHash = prevHash;
		
		MessageDigest md = MessageDigest.getInstance("she-256");
		
		
	}
	public Block(int num, int amount, Hash prevHash, long nonce){}
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
	public static void main(String[] args){
		
	}
}//Block class
