package haffman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HaffmanMain {

	public static void main(String[] args) throws IOException {

		System.out.println("Enter some word: ");
		System.out.flush();

		String text = getString();

		int[] charFrequencies = new int[256];// для простоты макс длина ограничена

		// cчитываем символы и частоту.
		for (char c : text.toCharArray()) {
			charFrequencies[c]++;
		}
		//
		HuffmanTree tree = HuffmanTree.buildHaffmanTree(charFrequencies);
		System.out.printf(" size before = %d%n", text.length() * 8);
		String incoded = tree.incode(text);
		System.out.println("incoded result " + incoded);
		System.out.printf("size after compression = %d%n", incoded.length());
		String decoded = tree.decode(incoded);
		System.out.println(decoded);

		tree.printCodes();
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}
