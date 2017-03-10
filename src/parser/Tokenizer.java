package parser;

public class Tokenizer {

	private String[] tokens;
	private int index;
	
	public Tokenizer(String expression, String delimeter) {
		tokens = expression.split(delimeter);
		index = 0;
	}
	
	public boolean hasNextToken() {
		return index < tokens.length;
	}
	
	public String nextToken() {
		if (!hasNextToken()) {
			throw new ArrayIndexOutOfBoundsException("Reached end of expression");
		}
		return tokens[index++];
	}
}
