/**
 * 
 */
package parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.ini4j.Options;

/**
 * @author iacercalixto
 *
 */
public class GraphParser {
	public static void main(String[] args) {
		File file = new File("/home/iacercalixto/workspace/YamadaSyntaxBasedDecoder/src/data/yamada_1.in");
		try {
			GraphParser.loadIniFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static void loadIniFile(File file) throws IOException
    {
        Options opt;
        opt = new Options(new FileReader(file));
        loadOptionsFromIni(opt);
    }
	
	static void loadOptionsFromIni(Options opt)
    {
        Set<String> optionNames = opt.keySet();
        
        String desc = opt.get("problem_description");
        String root = opt.get("root_nonterminal");
        
        List<String> nterms = opt.getAll("nonterminal");
        List<String> terms = opt.getAll("terminal");
        List<String> rules = opt.getAll("production_rules");
        
        System.out.println("description:" + desc + "\n");
        System.out.println("root node:" + root + "\n");
        
        Iterator<String> it = nterms.iterator();
        while (it.hasNext()) {
        	System.out.println("non-terminal: " + it.next() + "\n");
        }
        
        it = terms.iterator();
        while (it.hasNext()) {
        	System.out.println("terminal: " + it.next() + "\n");
        }
        
        it = rules.iterator();
        while (it.hasNext()) {
        	System.out.println("rule: " + it.next() + "\n");
        }
    }
}
