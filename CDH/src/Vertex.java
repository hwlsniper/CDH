import java.util.ArrayList;
import java.util.List;

public class Vertex {

	private List<Edge> edges = new ArrayList<Edge>();
	private List<Vertex> nextVertexs = new ArrayList<Vertex>();

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
	/**
	 * 获取该节点直接依赖的节点集合
	 * @return
	 */
	public List<Vertex> getNextVertexs(){
		return nextVertexs;
	}
	
	/**
	 * 
	 * 判断节点v是否能在该节点之后找到
	 * @param v:要找的节点
	 * @return
	 */
	public boolean findDependVertex(Vertex v) {
		if(this.getNextVertexs().contains(v))
			return true;
		for(Vertex vertex : this.getNextVertexs()) {
			return vertex.findDependVertex(v);
		}
		return false;
	}
}
