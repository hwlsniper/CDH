import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CDH {

	private List<Vertex> vertexs;
	private List<Edge> edges;
	private List<Vertex> DependencyRoots;
	
	/**
	 * @return the dependencyRoots
	 */
	public List<Vertex> getDependencyRoots() {
		this.DependencyRoots = new ArrayList<Vertex>();
		
		Iterator<Vertex> iterator = vertexs.iterator();
		while(iterator.hasNext()) {
			Vertex v = (Vertex)iterator.next();
			if(Judgement.isDependencyRoot(v, this)) {
				DependencyRoots.add(v);
			}
		}
		return DependencyRoots;
	}
	
	CDH(List<Vertex> vertexs, List<Edge> edges) {
		this.vertexs = vertexs;
		this.edges = edges;
	}
	public List<Vertex> getVertexs() {
		return vertexs;
	}
	public void setVertexs(List<Vertex> vertexs) {
		this.vertexs = vertexs;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
}
