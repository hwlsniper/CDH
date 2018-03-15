import java.util.List;


/**
 * 1.CDH �ĳ�CDH
 * 2.��������������
 * 
 * 
 * @author lenovo
 *
 */
public class Judgement {

	
	/**
	 * 
	 * �ж�subCDH�Ƿ�ΪCDH��������
	 * 
	 * @param subCDH��Ҫ���жϵ�������
	 * @param cDH�������ͼ
	 * @return true��ʾsubCDHΪcDH����������false��ʾ���ǡ�
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
	 * �ж������ڵ�����������ڵ��Ƿ���ͬ
	 * 
	 * @param vertex1�ڵ�1
	 * @param vertex2�ڵ�2
	 * @return true��ʾ����ͬ�Ľڵ�,false��ʾ������ͬ�Ľڵ�
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
	 * �ж��Ƿ�Ϊ������
	 * 
	 * @param vertex �ڵ�
	 * @param cDH �������ͼ
	 * @return true��ʾvertexΪcDH��������
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
	 * �ж��Ƿ�Ϊ����Ҷ
	 * 
	 * @param vertex �ڵ�
	 * @param cDH �������ͼ
	 * @return true��ʾvertexΪCDH������Ҷ
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
	 * �ж��Ƿ�Ϊֱ������
	 * @param vertex1 �ڵ�1
	 * @param vertex2 �ڵ�2
	 * @param cDH �������ͼ
	 * @return true��ʾvertex1ֱ��������vertex2��false��ʾ���ǡ�
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
	 * �ж��Ƿ�Ϊ�������
	 * @param vertex1 �ڵ�1
	 * @param vertex2 �ڵ�2
	 * @param cDH �������ͼ
	 * @return true��ʾvertex1���������vertex2��false��ʾ���ǡ�
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
	 * �ж�vertexs�Ƿ�Ϊvertex2������ǰ��
	 * @param vertexs �ڵ㼯��
	 * @param vertex2  �ڵ�2
	 * @param cDH �������ͼ
	 * @return true��ʾvertexs��vertex2������ǰ�false��ʾ���ǡ�
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
	 * �ж�vertexs�Ƿ�Ϊvertex2����������
	 * @param vertexs �ڵ㼯��
	 * @param vertex2  �ڵ�2
	 * @param cDH �������ͼ
	 * @return true��ʾvertexs��vertex2���������false��ʾ���ǡ�
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
