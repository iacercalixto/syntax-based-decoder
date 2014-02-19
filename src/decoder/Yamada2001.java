/**
 * 
 */
package decoder;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * See "A decoder for syntax-based statistical MT", by Yamada and Knight (2001).
 * This class implements their model.
 * 
 * @author icalixto
 * 
 */
public class Yamada2001 {
	/**
	 * 
	 */
	DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> sourceTree = null;
	/**
	 * 
	 */
	DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> reorderedTree = null;
	/**
	 * 
	 */
	DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> insertedTree = null;
	
	/**
	 * Translates the source sentence described in the sourceTree parameter.
	 * @param sourceTree DefaultDirectedWeightedGraph a tree containing the source sentence parsing to be translated
	 * @return String the final translation
	 */
	public String translate(DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> sourceTree) {
		this.sourceTree = sourceTree;
		this.reorderedTree = this.reorderingModel(sourceTree);
		this.insertedTree = this.insertionModel(this.reorderedTree);
		return this.translationModel(this.insertedTree);
	}
	
	/**
	 * The first model to be applied on the input tree.
	 * @return DefaultDirectedWeightedGraph a reordered graph
	 */
	private DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> reorderingModel(
			DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> sourceTree) {
		DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> reorderedTree = null;
		return reorderedTree;
	}
	
	/**
	 * The second model to be applied on the reordered tree.
	 * @return DefaultDirectedWeightedGraph a "inserted" graph
	 */
	private DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> insertionModel(
			DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> reorderedTree) {
		DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> insertedTree = null;
		return insertedTree;
	}
	
	/**
	 * The third model to be applied on the input tree.
	 * @return String the final translation of the input sourceTree
	 */
	private String translationModel(DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> insertedTree) {
		String translation = null;
		return translation;
	}
}
