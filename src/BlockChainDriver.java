import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class BlockChainDriver {

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

	public static void main(String[] args) throws NoSuchAlgorithmException {

		if (args.length != 1 || Integer.parseInt(args[0]) < 0) {
			System.out
					.println("invaid input. Required format: BlockChainDriver <non-negative int>");
			System.exit(0);
		}
		Scanner in = new Scanner(System.in);
		String input = new String();
		int initial = Integer.parseInt(args[0]);
		boolean quit = false;
		BlockChain chain = new BlockChain(initial);
		Block mined = null;
		int amountT = 0;

		System.out.println(chain.toString());
		while (!quit) {
			System.out.println(chain.toString());
			System.out.print("Command? ");
			input = in.next();
			input = input.toLowerCase();
			if(input.equals("help")){
				printPrompt();
			}
			else if(input.equals("mine")){
				System.out.print("Amount transferred? ");
				amountT = in.nextInt();
				mined = chain.mine(amountT);
				System.out.println("amount = " + amountT + ", nonce = " + mined.getNonce());
			}
			else if (input.equals("append")) {
				if (mined == null) {
					System.out.println("Unable to append. Please mine first.");
				} else {
				System.out.println("Amount transferred? " + amountT);
				chain.append(mined);
				System.out.println("nonce = " + mined.getNonce());
				mined = null;
				}
			}
			else if (input.equals("remove")) {
				chain.removeLast();
			}
			else if (input.equals("check")) {
				chain.isValidBlockChain();
			}
			else if (input.equals("report")) {
				chain.printBalances();
			}
			else if (input.equals("quit")) {
				quit = true;
			}
			else {
				System.out.println("invalid command. given: " + input + "!!!!!!!");
				printPrompt();
			}
		}

		in.close();
	}

}
