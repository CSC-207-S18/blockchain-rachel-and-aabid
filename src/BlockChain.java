import java.security.NoSuchAlgorithmException; //For BlockChain constructor
import java.util.Random; //For randomly generated nonce in main
import java.lang.Math; //For randomly generated nonce in main

/**
 * BlockChain class. BlockChains are lists of Block objects.
 * 
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
	 * BlockChain constructor
	 * 
	 * @param initial	an int
	 * @return none
	 * @throws NoSuchAlgorithmException
	 */
	public BlockChain(int initial) throws NoSuchAlgorithmException {
		this.first = new Node();
		this.last = this.first;
		long nonce = 0;

		this.first.current = new Block(0, initial, null, nonce);
		this.last.current = this.first.current;
	}// Blockchain

	/**
	 * mines a new candidate block to be added to the list. The returned Block
	 * should be valid to add onto this list.
	 * 
	 * @param amount		an int
	 * @return newBlock		a Block
	 * @throws NoSuchAlgorithmException
	 */
	Block mine(int amount) throws NoSuchAlgorithmException {
		Block newBlock = new Block(this.last.current.getNum() + 1, amount,
				this.last.current.getHash());
		return newBlock;
	}// mine

	/**
	 * getSize, a getter for the last block's blockNum
	 * 
	 * @param none
	 * @return size		an int
	 */
	int getSize() {
		return (this.last.current.getNum() + 1);
	}// getSize

	/**
	 * append, a method that appends blk to the end of the given BlockChain
	 * 
	 * @param blk	a Block
	 * @return none
	 */
	void append(Block blk) {
		Node newNode = new Node();
		newNode.current = blk;
		newNode.next = null;
		this.last.next = newNode;
		//Hash lastHash = this.last.current.getHash();
		this.last = newNode;
		//this.last.current.setPrevHash(lastHash);
	}// append

	/**
	 * removeLast, a method that removes the last node from the given BlockChain
	 * 
	 * @param none
	 * @return true		if a node was successfully removed
	 * @return false	if a node was not successfully removed
	 */
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
		}// else
	}// removeLast

	/**
	 * getHash, a getter for a BlockChain's last hash
	 * 
	 * @param none
	 * @return the BlockChain's last hash
	 */
	Hash getHash() {
		return this.last.current.getHash();
	}// getHash

	/**
	 * isValidBlockChain, a method that determines whether or not a BlockChain is valid
	 * 
	 * @return true		when the BlockChain is valid
	 * @return false	when the BlockChain is invalid
	 */
	boolean isValidBlockChain() {
		Node newNode = this.first;
		Node newNode2 = this.first.next;

		int sum = 0;
		Node curr = this.first;
		while (curr != null) {
			sum += curr.current.getAmount();
			curr = curr.next;
			if (sum < 0) {
				return false;
			}
		}// while
		if ((newNode.current.getNum() != 0) || (sum < 0)
				|| (newNode.current.getNonce() < 0)
				|| (!newNode.current.getHash().isValid())) {
			return false;
		}// if

		while (newNode2 != null) {
			if ((newNode2.current.getNum() != newNode.current.getNum() + 1)
					|| (!newNode2.current.getPrevHash().isValid())
					|| (newNode2.current.getPrevHash() != newNode.current.getHash()) 
					|| (newNode2.current.getNonce() < 0)
					|| (!newNode2.current.getHash().isValid())) {
				return false;
			}// if
			newNode = newNode2;
			newNode2 = newNode2.next;
		}// while
		return true;
	}// isValidBlockChain

	/**
	 *printBalances, a method that prints a description of Alice and Bob's current balances
	 *
	 *@param none
	 *@return none
	 */
	void printBalances() {
		int alice = 0;
		int bob = this.first.current.getAmount();
		Node curr = this.first;
		while (curr != null) {
			alice += curr.current.getAmount();
			bob -= curr.current.getAmount();
			curr = curr.next;
		}// while
		System.out.println("Alice: " + alice + " Bob: " + bob);
	}// printBalances

	/**
	 * toString, a method that creates a string which describes the given BlockChain
	 * 
	 * @param none
	 * @return ret	a String
	 */
	public String toString() {
		StringBuffer blockString = new StringBuffer("");
		Node curr = this.first;
		while (curr != null) {
			blockString.append(curr.current.toString());
			curr = curr.next;
			blockString.append("\n");
		}// while
		String ret = blockString.toString();
		return ret;
	}// toString
}// BlockChain class
