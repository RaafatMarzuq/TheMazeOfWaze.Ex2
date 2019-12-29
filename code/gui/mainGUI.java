package gui;


import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.graph;
import utils.Point3D;

public class mainGUI {

	public static void main(String[] args) {
		DGraph g = new DGraph();

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

		System.out.println(a.getKey());
		System.out.println(b.getKey());
		System.out.println(c.getKey());
		System.out.println(d.getKey());
		System.out.println(e.getKey());
		System.out.println(f.getKey());
		System.out.println(j.getKey());
		System.out.println(h.getKey());
		System.out.println(i.getKey());


		((graph) g).connect(a.getKey(),b.getKey(),4);
		((graph) g).connect(b.getKey(),c.getKey(), 2);
		((graph) g).connect(c.getKey(),d.getKey(),4);
		((graph) g).connect(d.getKey(),e.getKey(), 2);
		((graph) g).connect(e.getKey(),f.getKey(),4);
		((graph) g).connect(f.getKey(),j.getKey(), 2);
		((graph) g).connect(j.getKey(),h.getKey(),4);
		((graph) g).connect(h.getKey(),i.getKey(), 2);
		((graph) g).connect(i.getKey(),a.getKey(), 12);
		((graph) g).connect(d.getKey(),f.getKey(), 32);
		((graph) g).addNode(t);
		g.connect(6,9,2);
		g.connect(9, 5, 4);
	//	g.removeEdge(d.getKey(),f.getKey());
//		g.removeEdge(8, 0);
//		g.removeEdge(8, 0);
//		g.removeNode(8);
//	  g.removeNode(1);
//	  g.removeNode(2);
//	  g.removeNode(3);
//	  g.removeNode(4);
//	  g.removeNode(5);
//	  g.removeNode(6);
//	  g.removeNode(7);
//	  g.removeNode(8);
	//  g.removeNode(9);
	//	g.removeEdge(9, 5);
	//	g.removeEdge(6, 9);

		graphGUI bro = new graphGUI(g);
		Graph_Algo f2=new Graph_Algo();
		f2.init(g);
		bro.drawAll();
		System.out.println(f2.isConnected());
		System.out.println(f2.shortestPathDist(6, 5));

}
}