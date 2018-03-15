import java.util.List;


/**
 * 1.CDH 改成CDH
 * 2.方法参数标明。
 * 
 * 
 * @author lenovo
 *
 */
public class Judgement {

	
	/**
	 * 
	 * 判断subCDH是否为CDH的子依赖
	 * 
	 * @param subCDH需要被判断的子依赖
	 * @param cDH组件依赖图
	 * @return true表示subCDH为cDH的子依赖，false表示不是。
	 */
	public boolean isSubCDH(CDH subCDH, CDH cDH) {
		if(cDH.getVertexs().containsAll(subCDH.getDependencyRoots())) {
			for(Vertex vertex1 : subCDH.getDependencyRoots()) {
				for(Vertex vertex2 : cDH.getVertexs()) {
					if(vertex1.equals(vertex2)) {
						if(!hasSameVertexs(vertex1, vertex2)) {
							return false;
						}
					}
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * 判断两个节点的所有依赖节点是否相同
	 * 
	 * @param vertex1节点1
	 * @param vertex2节点2
	 * @return true表示是相同的节点,false表示不是相同的节点
	 */
	private boolean hasSameVertexs(Vertex vertex1, Vertex vertex2) {
		if(vertex1.getNextVertexs() == null && vertex2.getNextVertexs() == null)
			return true;
		else if(vertex1.getNextVertexs().size() == vertex2.getNextVertexs().size()) {
			for(int i = 0; i < vertex1.getNextVertexs().size(); i++) {
				if(vertex1.getNextVertexs().get(i) != vertex2.getNextVertexs().get(i))
					return false;
			}
			for(int j = 0; j < vertex1.getNextVertexs().size(); j++) {
				if(!hasSameVertexs(vertex1.getNextVertexs().get(j), vertex2.getNextVertexs().get(j)))
					return false;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * 判断是否为依赖根
	 * 
	 * @param vertex 节点
	 * @param cDH 组件依赖图
	 * @return true表示vertex为cDH的依赖根
	 */
	public static boolean isDependencyRoot(Vertex vertex, CDH cDH) {
		if(cDH.getVertexs().contains(vertex)) {
			for(Edge edge : cDH.getEdges()) {
				if(edge.getEnd().equals(vertex)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否为依赖叶
	 * 
	 * @param vertex 节点
	 * @param cDH 组件依赖图
	 * @return true表示vertex为CDH的依赖叶
	 */
	public boolean isDependencyLeaf(Vertex vertex, CDH cDH) {
		if(cDH.getVertexs().contains(vertex)) {
			for(Edge edge: cDH.getEdges()) {
				if(edge.getStart().equals(vertex)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * 判断是否为直接依赖
	 * @param vertex1 节点1
	 * @param vertex2 节点2
	 * @param cDH 组件依赖图
	 * @return true表示vertex1直接依赖于vertex2，false表示不是。
	 */
	public boolean isDirectDependency(Vertex vertex1, Vertex vertex2, CDH cDH) {
		if(vertex1.getNextVertexs() == null)
			return false;
		if(cDH.getVertexs().contains(vertex1) && cDH.getVertexs().contains(vertex2)) {
			for(Vertex vertex: vertex1.getNextVertexs()) {
				if(vertex.equals(vertex2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * 判断是否为间接依赖
	 * @param vertex1 节点1
	 * @param vertex2 节点2
	 * @param cDH 组件依赖图
	 * @return true表示vertex1间接依赖于vertex2，false表示不是。
	 */
	public boolean isInDirectDependency(Vertex vertex1, Vertex vertex2, CDH cDH) {
		if(vertex1.getNextVertexs() == null)
			return false;
		if(cDH.getVertexs().contains(vertex1) && cDH.getVertexs().contains(vertex2)) {
			for(Vertex vertex : vertex1.getNextVertexs()) {
				if(vertex.equals(vertex2)) {
					return false;
				}
			}
			
			for(Vertex vertex : vertex1.getNextVertexs()) {
				if(vertex.findDependVertex(vertex2))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * 判断vertexs是否为vertex2的依赖前项
	 * @param vertexs 节点集合
	 * @param vertex2  节点2
	 * @param cDH 组件依赖图
	 * @return true表示vertexs是vertex2的依赖前项，false表示不是。
	 */
	public boolean isPrevious(List<Vertex> vertexs, Vertex vertex2, CDH cDH) {
		for(Vertex vertex1 : vertexs) {
			if(!isDirectDependency(vertex1, vertex2 ,cDH)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * 判断vertexs是否为vertex2的依赖后项
	 * @param vertexs 节点集合
	 * @param vertex2  节点2
	 * @param cDH 组件依赖图
	 * @return true表示vertexs是vertex2的依赖后项，false表示不是。
	 */
	public boolean isAfter(List<Vertex> vertexs, Vertex vertex2, CDH cDH) {
		for(Vertex vertex1 : vertexs) {
			if(!isDirectDependency(vertex2, vertex1 ,cDH)) {
				return false;
			}
		}
		return true;
	}
}
