package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import gui.graphGUI;
import sun.misc.Queue;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{

	public DGraph g= new DGraph();

	public Graph_Algo() {
		this.g= new DGraph();	
	}

	@Override
	public void init(graph g) {
		DGraph newG=(DGraph) g;
		this.g= newG;

	}

	@Override
	public void init(String file_name) {


		String File = file_name;
		String line = "";
		String SplitBy = "\n";

		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(File));

			while ((line = br.readLine()) != null) 
			{
				String[] userInfo = line.split(SplitBy);

				System.out.println("Graph name : " + userInfo[0] + " , location points : " + userInfo[1] 
						/* +" Phone: " + userInfo[2] + " Country: " + userInfo[3]*/ );
			}

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("could not read file");
		}

	}




	@Override
	public void save(String file_name) {

	}

	@Override
	public boolean isConnected() {
		g.setTags();
		int n= this.g.G.size();
		Stack<NodeData> S= new Stack<NodeData>();
		List<NodeData> ListN= new ArrayList<NodeData>();
		for (int i = 0; i < n; i++) {
			NodeData a= (NodeData) this.g.G.get(n-i-1);
			S.add(a);
		}
		NodeData b= S.pop();
		ListN.add(b);
		b.setTag(1);
		while(!S.isEmpty()) {
			Collection<edge_data> c= g.getE(b.getKey());
			if( c != null) {
				for(edge_data e:c) {
					NodeData v= (NodeData) g.G.get(e.getDest());
					if(v != null) {
						if(v.getTag() == 0 ) {
							if(!ListN.contains(v)) {
								ListN.add(v);
							}
							v.setTag(1);
						}
					}
				}
			}
			if(S.size() == 1) {
				b=S.peek();
				if(g.R.get(b.getKey()).isEmpty()) {
					return false;
				}
			}
			b= S.pop();


		}
		//		System.out.println("ListN size =" +ListN.size() +"\n"+ "g.G.size = " + this.g.G.size()
		//			+"\n"	+ " stack size = " + S.size());
		if(ListN.size() == g.G.size()) {
			return true ;
		}

		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		List<node_data> shortest= shortestPath(src,dest);
		int n= shortest.size();

		double length =shortest.get(n-1).getWeight();
		return length;
	}

	

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		g.set_infinity();
		g.setTags();
		List<node_data> Shortest= new LinkedList<node_data>();
		double d =0 ;
		int n = this.g.G.size();
		NodeData s = (NodeData) g.getNode(src);
		Stack<NodeData> S= new Stack<NodeData>();

		s.setWeight(0);

		for( int i=0 ; i < n ; i++) {
			S.add((NodeData) g.G.get(n-i-1));
		}
		
		Shortest.add(s);
		
//		s= S.pop();
		while(!S.isEmpty()) {
			Collection<edge_data> c= g.getE(s.getKey());
			while(s.getKey() != dest) {
			for(edge_data e : c ) {
				
				NodeData v=(NodeData) g.G.get(e.getDest());
				if(v.getTag() == 0) {
					d= s.getWeight() + e.getWeight();
					if(d<= v.getWeight()) {
						v.setWeight(d);
						if(!Shortest.contains(v)) {
						Shortest.add(v);
						}
					}
				}
				v.setTag(1);
			}
			
			s= S.pop();
			}
		}
		return Shortest;

	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		List<node_data> smallestPath = new ArrayList<node_data>();
		Collection<node_data> vertex = this.g.getV();
		double currentPath = 0;
		double currentPathBack = 0;
		if(!vertex.isEmpty()) {
			double weightSmall = Double.POSITIVE_INFINITY;
			for (int i = 0; i < targets.size() - 1; i++) {
				for (int j = 1; j < targets.size(); j++) {
					currentPath = shortestPathDist(targets.get(i), targets.get(j));
					currentPathBack = shortestPathDist(targets.get(i), targets.get(j));
					currentPath += Math.min(currentPath, currentPathBack);
					smallestPath.add(shortestPath(targets.get(i), targets.get(j)).get(j));
					if (currentPath < weightSmall) {
						weightSmall = currentPath;
					}
				}
			}
			for (int i = 0; i < smallestPath.size(); i++) {
				System.out.println(smallestPath.get(i).getKey());
			}
			return smallestPath;
		}

		return null;

	}


	@Override
	public graph copy() {

		return this.g;
	}

}
