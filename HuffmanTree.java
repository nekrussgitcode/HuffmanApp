//Алгоритм:
//	У нас есть слово. Мы  создаем отдельный узел, содержащий частоту появления символа в строке, для каждого
//	отдельного символа.
//	затем мы создаем объекты деревьев для каждого из этих узлов и каждый узел является корнем древа.
//	мы вставляем эти деревья в приоритетную очередь
//	затем, извлекая, мы сопоставляем деревья с наименьшим индексом, и объединяем их в новое древо, передавая
//	ему индексом суму прежних деревьев. И вставляем новое древо обратно в очередь. и, таким образом, уменьшаем
//	число деревьев, пока не останется только одно, и являющееся деревом ХАффмана.

package haffman;

import java.util.PriorityQueue;

public class HuffmanTree implements Comparable<HuffmanTree> {

	private Node root;

	public HuffmanTree(Node root) {

		this.root = root;

	}

	private static class Node {

		private Integer frequency;

		private Character character;

		private Node leftChild;

		private Node rightChild;

		public Node(Integer frequency, Character character) {

			this.frequency = frequency;
			this.character = character;

		}

		public Node(HuffmanTree left, HuffmanTree right) {

			frequency = left.root.frequency + right.root.frequency;
			leftChild = left.root;
			rightChild = right.root;

		}

	}
// 	строим дерево хаффмана 
//	Массив содержащий частоту символов в сообщении. Номер ячейки соответствует 
// 	* коду символа в ASCII
	public static HuffmanTree buildHaffmanTree(int[] charFrequencies) {

		PriorityQueue<HuffmanTree> trees = new PriorityQueue<>();

		for (int i = 0; i < charFrequencies.length; i++) {
			if (charFrequencies[i] > 0) {
				trees.offer(new HuffmanTree(new Node(charFrequencies[i], (char) i)));
			}
		}

		while (trees.size() > 1) {

			HuffmanTree a = trees.poll();// извлечение
			HuffmanTree b = trees.poll();
			trees.offer(new HuffmanTree(new Node(a, b))); // совмещает два узла
															// в новый единый
															// узел нового
															// дерева
		}
		// соединяет все образующиеся деревья в одно, которое, в итоге и
		// является деревом хаффмана
		return trees.poll();

	}

	///////////////////

	public String decode(String bytes) { // декодинг возвращает декод строку
		StringBuilder result = new StringBuilder();
		return result.toString();
	}

	public String incode(String text) {

		String[] codes = codeTable();

		StringBuilder result = new StringBuilder();

		// присоединение кодов хаффмана к кодированному сообщению, пока оно не
		// будет завершено

		for (int i = 0; i < text.length(); i++) {
			result.append(codes[text.charAt(i)]);
		}

		return result.toString();

	}

	///////////////

	private String[] codeTable() {
		String[] codeTable = new String[256];
		codeTable(root, new StringBuilder(), codeTable);
		return codeTable;
	}

	private void codeTable(Node node, StringBuilder code, String[] codeTable) {

		if (node.character != null) {
			codeTable[(char) node.character] = code.toString();
			return;
		}
		codeTable(node.leftChild, code.append('0'), codeTable);
		code.deleteCharAt(code.length() - 1);
		codeTable(node.rightChild, code.append('1'), codeTable);
		code.deleteCharAt(code.length() - 1);

	}

	public void printCodes() {
		System.out.println("char\t frequency\t binary code");
		printCodes(root, new StringBuilder());
	}

	private void printCodes(Node current, StringBuilder code) {
		// если не листовой узел
		if (current.character != null) {
			System.out.println(current.character + "\t" + current.frequency + "\t\t" + code);

		} else {
			// левое поддерево
			printCodes(current.leftChild, code.append(0));
			code.deleteCharAt(code.length() - 1);
			// правое
			printCodes(current.rightChild, code.append(1));
			code.deleteCharAt(code.length() - 1);
		}
	}

	public int compareTo(HuffmanTree tree) {

		return root.frequency - tree.root.frequency;
	}

}
