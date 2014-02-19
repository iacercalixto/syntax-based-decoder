/**
 * 
 */
package parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.*;

import org.ini4j.Options;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * @author iacercalixto
 *
 */
public class GraphParser {
	/**
	 * 
	 */
	private DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> g = null;
	/**
	 * 
	 */
	private String desc = null;
	/**
	 * 
	 */
	private List<String> terminals = null;
	/**
	 * 
	 */
	private List<String> nonTerminals = null;
	/**
	 * 
	 */
	private String rootNonTerminal = null;
	/**
	 * 
	 */
	private List<String> rules = null;
	/**
	 * @var boolean If true, verbosely output messages to stdout.
	 */
	private boolean debug = false;
	
	/**
	 * Default constructor
	 */
	public GraphParser() {
		this.debug = false;
	}
	
	/**
	 * Constructor to be used for debugging
	 * @param debug Default false
	 */
	public GraphParser(boolean debug) {
		this.debug = debug;
	}
	
	/**
	 * Helper for constructor to be used for debugging
	 * @return a GraphParser object with the debug option enabled.
	 */
	public static GraphParser debug() {
		GraphParser gp = new GraphParser(true);
		return gp;
	}
	
	/**
	 * Parse default input INI file
	 * @return JGraphT object containing a graph for the default input file
	 */
	public DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> parse() {
		String defaultFileName = "yamada_1.in";
		return this.parse(defaultFileName);
	}
	
	/**
	 * Parse INI file given by parameter
	 * @param filename The input file name to open, which must be located inside the 'data' directory
	 * @return JGraphT object containing a graph for the input file
	 */
	public DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> parse(String filename) {
		// Create the graph
		g = new DefaultDirectedWeightedGraph<String, DefaultWeightedEdge>(
					DefaultWeightedEdge.class
				);
		
		File file = new File(System.getProperty("user.dir")+"/src/data/" + filename);
		try {
			this.loadIniFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Populate the graph
		this.populate();
		
		return g;
	}
	
	/**
	 * After parsing INI file, populate a graph with its contents
	 */
	private void populate() {
		String item;
		String from = null, to = null, fromTo = null;
		@SuppressWarnings("rawtypes")
		Iterator it;
		// Pattern to extract rules
		Pattern pattern = Pattern.compile("\\(\\s*(.+?)\\s*,\\s*(.+?)\\s*\\)");
		Matcher matcher = null;
		
		if (debug) System.out.println("Populating graph...");
		
		// Add non-terminal nodes
		it = nonTerminals.iterator();
		while (it.hasNext()) {
			item = (String) it.next();
			try {
				g.addVertex(item);
				if (debug) System.out.println("Created vertex "+item);
			} catch (Exception e) {
				System.err.println("Problem parsing non-terminal node "+item+"\n"+e.getMessage());
				System.exit(1);
			}
		}
		
		// Add terminal nodes
		it = terminals.iterator();
		while (it.hasNext()) {
			item = (String) it.next();
			try {
				g.addVertex(item);
				if (debug) System.out.println("Created vertex "+item);
			} catch (Exception e) {
				System.err.println("Problem parsing non-terminal node "+item+"\n"+e.getMessage());
				System.exit(1);
			}
		}
		
		// Add the rules
		it = rules.iterator();
		while (it.hasNext()) {
			item = (String) it.next();
			matcher = pattern.matcher(item);
			matcher.find();
			
			try {
				from = matcher.group(1);
				to = matcher.group(2);
				fromTo = "(" + from + " -> " + to + ")";
			} catch (Exception e) {
				System.err.println("Problem parsing rule "+item+"\n"+e.getMessage());
				System.exit(1);
			}
			
			if (matcher.matches()) {
		
				if (debug) System.out.println(matcher.group(1)+", "+matcher.group(2));
				try {
					g.addEdge(from, to);
					if (debug) System.out.println("Created edge "+fromTo);
				} catch (Exception e) {
					System.err.println("Problem parsing rule "+fromTo+"\n"+e.getMessage());
					System.exit(1);
				}
				
			} else {
				System.err.println("Problem parsing rule "+fromTo+". Could not match entry with regex.");
				System.exit(1);
			}
		}
		
		if (debug) System.out.println("Done!\n");
	}
	
	/**
	 * 
	 * @param file File object pointing to INI
	 * @throws IOException
	 */
	private void loadIniFile(File file) throws IOException
    {
        Options opt;
        opt = new Options(new FileReader(file));
        loadOptionsFromIni(opt);
    }
	
	/**
	 * 
	 * @param opt Options object containing parsed INI
	 */
	private void loadOptionsFromIni(Options opt)
	{
		this.desc = opt.get("problem_description");
		this.rootNonTerminal = opt.get("root_nonterminal");
		this.nonTerminals = opt.getAll("nonterminal");
		this.terminals = opt.getAll("terminal");
		this.rules = opt.getAll("production_rules");
		
        if (debug) {
        	System.out.println("Processing input file (INI)...");
        	System.out.println("description:" + desc);
        	System.out.println("root node:" + rootNonTerminal);
            
            Iterator<String> it = nonTerminals.iterator();
            while (it.hasNext()) {
            	System.out.println("non-terminal: " + it.next());
            }
            
            it = terminals.iterator();
            while (it.hasNext()) {
            	System.out.println("terminal: " + it.next());
            }
            
            it = rules.iterator();
            while (it.hasNext()) {
            	System.out.println("rule: " + it.next());
            }
            System.out.println("Done!\n");
        }
    }
}
