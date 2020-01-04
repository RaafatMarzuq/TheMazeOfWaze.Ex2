package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;

import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.edge;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import gui.graphGUI;
import sun.misc.Queue;
import utils.Point3D;
import utils.StdDraw;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms,Serializable{

	
	public graph g;

	public Graph_Algo() {
		this.g=g;
	}
	
	public graph getG() {
		return g;
	}

	public void setG(graph g) {
		this.g = g;
	}

	public Graph_Algo(graph _graph) {
		this.g=_graph;
		
	}

	
	@Override
	public void init(graph g) {
		DGraph newG=(DGraph) g;
		this.g= newG;

	}

	@Override
	public void init(String file_name) {



		try
		{    
			FileInputStream file = new FileInputStream(file_name); 
			ObjectInputStream in = new ObjectInputStream(file); 

			g = (DGraph) in.readObject(); 

			in.close(); 
			file.close(); 

			System.out.println("init successfully"); 
		} 

		catch(IOException e) 
		{ 
			System.err.println("there has been a problem");
		} 

		catch(ClassNotFoundException e) 
		{ 
			System.out.println("ClassNotFoundException is caught"); 
		}
	}


	public void set_infinity() { // sets all the nodes weights to infinity
		int n = this.g.nodeSize();
		for(node_data nodes: g.getV()) {
			nodes.setWeight(Double.POSITIVE_INFINITY);
		}
	}
	public void setTags() { // sets all the nodes tag to zero 
		int n = g.nodeSize();
		for (node_data node: g.getV()) {
			node.setTag(0);
		}

	}


	@Override
	public void save(String file_name) {
		StdDraw.save(file_name);
	}


	@Override
	public boolean isConnected() {
		setTags();
		int n1= g.nodeSize();
		int n2=g.edgeSize();
		if(n1 ==1 || n1==0) { return true;}
		if((n1-1) > n2) { return false;}
		ArrayBlockingQueue<NodeData> S=new ArrayBlockingQueue<NodeData>(n1);
		List<NodeData> ListN= new ArrayList<NodeData>();

		for (node_data node : g.getV() ) {
			NodeData n=   (NodeData) node;
			if (n.OutEdges.values()== null) return false;
			S.add(n);
			n.setTag(1);
			while (!S.isEmpty()) {
				Collection<edge_data> Out=S.peek().OutEdges.values();
				for (edge_data edge :Out ) {
					NodeData dest=(NodeData) g.getNode(edge.getDest());
					if(dest.getTag()==0) {
						dest.setTag(1);
						ListN.add(dest);
						S.add(dest);
					}
				}
				S.remove();
			} 
			Collection<node_data> V=g.getV();
			for (node_data nodes : V) {
				if (nodes.getTag()==0) return false;
				else nodes.setTag(0);
			}
		}
		return true;
	}



	@Override
	public double shortestPathDist(int src, int dest) {
		deleteInfo();
		set_infinity();

		if(src==dest) {return 0;}
		g.getNode(src).setWeight(0);

		recusiveSP(src, dest,"");
		double length =g.getNode(dest).getWeight();	
		return length;
	}

	private void deleteInfo() {
		for(node_data node : g.getV()) {
			node.setInfo("");
		}

	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		int n=g.nodeSize();
		set_infinity();
		setTags();
		deleteInfo();
		if(src == dest) {return null;}
		if(	shortestPathDist( src,  dest) == Double.POSITIVE_INFINITY) 
		{
			return null;
		}
		set_infinity();
		setTags();
		deleteInfo();
		g.getNode(src).setWeight(0);
		recusiveSP(src, dest, "");
		node_data dest1= g.getNode(dest);
		dest1.setInfo(dest1.getInfo() );
		if(dest1.getInfo() != null && dest1.getWeight() != Double.POSITIVE_INFINITY) {
			String s = dest1.getInfo();
			String[] split=s.split("->"); 
			List<node_data> ans =new ArrayList<node_data>();
			for (int i = 0; i < split.length;i++) {
				if(!split[i].equals("")) {
					Integer  result =new Integer(Integer.valueOf(split[i])) ;			
					ans.add(g.getNode(result));	
				}

			}
			if(ans != null  ) {
				return ans;
			}
		}
		return null;

	}
	private void recusiveSP(int src, int dest, String info) {
		double  d;
		double oldD;
		if(g.getNode(src).getTag() == 1 ) {
			if( g.getNode(src) == g.getNode(dest)) {
				return;
			}
		}
		for (edge_data edge : g.getE(src)) {

			d = edge.getWeight() + g.getNode(edge.getSrc()).getWeight();
			oldD = g.getNode(edge.getDest()).getWeight();
			if(d < oldD) {
				g.getNode(edge.getDest()).setWeight(d);
				g.getNode(edge.getDest()).setInfo(info + "->" + src);

				g.getNode(edge.getSrc()).setTag(1);

				recusiveSP(edge.getDest(), dest, info + "->" + src);
			}	
		}
	}



	@Override
	public List<node_data> TSP(List<Integer> targets) {
		setTags();
		set_infinity();
		deleteInfo();
		int n=targets.size();
		ArrayBlockingQueue<NodeData> nodes = new ArrayBlockingQueue<NodeData>(n);
		if(n == 0) { return null;}


		if(!checkTargets(targets)) {return null;}
		List<node_data> list1=new ArrayList<node_data>();
		for(Integer i :targets) {
			list1.add(g.getNode(i));
		}
		if(n == 1) {return list1;}
		for (node_data node : list1) {
			NodeData no=(NodeData) node;
			if (no.OutEdges.values()== null) { return null;}
			nodes.add(no);
			no.setTag(1);
			while (!nodes.isEmpty()) {
				for (edge_data edge : nodes.peek().OutEdges.values()) {
					NodeData dest=(NodeData) g.getNode(edge.getDest());
					if(dest.getTag()==0) {
						dest.setTag(1);
						nodes.add(dest);
					}
				}
				nodes.remove();
			} 
			for (node_data cala : list1) {
				if (cala.getTag()==0) return null;

			}
		}
		setTags();
		List<node_data> ans = new ArrayList<>();
		int src, dest;
		while(list1.size()>1) {
			src =list1.get(0).getKey();
			dest = list1.get(1).getKey();
			List<node_data> list = shortestPath(src, dest);

			for(node_data ver : list) {
				if(list1.contains(ver)&& list1.size()>1) {
					if(list1.size()!=1) {					
						list1.remove(ver);					
					}
				}
		
				ans.add(ver);
			}
		}
		if(!ans.contains(list1.get(list1.size()-1))) {
			ans.add(list1.get(list1.size()-1));
		}
		{
			return ans != null? null : null;
		}

	}

	// returns false if the targets nodes aren't connected
	private boolean checkTargets(List<Integer> targets) {

		setTags();
		int n1= targets.size();
		int n2=0;
		List<node_data> list1=new ArrayList<>();
		for(Integer i :targets) {
			list1.add(g.getNode(i));
			for(edge_data edge:g.getE(i)) {
				if(targets.contains(edge.getDest())) {
					n2++;
				}
			}
		}

		if(n1 ==1 || n1==0) { return true;}
		if((n1-1) > n2) { return false;}
		ArrayBlockingQueue<NodeData> S=new ArrayBlockingQueue<NodeData>(n1);
		List<NodeData> ListN= new ArrayList<NodeData>();

		for (node_data node :list1 ) {
			NodeData n=   (NodeData) node;
			if (n.OutEdges.values()== null) return false;
			S.add(n);
			n.setTag(1);
			while (!S.isEmpty()) {
				Collection<edge_data> Out=S.peek().OutEdges.values();

				for (edge_data edge :Out ) {
					if(list1.contains(g.getNode(edge.getDest()))) {
						NodeData dest=(NodeData) g.getNode(edge.getDest());
						if(dest.getTag()==0) {
							dest.setTag(1);
							ListN.add(dest);
							S.add(dest);
						}
					}
				}
				S.remove();
			} 
			Collection<node_data> V=list1;
			for (node_data nodes : V) {
				if (nodes.getTag()==0) return false;
				else nodes.setTag(0);
			}
		}
		return true;
	}



	@Override
	public graph copy() {
		Graph_Algo copy= new Graph_Algo(this.g);
		return copy.g;
	}

}
