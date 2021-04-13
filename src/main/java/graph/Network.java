package graph;

import java.util.*;

public class Network {
	
	private Map<String, Node> nodeMap;
	private Set<Node> nodes;
	private ArrayList<Edge> edges;
	
	public Network() {
		this.nodeMap = new HashMap<String, Node>();
		this.nodes = new LinkedHashSet<Node>();
		this.edges = new ArrayList<Edge>();
	}
	
	public void addEdge(Edge a) {
		Node in;
		Node out;

		if (nodes.contains(a.getOut())) {
			out = (Node) nodeMap.get(a.getOut().getId());
		} else {
			nodes.add(a.getOut());
			out = a.getOut();
		}
		
		if (nodes.contains(a.getIn())) {
			in = (Node) nodeMap.get(a.getIn().getId());
		} else {
			nodes.add(a.getIn());
			in = a.getIn();
		}

		a.setIn(in);
		a.setOut(out);

		edges.add(a);
		nodeMap.put(in.getId(), in);
		nodeMap.put(out.getId(), out);

		//UNDIRECTED
		out.addNeighbour(in);
		in.addNeighbour(out);
		
		//DIRECTED
		out.addOutNeighbour(in);
		out.addOutEdge(a);
		in.addInNeighbour(out);
		in.addInEdge(a);
	}

	public Map<String, Node> getNodeMap() {
		return nodeMap;
	}

	public void setNodeMap(Map<String, Node> nodeMap) {
		this.nodeMap = nodeMap;
	}

	public Set<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
	
	public void setHealthy() {
		for (Node n: nodes) {
			n.setState(States.HEALTHY);
		}
	}
	
	public void deleteFv() {
		for (Node n: nodes) {
			n.setFv(0);
		}
	}
	
	public double getFvSum() {
		double sum = 0.0;
		for (Node n: nodes) {
			sum += n.getFv();
		}
		return sum;
	}

	public Node getNode(String n) {
		nodeMap = this.getNodeMap();
		Node node = nodeMap.get(n);
		return node;
	}

}
