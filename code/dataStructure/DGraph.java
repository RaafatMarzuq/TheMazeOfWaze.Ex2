package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.text.GapContent;


public class DGraph implements graph {
	public   int MC=0;
	public HashMap<Integer, HashMap<Integer,edge_data>> R = new HashMap<Integer, HashMap<Integer,edge_data>>();
	public HashMap<Integer,node_data> G= new HashMap<>();

	//////////// Constructor//////////////	
	public DGraph() {

		R =  new HashMap<>();
		G=new HashMap<>(); 
	}

	////////////////////////////////////////////////////
	@Override
	public node_data getNode(int key) {

		return (node_data) G.get(key);


	}
	@Override
	public edge_data getEdge(int src, int dest) {
		if(R.get(src).containsKey(dest)) {
			//			System.out.println( " your edge is: src("+G.get(src).getKey()
			//			+")--> Dest("+R.get(src).get(dest).getDest() +")" );

			return R.get(src).get(dest);
		}

		return null;
	}

	@Override
	public void addNode(node_data n) {
		this.G.put(n.getKey(), n);
		//System.out.println(" G.size = " +G.size());
		MC++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		edge  a= new edge(src,dest,w);
		NodeData  n2= (NodeData) G.get(dest);
		NodeData  n1= (NodeData) G.get(src);

		if(!R.containsKey(src)) 
		{
			HashMap<Integer, edge_data> hm= new HashMap<Integer,edge_data>();
			hm.put(dest, a);
			R.put(src,hm);
			n1.OutEdges.put(dest, (edge_data) a);
			n2.enteringEdges.put(src,(edge_data) a);
		}

		if(!R.get(src).containsKey(dest))
		{

			R.get(src).put(a.getDest(), a);
			n1.OutEdges.put(dest, (edge_data) a);
			n2.enteringEdges.put(src,(edge_data) a);
			MC++;
		}
		else if(R.get(src).containsKey(dest))
		{
			return;
			//	System.out.println("These two are already connected");
		}
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
		if(!R.containsKey(node_id)) {
			return null;
		}

		Collection<edge_data> s= (Collection<edge_data>)  (R.get(node_id).values());

		return   s;
	}


	@Override
	public void set_infinity() { // sets all the nodes weights to infinity
		int n = this.G.size();
		for(int i =0; i<n ; i++) {
			G.get(i).setWeight(Double.POSITIVE_INFINITY);
		}
	}
	public void setTags() { // sets all the nodes tag to zero 
		int n = this.G.size();
		for(int i =0; i<n ; i++) {
			G.get(i).setTag(0);
		}
	}
	public node_data removeNode(int key) 
	{
		if(G.containsKey(key) ) {
			if(R.get(key) !=null) {
		
			NodeData  n= (NodeData) getNode(key);
			Collection<edge_data> all= getE(n.getKey());
			if(all != null) {
				for(edge_data a : all) {
					NodeData s =(NodeData) G.get(a.getDest()) ;
					if(s.enteringEdges.containsKey(key)) {
						s.enteringEdges.remove(key);
					}
					if(s.OutEdges.containsKey(key)) {
						s.OutEdges.remove(key);
					}
					if(R.containsKey(key) || R.values().contains(key) ){
						R.remove(a);
						R.values().remove(a);
					}
				}
			}
			G.remove(key, n);

			MC++;
			return n;
		}
			NodeData  n= (NodeData) getNode(key);
			G.remove(key, n);

		}
		return null;
	}

	//		if(G.containsKey(key)) 
	//		{
	//			node_data  n = getNode(key);
	//			Collection<edge_data> all= getE(key);
	//			if(!all.isEmpty())
	//			{
	//				for(edge_data a : all)
	//				{
	//					removeEdge(key, a.getDest());
	//					//				edge_data k=i.next();
	//					//				NodeData s =(NodeData) G.get(k.getDest()) ;
	//					//				s.enteringEdges.remove(key);
	//					//				s.OutEdges.remove(key);
	//					//				R.remove(k);
	//				}
	//				G.remove(key, n);
	//
	//
	//				MC++;
	//				return n;
	//			}
	//		}
	//		return null;
	//}

	@Override
	public edge_data removeEdge(int src, int dest) {
		edge_data e=getEdge(src, dest);
		if(R.containsKey(src)) {
			NodeData n=	(NodeData) G.get(e.getSrc());
			NodeData n1=	(NodeData) G.get(e.getDest());
			//		n.enteringEdges.remove(e.getSrc());
			//		n1.OutEdges.remove(e.getDest());

			R.get(src).remove(dest);
			MC++;
			return e;
		}

		return null;
	}
	//		edge_data e=getEdge(src, dest);
	//		NodeData  n2= (NodeData) G.get(dest);
	//		NodeData  n1= (NodeData) G.get(src);
	//		if(R.containsKey(src))
	//		{
	////			NodeData  n2= (NodeData) G.get(dest);
	////			NodeData  n1= (NodeData) G.get(src);
	//			n1.OutEdges.remove(dest, e);
	//			n2.enteringEdges.remove(src,e);
	//			R.get(src).remove(dest, e);
	//			MC++;
	//
	//		   return e;
	//		}
	//		System.out.println("the edge is :" + R.get(src).get(dest).getDest());
	//
	//		return null;
	//	}

	@Override
	public int nodeSize() {
		return G.size();
	}

	@Override
	public int edgeSize() {
		int size=  R.values().size();
		return size;
	}

	@Override
	public int getMC() {

		return MC;
	}

}
