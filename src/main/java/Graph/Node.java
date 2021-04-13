package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Node {
	private String id;
	private States state;
	private ArrayList<Edge> inList;
	private ArrayList<Edge> outList;
	private Set<Node> outNeighbour;
	private Set<Node> inNeighbour;
	private Set<Node> neighbour;
	private double fv;
	
	public Node(String id) {
		this.id = id;
		this.state = States.NOTVISITED;
		this.outList = new ArrayList<Edge>();
		this.inList = new ArrayList<Edge>();
		this.outNeighbour = new HashSet<Node>();
		this.inNeighbour = new HashSet<Node>();
		this.neighbour = new HashSet<Node>();
		this.fv = 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}

	public double getFv() {
		return fv;
	}

	public void setFv(double fv) {
		this.fv = fv;
	}

	public void addFv() {
		this.fv++;
	}
	
	public void finalizefv(int samplesize) {
		this.fv = this.fv / (double)samplesize;
		if (this.fv > 1.0) {
			System.out.println("SZAR fv: "+ this.fv);
		}
	}

	public ArrayList<Edge> getInlist() {
		return inList;
	}

	public void setInlist(ArrayList<Edge> inList) {
		this.inList = inList;
	}

	public void addInEdge(Edge e) {
		this.inList.add(e);
	}

	public ArrayList<Edge> getOutlist() {
		return outList;
	}

	public void setOutlist(ArrayList<Edge> outList) {
		this.outList = outList;
	}
	
	public void addOutEdge(Edge e) {
		this.outList.add(e);
	}

	public Set<Node> getNeighbour() {
		return neighbour;
	}

	public void setNeighbour(Set<Node> neighbour) {
		this.neighbour = neighbour;
	}

	public void addNeighbour(Node a) {
		this.neighbour.add(a);
	}

	public Set<Node> getInNeighbour() {
		return inNeighbour;
	}

	public void setInNeighbour(Set<Node> inNeighbour) {
		this.inNeighbour = inNeighbour;
	}
	public void addInNeighbour(Node a) {
		this.inNeighbour.add(a);
	}

	public Set<Node> getOutNeighbour() {
		return outNeighbour;
	}

	public void setOutNeighbour(Set<Node> outNeighbour) {
		this.outNeighbour = outNeighbour;
	}
	
	public void addOutNeighbour(Node a) {
		this.outNeighbour.add(a);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Node other = (Node) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", state=" + state + ", fv=" + fv + "]";
	}

}
