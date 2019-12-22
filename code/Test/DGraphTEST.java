package Test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import com.sun.xml.internal.bind.v2.util.FatalAdapter;

import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.edge;
import dataStructure.edge_data;
import dataStructure.node_data;
import utils.Point3D;

class DGraphTEST {

	DGraph g = new DGraph();
	Point3D p = new	Point3D(34, 54, 324);
	Point3D p1 = new	Point3D(340, 654, 224);
	Point3D p2 = new	Point3D(100, 574, 624);
	String s="";
	node_data n = new NodeData(p);
	node_data n1 = new NodeData(p1);
	node_data n2 = new NodeData(p2);


	@Test
	void testGetNode() {
		System.out.println("\n*** getNode ***");

		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);	
		edge_data e1 = new edge(n.getKey(),n1.getKey(),10);
		edge_data e2 = new edge(n1.getKey(),n2.getKey(),10);
		edge_data e3 = new edge(n2.getKey(),n.getKey(),10);
		g.connect(n.getKey(), n1.getKey(), e1.getWeight());
		g.connect(n1.getKey(),n2.getKey() , e2.getWeight());
		g.connect(n2.getKey(),n.getKey(), e3.getWeight());


		System.out.println(n.getKey());
		System.out.println(n1.getKey());
		System.out.println(n2.getKey());
		System.out.println("e1 src --> dest: " + e1.getSrc() + " --> " + e1.getDest() );
		System.out.println("e2 src --> dest: " + e2.getSrc() + " --> " + e2.getDest() );
		System.out.println("e3 src --> dest: " + e3.getSrc() + " --> " + e3.getDest() );
		System.out.println(g.edgeSize());
		System.out.println(g.nodeSize());

