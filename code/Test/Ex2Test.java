package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.*;
import dataStructure.*;
import utils.*;
import gui.*;

/**
 * EX2 Structure test:
 * 1. make sure your code compile with this dummy test (DO NOT Change a thing in this test). 
 * 2. the only change require is to run your GUI window (see line 64).
 * 3. EX2 auto-test will be based on such file structure.
 * 4. Do include this test-case in you submitted repository, in folder Tests (under src).
 * 5. Do note that all the required packages are imported - do NOT use other 
 * 
 * @author boaz.benmoshe
 *
 */
class Ex2Test {
	private static graph _graph;
	private static graph_algorithms _alg;
	public static final double EPS = 0.001; 
	private static Point3D min = new Point3D(0,0,0);
	private static Point3D max = new Point3D(100,100,0);
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		_graph = tinyGraph();
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testConnectivity() {
		_alg = new Graph_Algo(_graph);
		boolean con = _alg.isConnected();
		if(!con) {
			fail("shoulbe be connected");
		}
	}
	@Test
	void testgraph() {
		boolean ans = drawGraph(_graph);
		assertTrue(ans);
	}
	
	private static graph tinyGraph() {
		graph ans = new DGraph();
		return ans;
	}
	boolean drawGraph(graph g) { 
		// YOUR GUI graph draw 
		Point3D p1=new Point3D(0,0);
		Point3D p2=new Point3D(61,1);
		Point3D p3=new Point3D(-115,-180);
		Point3D p4=new Point3D(97,100);
		Point3D p5=new Point3D(150,-120);
		Point3D p6=new Point3D(114,150);
		Point3D p7=new Point3D(-96,114);
		Point3D p8=new Point3D(-115,-130);
		Point3D p9=new Point3D(-172,14);
		Point3D p10=new Point3D(-12,143);

		NodeData a=new NodeData( p1);
		NodeData b=new NodeData( p2);
		NodeData c=new NodeData(p3);
		NodeData d=new NodeData(p4);	
		NodeData e=new NodeData(p5);
		NodeData f=new NodeData(p6);
		NodeData j=new NodeData(p7);
		NodeData h=new NodeData(p8);
		NodeData i=new NodeData(p9);
		NodeData t=new NodeData(p10);

		((graph) g).addNode(a);
		((graph) g).addNode(b);
		((graph) g).addNode(c);
		((graph) g).addNode(d);
		((graph) g).addNode(e);
		((graph) g).addNode(f);
		((graph) g).addNode(j);
		((graph) g).addNode(h);
		((graph) g).addNode(i);

	

		((graph) g).connect(a.getKey(),b.getKey(),4);
		((graph) g).connect(b.getKey(),c.getKey(), 2);
		((graph) g).connect(c.getKey(),d.getKey(),4);
		((graph) g).connect(d.getKey(),e.getKey(), 2);
		((graph) g).connect(e.getKey(),f.getKey(),4);
		((graph) g).connect(f.getKey(),j.getKey(), 2);
		((graph) g).connect(j.getKey(),h.getKey(),4);
		((graph) g).connect(h.getKey(),i.getKey(), 2);
		((graph) g).connect(i.getKey(),a.getKey(), 2);
		((graph) g).connect(d.getKey(),f.getKey(), 12);
		((graph) g).addNode(t);
		g.connect(6,9,2);
		g.connect(9, 5, 4);
		return true;
		
	}

}