import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Solution {

	public static void main(String[] args) throws IOException {

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(Solution.class.getResourceAsStream("input.txt")))) {
			for (String line; (line = reader.readLine()) != null;) {
				final List<Integer> data = Arrays.asList(line.split(" ")).stream().map(Integer::parseInt)
						.collect(Collectors.toList());

				// Read data
				Stack<Integer> dataToRead = new Stack<Integer>();
				List<Integer> reverseData = new ArrayList<>(data);
				Collections.reverse(reverseData);
				dataToRead.addAll(reverseData);
				Node root = read("", dataToRead);
				if (!dataToRead.isEmpty()) {
					throw new IllegalStateException("Stack must be empty after reading.");
				}
				
				// Part 1
				System.out.print("Sum of all metadata entries: " + root.sumMetadata());
				
				
			}
		}
	}

	private static Node read(String indent, Stack<Integer> data) {
		
//		System.out.println(indent + "=======================");
//		System.out.println(indent + "Stack: " + data);

		int quantityOfChildNodes = data.pop();
		int quantityOfMetadataEntries = data.pop();
//		System.out.println(indent + "Quantity of child nodes: " + quantityOfChildNodes);
//		System.out.println(indent + "Quantity of metadata entries: " + quantityOfMetadataEntries);

		List<Node> childNodes = new ArrayList<>(quantityOfChildNodes);
		for (int index = 0; index < quantityOfChildNodes; index++) {
			childNodes.add(read(indent + "\t", data));
		}
		List<Integer> metadataEntries = new ArrayList<>();
		for (int index = 0; index < quantityOfMetadataEntries; index++) {
			metadataEntries.add(data.pop());
		}
//		System.out.println(indent + "Metadata: " + quantityOfChildNodes);
		return new Node(childNodes, metadataEntries);
	}
}