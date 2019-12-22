package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import dataStructure.NodeData;
import dataStructure.edge;
import utils.Point3D;

class NodeDataTEST {
	Point3D p = new	Point3D(34, 54, 324);
	String s="";
	NodeData n = new NodeData(p);
	NodeData n2 = new NodeData(p);
	NodeData n1 = new NodeData(p);
	
	
	
	@Test
	void testGetKey() {
		NodeData[] node = new NodeData[3];
		node[0] = n ;node[1] = n2 ;node[2] = n1 ;
		int[] ar = new int[3] ;
		ar[0]=n.getKey();ar[1]= n2.getKey() ;ar[2]= n1.getKey() ;
		
		for(int i=0 ; i < 3 ; i++) {
			
			assertEquals(node[i].getKey(), ar[i]);
		}
		
				
	}

	@Test
	void testNodeDataDoublePoint3D() {
		NodeData n4 = new NodeData(p);
		assertEquals(n.INFINITY, n.getWeight());
		assertEquals(p, n.getLocation());
		
		if(!n4.OutEdges.isEmpty() ) {
			fail("Check your Node parametirs");

		}
		if(!n4.enteringEdges.isEmpty() ) {
			fail("Check your Node parametirs");

		}
	
		

	}

	


	@Test
	void testGetLocation() {
	
		assertEquals(p, n.getLocation());
	}

	@Test
	void testSetLocation() {
		NodeData n1 = new NodeData(p);
		Point3D p1 = new	Point3D(34, 54, 324);
		n1.setLocation(p1);
		assertEquals(p1, n1.getLocation());

		}

	@Test
	void testGetWeight() {
		assertEquals(n.INFINITY, n.getWeight());

	}

	@Test
	void testSetWeight() {
		NodeData n3= new NodeData(p);
		n3.setWeight(155);
		assertEquals(155, n3.getWeight());

	}

	@Test
	void testGetInfo() {
		n.setInfo(s);
		if(!n.getInfo().equals(s.toString())) {
			fail("The Info that you inseted does not match the nodes Info");
		};
		}

	@Test
	void testSetInfo() {
		NodeData n2= new NodeData(p);
		n2.setInfo("I love pizza");
		if(!n2.getInfo().equals("I love pizza" )) {
			fail("The Info that you inseted does not match the nodes Info");
		}
	}

	@Test
	void testGetTag() {
		
	
		assertEquals(0, n.getTag());

	}

	@Test
	void testSetTag() {
		NodeData n3= new NodeData(p);
		n3.setTag(10);
		assertEquals(10, n3.getTag());
	}

}
