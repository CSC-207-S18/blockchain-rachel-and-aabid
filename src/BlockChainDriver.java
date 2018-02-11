import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
/**
 * Driver class for the BlockChain class
 * @author nastelin
 *
 */
public class BlockChainDriver {
	/**
	 * printPrompt, a function that explains valid commands to user
	 * 
	 * @param none
	 * @return none
	 */
	public static void printPrompt() {
		System.out.println("Valid commands:");
		System.out
		.printf("\tmine: discovers the nonce for a given transaction\n");
		System.out
		.printf("\tappend: appends a new block onto the end of the chain\n");
		System.out
		.printf("\tremove: removes the last block from the end of the chain\n");
		System.out.printf("\tcheck: checks that the block chain is valid\n");
		System.out.printf("\treport: reports the balances of Alice and Bob\n");
		System.out.printf("\thelp: prints this list of commands\n");
		System.out.printf("\tquit: quits the program\n");
	}

	/**
	 * main function
	 * 
	 * @param args	a String[]
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {

		if (args.length != 1 || Integer.parseInt(args[0]) < 0) {
			System.out
			.println("invaid input. Required format: BlockChainDriver <non-negative int>");
			System.exit(0);
		}
		Scanner in = new Scanner(System.in);
		String input = new String();
		int initial = Integer.parseInt(args[0]);
		BlockChain chain = new BlockChain(initial);
		Block mined = null;
		int amountT = 0;

		while (true) {
			// Prompt user
			System.out.println(chain.toString());
			System.out.print("Command? ");
			input = in.next();
			input = input.toLowerCase();

			// Evaluate user input
			if (input.equals("help")) {
				printPrompt();
			} else if (input.equals("quit")) {
				in.close();
				return;
			} else if (input.equals("mine")) {
				System.out.print("Amount transferred? ");
				amountT = in.nextInt();
				mined = chain.mine(amountT);
				System.out.println("amount = " + amountT + ", nonce = "
						+ mined.getNonce());
			} else if (input.equals("append")) {
				if (mined == null) {
					System.out.println("Unable to append. Please mine first.");
				} else {
					System.out.print("Amount transferred? ");
					int newAmount = Integer.parseInt(in.next());
					while (newAmount != mined.getAmount()) {
						System.out.print("Invalid amount. Please enter "
								+ mined.getAmount() + "\nAmount transferred? ");
						newAmount = Integer.parseInt(in.next());
					}// while
					chain.append(mined);
					System.out.print("Nonce? ");
					long newNonce = Integer.parseInt(in.next());
					while (newNonce != mined.getNonce()) {
						System.out.print("Invalid nonce. Please enter "
								+ mined.getNonce() + "\nNonce? ");
						newNonce = Integer.parseInt(in.next());
					}// while
					mined = null;
				}// else
			} else if (input.equals("remove")) {
				chain.removeLast();
			} else if (input.equals("check")) {
				if(chain.isValidBlockChain()){
					System.out.println("Chain is valid!");
				}
				else{
					System.out.println("Chain is invalid!");
				}
			} else if (input.equals("report")) {
				chain.printBalances();
			} else {
				System.out.println("invalid command. given: " + input);
				printPrompt();
			}// else
		}// while 
	}// main
}// BlockChainDriver class
