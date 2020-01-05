package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.edge;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

class GraphAlgoTEST {
	DGraph g = new DGraph();

	Point3D p1=new Point3D(0,-140);
	Point3D p2=new Point3D(-120,-20);
	Point3D p3=new Point3D(-320,0);
	NodeData a=new NodeData( p1);
	NodeData b=new NodeData( p2);
	NodeData c=new NodeData(p3);
	 
	Graph_Algo f2=new Graph_Algo(g);

	
	@Test
	void testInitGraph() {
		 g.addNode(a);
		 g.addNode(b);
		 g.addNode(c);
		 g.connect(a.getKey(), b.getKey(), 12);
		 g.connect(b.getKey(), c.getKey(), 12);
		 g.connect(c.getKey(), a.getKey(), 12);
		 Graph_Algo newg = new Graph_Algo();
		 newg.init(g);
		 if(!(newg instanceof Graph_Algo)) {
			 fail("your init is incorrect");
		 }
	}


	@Test
	void testIsConnected() {
		 g.addNode(a);
		 g.addNode(b);
		 g.addNode(c);
		 g.connect(a.getKey(), b.getKey(), 12);
		 g.connect(b.getKey(), c.getKey(), 12);
		 g.connect(c.getKey(), a.getKey(), 12);
		 Graph_Algo newg = new Graph_Algo();
		 newg.init(g);
		 
		 if(!newg.isConnected()) {
		fail("check your graph connection");
		 }
	}

	@Test
	void testShortestPathDist() {
		 g.addNode(a);
		 g.addNode(b);
		 g.addNode(c);
		 g.connect(a.getKey(), b.getKey(), 12);
		 g.connect(b.getKey(), c.getKey(), 12);
		 g.connect(c.getKey(), a.getKey(), 12);
		 Graph_Algo newg = new Graph_Algo();
		 newg.init(g);
		 double dis= newg.shortestPathDist(a.getKey(), c.getKey());
		if(dis != 24) {
		 fail("wrong math");
	}
	}

	@Test
	void testShortestPath() {
		 g.addNode(a);
		 g.addNode(b);
		 g.addNode(c);
		 g.connect(a.getKey(), b.getKey(), 12);
		 g.connect(b.getKey(), c.getKey(), 12);
		 g.connect(c.getKey(), a.getKey(), 12);
		 Graph_Algo newg = new Graph_Algo();
		 newg.init(g);
		
		List<node_data> list= newg.shortestPath(a.getKey(),c.getKey());
		if(list.isEmpty() ) {
		 fail("wrog path");
	}
		if(!(list.contains(a)) && !(list.contains(b))  ) {
			 fail("wrog path");

		}
	}

	@Test
	void testTSP() {
		g.addNode(a);
		 g.addNode(b);
		 g.addNode(c);
		 g.connect(a.getKey(), b.getKey(), 12);
		 g.connect(b.getKey(), c.getKey(), 12);
		 g.connect(c.getKey(), a.getKey(), 12);
		 Graph_Algo newg = new Graph_Algo();
		 newg.init(g);
		 List<Integer> list= new ArrayList<Integer>();
		 list.add(a.getKey());
		 list.add(b.getKey());
		 list.add(c.getKey());
		List<node_data> ans= newg.TSP(list);
		 if( ans != null ) {
			 fail(" it's not the shortest path ");
		 }
		
	}

	@Test
	void testCopy() {
		g.addNode(a);
		 g.addNode(b);
		 g.addNode(c);
		 g.connect(a.getKey(), b.getKey(), 12);
		 g.connect(b.getKey(), c.getKey(), 12);
		 g.connect(c.getKey(), a.getKey(), 12);
		 Graph_Algo newg = new Graph_Algo();
		 newg.init(g);
		graph nn= newg.copy();
		if(!nn.equals(newg.g))
		fail("did not copy");
	}

}
