/**
 * 
 */
package test;

import parser.GraphParser;

/**
 * @author icalixto
 *
 */
public class GraphParserTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("First GraphParser creation...");
		GraphParser p = new GraphParser();
		p.parse();
		
		System.out.println("\nSecond GraphParser creation...");
		p = new GraphParser(true);
		p.parse();
		
		System.out.println("\nThird GraphParser creation...");
		p = new GraphParser(false);
		p.parse();
		
		System.out.println("\nFourth GraphParser creation...");
		p = GraphParser.debug();
		p.parse();
	}

}
