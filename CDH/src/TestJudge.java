import java.util.ArrayList;
import java.util.List;

public class TestJudge {

	public static void main(String[] args) {

		CDHBuilder builder = new CDHBuilder();
		int[][] dependRelation = {{0,1}, {1,2}, {2,3}, {1,4}, {0,5}, {5,6},{5,7},{8,9},{8,10}};
		
		CDH cDH = builder.buildCDH(11, dependRelation);
		List<Vertex> vertexs = cDH.getVertexs();
		List<Edge> edges = cDH.getEdges();
		
		List<Vertex> testVertexs = new ArrayList<Vertex>();
		List<Edge> testEdges = new ArrayList<Edge>();
		
		
		testVertexs.add(vertexs.get(1));
		testVertexs.add(vertexs.get(2));
		testVertexs.add(vertexs.get(3));
		testVertexs.add(vertexs.get(4));
		testEdges.add(edges.get(1));
		testEdges.add(edges.get(2));
		testEdges.add(edges.get(3));
		CDH subCDH = new CDH(testVertexs, testEdges);
//		System.out.println(cDH.getVertexs().size()+"..."+cDH.getEdges().size());
//		System.out.println(subCDH.getVertexs().size()+"..."+subCDH.getEdges().size());
//		System.out.println(cDH.getVertexs().get(1).getNextVertexs().size());
		
		Judgement judgement = new Judgement();
		boolean b = Judgement.isDependencyRoot(cDH.getVertexs().get(8), cDH);
		System.out.println("是否为依赖根："+b);
		
		b = judgement.isSubCDH(subCDH, cDH);
		System.out.println("是否为子依赖："+b);
		
		b = judgement.isDependencyLeaf(cDH.getVertexs().get(8), cDH);
		System.out.println("是否为依赖叶："+b);
		
		b = judgement.isDirectDependency(cDH.getVertexs().get(8), cDH.getVertexs().get(9), cDH);
		System.out.println("是否为直接依赖："+b);
		
		b = judgement.isInDirectDependency(cDH.getVertexs().get(1), cDH.getVertexs().get(7), cDH);
		System.out.println("是否为间接依赖："+b);
		
		b = judgement.isPrevious(testVertexs, cDH.getVertexs().get(1), cDH);
		System.out.println("是否为前项依赖："+b);
		
		b = judgement.isAfter(testVertexs, cDH.getVertexs().get(1), cDH);
		System.out.println("是否为后项依赖："+b);
	}

}