		if(g.getNode(n.getKey()).getKey() != n.getKey()) {
			fail(" failed at building a graph");
		}

	}

	@Test
	void testGetEdge() {
		System.out.println("\n*** getEdge ***");

		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);	
		edge_data e1 = new edge(n.getKey(),n1.getKey(),10);
		edge_data e2 = new edge(n1.getKey(),n2.getKey(),10);
		edge_data e3 = new edge(n2.getKey(),n.getKey(),10);
		g.connect(n.getKey(), n1.getKey(), e1.getWeight());
		g.connect(n1.getKey(),n2.getKey() , e2.getWeight());
		g.connect(n2.getKey(),n.getKey(), e3.getWeight());


		System.out.println(n.getKey());
		System.out.println(n1.getKey());
		System.out.println(n2.getKey());
		System.out.println("e1 src --> dest: " + e1.getSrc() + " --> " + e1.getDest() );
		System.out.println("e2 src --> dest: " + e2.getSrc() + " --> " + e2.getDest() );
		System.out.println("e3 src --> dest: " + e3.getSrc() + " --> " + e3.getDest() );
		System.out.println(g.edgeSize());
		System.out.println(g.nodeSize());
		int src = n.getKey();
		int dest= n1.getKey();
		if( g.getEdge(src, dest).getSrc() != e1.getSrc()) {
			if( g.getEdge(src, dest).getSrc() != e1.getSrc()) {

				fail(" check your connect function");
			}
		}
	}

	@Test
	void testAddNode() {
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);	
		if(!(g.G.containsValue(n) &&g.G.containsValue(n1) && g.G.containsValue(n2)) ) {
			fail("Not yet implemented");
		}
		
	}

	@Test
	void testConnect() {
		g.addNode(n);
		g.addNode(n1);
		edge_data e1 = new edge(n.getKey(),n1.getKey(),10);
		g.connect(n.getKey(), n1.getKey(), e1.getWeight());
		if(g.R.isEmpty() && g.G.isEmpty())
		fail("Not yet implemented");
	}

	@Test
	void testGetV() {
		System.out.println("\n*** getV ***");
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);	
		Point3D p3= new Point3D(123, 234);
		NodeData n3 = new NodeData(p3) ;
		g.addNode(n3);
		edge_data e1 = new edge(n.getKey(),n1.getKey(),10);
		edge_data e2 = new edge(n1.getKey(),n2.getKey(),10);
		edge_data e3 = new edge(n2.getKey(),n.getKey(),10);
		edge_data e4 = new edge(n.getKey(),n3.getKey(),10);
		g.connect(n.getKey(), n3.getKey(), e4.getWeight());
		g.connect(n.getKey(), n1.getKey(), e1.getWeight());
		g.connect(n1.getKey(),n2.getKey() , e2.getWeight());
		g.connect(n2.getKey(),n.getKey(), e3.getWeight());
		Collection<node_data> V = g.getV();
		if(V == null) {
		return;	
		}
		Iterator<node_data> i=V.iterator();
	System.out.print("the nodes in the graph are : {");
		while(i.hasNext()) {
			node_data x= i.next();
			System.out.print(x.getKey() +",");
		}
		System.out.print("}");
		if( g.getV()== null) {
			if(g.getV()== null) {
				fail("you didn't connect your graph");
			}
		}
	}

	@Test
	void testGetE() {
		System.out.println("\n*** getE ***");

		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);	
		Point3D p3= new Point3D(123, 234);
		NodeData n3 = new NodeData(p3) ;
		g.addNode(n3);
		edge_data e1 = new edge(n.getKey(),n1.getKey(),10);
		edge_data e2 = new edge(n1.getKey(),n2.getKey(),10);
		edge_data e3 = new edge(n2.getKey(),n.getKey(),10);
		edge_data e4 = new edge(n.getKey(),n3.getKey(),10);
		g.connect(n.getKey(), n3.getKey(), e4.getWeight());
		g.connect(n.getKey(), n1.getKey(), e1.getWeight());
		g.connect(n1.getKey(),n2.getKey() , e2.getWeight());
		g.connect(n2.getKey(),n.getKey(), e3.getWeight());


		System.out.println(n.getKey());
		System.out.println(n1.getKey());
		System.out.println(n2.getKey());
		System.out.println(n3.getKey());

		System.out.println("e1 src --> dest: " + e1.getSrc() + " --> " + e1.getDest() );
		System.out.println("e2 src --> dest: " + e2.getSrc() + " --> " + e2.getDest() );
		System.out.println("e3 src --> dest: " + e3.getSrc() + " --> " + e3.getDest() );
		System.out.println("e4 src --> dest: " + e4.getSrc() + " --> " + e4.getDest() );

		System.out.println("there are { " + g.edgeSize()+" } edges" );
		System.out.println("there are { " + g.nodeSize() + " } vertexes");
		System.out.println();
		Collection<edge_data> c= g.getE(n.getKey()) ;

		Iterator<edge_data> i= c.iterator();
		System.out.println("all the edges getting out of : " + n.getKey());
		while(i.hasNext()) {
			edge x= (edge) i.next();
			System.out.print( x.getSrc() +"-->" +x.getDest() );
			System.out.println();
		}

	}

	@Test
	void testRemoveNode() {
		System.out.println("\n*** removeNode ***");
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);	
		Point3D p3= new Point3D(123, 234);
		NodeData n3 = new NodeData(p3) ;
		g.addNode(n3);
		edge_data e1 = new edge(n.getKey(),n1.getKey(),10);
		edge_data e2 = new edge(n1.getKey(),n2.getKey(),10);
		edge_data e3 = new edge(n2.getKey(),n.getKey(),10);
		edge_data e4 = new edge(n.getKey(),n3.getKey(),10);
		g.connect(n.getKey(), n3.getKey(), e4.getWeight());
		g.connect(n.getKey(), n1.getKey(), e1.getWeight());
		g.connect(n1.getKey(),n2.getKey() , e2.getWeight());
		g.connect(n2.getKey(),n.getKey(), e3.getWeight());
		
		g.removeNode(n.getKey());
		if(g.getNode(n.getKey()) != null) {
			fail("node has not been removed");

		}
		System.out.println(g.getNode(n.getKey()) );


	}

	@Test
	void testRemoveEdge() {
		System.out.println("\n*** removeEdge ***");
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);	
		Point3D p3= new Point3D(123, 234);
		NodeData n3 = new NodeData(p3) ;
		g.addNode(n3);
		edge_data e1 = new edge(n.getKey(),n1.getKey(),10);
		edge_data e2 = new edge(n1.getKey(),n2.getKey(),10);
		edge_data e3 = new edge(n2.getKey(),n.getKey(),10);
		edge_data e4 = new edge(n.getKey(),n3.getKey(),10);
		g.connect(n.getKey(), n3.getKey(), e4.getWeight());
		g.connect(n.getKey(), n1.getKey(), e1.getWeight());
		g.connect(n1.getKey(),n2.getKey() , e2.getWeight());
		g.connect(n2.getKey(),n.getKey(), e3.getWeight());
	
		g.removeEdge(n.getKey(), n1.getKey());
		
		if(g.getEdge(n.getKey(),n1.getKey() ) != null ) {
		fail("edge has not been removed");
		}
		System.out.println(g.getEdge(n.getKey(), n1.getKey()));
		
	}

	

	@Test
	void testGetMC() {
		g.addNode(n);
		g.addNode(n1);
		g.addNode(n2);	
		edge_data e1 = new edge(n.getKey(),n1.getKey(),10);
		edge_data e2 = new edge(n1.getKey(),n2.getKey(),10);
		edge_data e3 = new edge(n2.getKey(),n.getKey(),10);
		g.connect(n.getKey(), n1.getKey(), e1.getWeight());
		g.connect(n1.getKey(),n2.getKey() , e2.getWeight());
		g.connect(n2.getKey(),n.getKey(), e3.getWeight());


		System.out.println(n.getKey());
		System.out.println(n1.getKey());
		System.out.println(n2.getKey());
		System.out.println("e1 src --> dest: " + e1.getSrc() + " --> " + e1.getDest() );
		System.out.println("e2 src --> dest: " + e2.getSrc() + " --> " + e2.getDest() );
		System.out.println("e3 src --> dest: " + e3.getSrc() + " --> " + e3.getDest() );
		System.out.println(g.edgeSize());
		System.out.println(g.nodeSize());
		int src = n.getKey();
		int dest= n1.getKey();

		System.out.println( "MC= " + g.getMC());
		if (g.getMC() != g.MC) {
			fail("Not yet implemented");

		}
	}
}
