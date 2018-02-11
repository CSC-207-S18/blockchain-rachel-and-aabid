import java.security.NoSuchAlgorithmException; //For BlockChain constructor
import java.util.Random; //For randomly generated nonce in main
import java.lang.Math; //For randomly generated nonce in main
/**
 * 
 */

/**
 * @author nastelin
 * 
 */
public class BlockChain {
	Node first;
	Node last;
	
	public class Node {
		Block current;
		Node next;

		public Node() {
			this.current = null;
			this.next = null;
		}
	}// Node class

	/**
	 * creates a BlockChain that possess a single block that starts with the
	 * given initial amount.
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public BlockChain(int initial) throws NoSuchAlgorithmException {
		this.first = new Node();
		this.last = this.first;
		Random rand = new Random();
		long nonce = rand.nextLong();
		nonce = Math.abs(nonce);
		this.first.current = new Block(0, initial, null, 0);
		this.last.current = this.first.current;
	}// Blockchain

	/**
	 * mines a new candidate block to be added to the list. The returned Block
	 * should be valid to add onto this list.
	 * 
	 * @param amount
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	Block mine(int amount) throws NoSuchAlgorithmException {
		Block newBlock = new Block(this.last.current.getNum() + 1, amount,
				this.last.current.getPrevHash());
		return newBlock;
	}// mine

	/**
	 * 
	 * @return
	 */
	int getSize() {
		return (this.last.current.getNum() + 1);
	}// getSize

	/**
	 * 
	 */
	void append(Block blk) {
		Node newNode = new Node();
		newNode.current = blk;
		newNode.next = null;
		this.last.next = newNode;
		this.last = newNode;
	}// append

	boolean removeLast() {
		if (this.first.next == null) {
			return false;
		} else {
			Node curr = this.first;
			while (curr.next.next != null) {
				curr = curr.next;
			}
			curr.next = null;
			this.last = curr;
			return true;
		}
	}

	Hash getHash() {
		return this.last.current.getHash();
	}

	boolean isValidBlockChain(){
		Node newNode = this.first;
		Node newNode2 = this.first.next;

		if (newNode.current.getNum() != 0 ||
				newNode.current.getAmount() < 0 ||
				!newNode.current.getPrevHash().isValid() ||
				newNode.current.getNonce() < 0 ||
				!newNode.current.getHash().isValid()) {
			return false;
		}

		while (newNode2 != null){
			if (newNode2.current.getNum() != newNode.current.getNum() + 1 ||
					newNode2.current.getAmount() < 0 ||
					!newNode2.current.getPrevHash().isValid() ||
					newNode2.current.getPrevHash() != newNode.current.getHash() ||
					newNode2.current.getNonce() < 0 ||
					!newNode2.current.getHash().isValid()) {
				return false;
			}
			newNode = newNode2;
			newNode2 = newNode2.next;
		}
		return true;
	}
	
	void printBalances(){
		int alice = this.last.current.getAmount();
		int bob = this.first.current.getAmount() - this.last.current.getAmount();
		System.out.println("Alice: " + alice + " Bob: " + bob);
	}
	
	public String toString(){
		StringBuffer blockString = new StringBuffer("");
		Node curr = this.first;
		//boolean hasLooped = false; 
		
		while(curr != null) {
			//hasLooped = true;
			blockString.append(curr.current.toString());
			curr = curr.next;
		}
		/*
		if(!hasLooped && curr != null){
			blockString.append(curr.current.toString());
		}
		*/
		String ret = blockString.toString();
		return ret;
	}


}// BlockChain class
