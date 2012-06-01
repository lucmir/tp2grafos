
/**
 * Class Edge
 * 
 * Edge representation.
 */
public class Edge {
	
	Integer vertexA;
	Integer vertexB;
	
	
	public Edge(Integer vertexA, Integer vertexB) {
		super();
		this.vertexA = vertexA;
		this.vertexB = vertexB;
	}
	
	public Integer getVertexA() {
		return vertexA;
	}

	public void setVertexA(Integer vertexA) {
		this.vertexA = vertexA;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertexA == null) ? 0 : vertexA.hashCode());
		result = prime * result + ((vertexB == null) ? 0 : vertexB.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (vertexA == null) {
			if (other.vertexA != null)
				return false;
		} else if (!vertexA.equals(other.vertexA))
			return false;
		if (vertexB == null) {
			if (other.vertexB != null)
				return false;
		} else if (!vertexB.equals(other.vertexB))
			return false;
		return true;
	}

	public Integer getVertexB() {
		return vertexB;
	}

	public void setVertexB(Integer vertexB) {
		this.vertexB = vertexB;
	}

	@Override
	public String toString() {
		return "Edge [vertexA=" + vertexA + ", vertexB=" + vertexB + "]";
	}

}
