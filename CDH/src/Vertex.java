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
	 * ��ȡ�ýڵ�ֱ�������Ľڵ㼯��
	 * @return
	 */
	public List<Vertex> getNextVertexs(){
		return nextVertexs;
	}
	
	/**
	 * 
	 * �жϽڵ�v�Ƿ����ڸýڵ�֮���ҵ�
	 * @param v:Ҫ�ҵĽڵ�
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
