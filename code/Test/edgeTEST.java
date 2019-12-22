package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructure.edge;

class edgeTEST {
	edge e1 = new edge(0,1,10);
	edge e2 = new edge(1,2,10);
	edge e3 = new edge(2,3,10);

	
	int a[] = {0,1,2};
	int b[] = {1,2,3};

	
	@Test
	void testEdge() {
		edge e = new edge(0,1,10);
		assertEquals(e.getSrc(), 0);
		assertEquals(e.getDest(), 1);
		assertEquals(e.getWeight(), 10);

	}

	@Test
	void testGetSrc() {
	
		for (int i = 0; i < a.length; i++) {
			if(i==0) {
			assertEquals(e1.getSrc(), a[i]);
			}
			if(i==1) {
				assertEquals(e2.getSrc(), a[i]);
				}
			if(i==2) {
				assertEquals(e3.getSrc(), a[i]);
				}
		}
		
	}

	@Test
	void testGetDest() {
		for (int i = 0; i < a.length; i++) {
			if(i==0) {
			assertEquals(e1.getDest(),b[i]);
			}
			if(i==1) {
				assertEquals(e2.getDest(), b[i]);
				}
			if(i==2) {
				assertEquals(e3.getDest(),b[i]);
				}
		}
	}

	@Test
	void testGetWeight() {
		if(e1.getWeight() != 10 ) {
			fail("check your Constructor");
		}
	}

	@Test
	void testGetInfo() {
		if(!e1.getInfo().equals("")) {
		fail("check your Constructor");
		}
	}

	@Test
	void testSetInfo() {
		e2.setInfo("abc");
		if(e2.getInfo().equals(""))
			fail("check your Constructor");
	}

	@Test
	void testGetTag() {
		assertEquals(e1.getTag(), 0);
	}

	@Test
	void testSetTag() {
		e3.setTag(132);
		assertEquals(e3.getTag(),132);
	}

}
