package gibben;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ScrambledWord {

	private int location;
	private static Scanner scan2;
	private static Scanner scan;

	private String Scramble(String txt) {
		int[] local;
		char[] temp;
		int range = 0;
		StringTokenizer Token = new StringTokenizer(txt, " ,.!?()-/&:;\"", true);
		String[] tokens = new String[Token.countTokens()];
		int l = 0;

		while (Token.hasMoreTokens()) {
			tokens[l] = Token.nextToken();
			l++;
		}

		for (int i = 0; i < tokens.length; i++) {
			System.out.println(tokens[i]);
		}

		System.out.println(tokens.length);
		String res = "";

		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].length() <= 3) {
				res += tokens[i];
				continue;
			}

			setLocation(-1);
			range = tokens[i].length();
			local = new int[range - 2];
			local[0] = 0;
			temp = new char[range];
			temp[0] = tokens[i].charAt(0);
			temp[range - 1] = tokens[i].charAt(range - 1);

			ArrayList<Character> Char = new ArrayList<Character>();
			for (int j = 1; j < range - 1; j++) {
				Char.add(tokens[i].charAt(j));
			}

			Collections.shuffle(Char);
			Character[] change = Char.toArray(new Character[0]);

			String z = new String();
			for (Character Single : change) {
				z += Single.toString();
			}
			String s = new String(temp[0] + z + temp[range - 1]);
			res += s;
		}
		return res;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public static void main(String[] args) throws FileNotFoundException {
		ScrambledWord word = new ScrambledWord();
		ScrambledWord word2 = new ScrambledWord();
		System.out
				.println("Do you want to enter your string manually through the console or do you want continue with the already generated text file:");
		System.out
				.println("Type '1' for the first option and '2' for the second option");
		scan = new Scanner(System.in);
		scan2 = new Scanner(System.in);
		int option = scan.nextInt();

		int first = 1;
		int second = 2;

		if (option == first) {
			System.out.println("Please enter your sentence/paragraph below");
			String userInput = scan2.nextLine();
			System.out.println(word.Scramble(userInput));
		} else if (option == second) {

			String text = "example.txt";
			try {
				FileReader inputFile = new FileReader(text);

				BufferedReader bufferReader = new BufferedReader(inputFile);

				String line = bufferReader.readLine();

				System.out.println("This is the text after being scrambled: ");
				System.out.println(word2.Scramble(line));

				bufferReader.close();
			} catch (Exception e) {
				System.out.println("Error whilst reading the file"
						+ e.getMessage());
			}
		}

	}

}
