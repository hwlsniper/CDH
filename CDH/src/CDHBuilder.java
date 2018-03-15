import java.util.ArrayList;
import java.util.List;

public class CDHBuilder {
	
	public CDH buildCDH(int vertexNum, int[][] dependRelation) {
		List<Vertex> vertexs = new ArrayList<Vertex>();
		List<Edge> edges = new ArrayList<Edge>();
		for(int i = 0; i < vertexNum; i++) {
			Vertex vertex = new Vertex();
			vertexs.add(vertex);
		}
		for(int i = 0; i < dependRelation.length; i++) {
			Edge edge = new Edge();
			edge.setStart(vertexs.get(dependRelation[i][0]));
			edge.setEnd(vertexs.get(dependRelation[i][1]));
			vertexs.get(dependRelation[i][0]).getEdges().add(edge);
			vertexs.get(dependRelation[i][0]).getNextVertexs().add(edge.getEnd());
			edges.add(edge);
		}
		
		CDH cDH = new CDH(vertexs, edges);
		return cDH;
	}
	
//	public DAG buildSubDAG() {
//		
//	}
}
