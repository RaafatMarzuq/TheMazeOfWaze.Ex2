package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.text.GapContent;


public class DGraph implements graph {
	public static  int MC=0;
	public HashMap<Integer, edge> R = new HashMap<>();
	public HashMap<Integer,node_data> G= new HashMap<>();
	
//////////// Constructor//////////////	
public DGraph() {

	R =  new HashMap<>();
	G=new HashMap<>(); 
}

////////////////////////////////////////////////////
	@Override
	public node_data getNode(int key) {
		return (node_data) this.G.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		if(R.containsKey(src)) {
		NodeData n= (NodeData) G.get(src);
		System.out.println( " your edge is: src("+n.OutEdges.get(dest).getSrc() 
													+")--> Dest("+n.OutEdges.get(dest).getDest() +")" );
				
		return n.OutEdges.get(dest);
		}
		
		return null;
	}

	@Override
	public void addNode(node_data n) {
		G.put(n.getKey(), n);
		MC++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		edge  a=new edge(src,dest,w);
		NodeData  n2= (NodeData) G.get(dest);
		NodeData  n1= (NodeData) G.get(src);
		R.put(src, a);
		n1.OutEdges.put(dest, (edge_data) a);
		n2.enteringEdges.put(src,(edge_data) a);
		MC++;
	}

	@Override
	public Collection<node_data> getV() {
		if(!G.isEmpty()) {
		Collection<node_data> c= (Collection<node_data>) G.values();
		return c;
		}
		System.out.println("The graph is Empty");
		return null;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		if(R.isEmpty()) {
		return null;
		}
		NodeData e=(NodeData) G.get(node_id);
		Collection<edge_data> s= (Collection<edge_data>)  (e.OutEdges.values() );
		
		return   s;
	}

	
	@Override
	public node_data removeNode(int key) {
		if(G.containsKey(key)) {
		node_data  n= getNode(key);
		Collection<edge_data> all= getE(key);
		G.remove(key, n);
		Iterator<edge_data> i= all.iterator();	
		for(edge_data a : all) {
			edge_data k=i.next();
			NodeData s =(NodeData) G.get(k.getDest()) ;
			s.enteringEdges.remove(key);
			s.OutEdges.remove(key);
			R.remove(k);
		}
		
		
		MC++;
		return n;
	}
		
		return null;
	}
		
	@Override
	public edge_data removeEdge(int src, int dest) {
		
		edge_data e=getEdge(src, dest);
		if(R.containsKey(src)) {
		NodeData n=	(NodeData) G.get(e.getSrc());
		NodeData n1=	(NodeData) G.get(e.getDest());
		n.enteringEdges.remove(e.getSrc());
		n1.OutEdges.remove(e.getDest());
		
		R.remove(e.getSrc());
		MC++;
		return e;
		}
		
		return null;
	}

	@Override
	public int nodeSize() {
		return G.size();
	}

	@Override
	public int edgeSize() {
		return R.size();
	}

	@Override
	public int getMC() {
		
		return MC;
	}

}
