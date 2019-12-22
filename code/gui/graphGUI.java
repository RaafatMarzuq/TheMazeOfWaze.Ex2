package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Collection;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.StdDraw;

public class graphGUI {

	private graph g;
	private Collection<node_data> points;

	public graphGUI(graph g) {
		this.g = g;	
		this.points=g.getV();


	}
	public void drawAll() {
		drawCanvas();
		drawEdges();
		drawNodes();
		drawSrcPoint();
	}

	public void drawCanvas() {
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(-200, 200);
		StdDraw.setYscale(-200, 200);
		

	}
	public void drawNodes() {
		StdDraw.setPenColor(Color.BLUE);
		StdDraw.setPenRadius(0.03);

		for (node_data nodes : points) {

			StdDraw.point(nodes.getLocation().x(), nodes.getLocation().y());
			StdDraw.setFont(new Font("Ariel", Font.ROMAN_BASELINE, 20));
			StdDraw.text(nodes.getLocation().x(), nodes.getLocation().y()+7, ""+ nodes.getKey());
		}
	}
	public void drawSrcPoint() {
		
		StdDraw.setPenRadius(0.02);
		Collection<node_data> points = g.getV();
		for(node_data nodes: points) {
			Collection<edge_data> e = g.getE(nodes.getKey());
			for (edge_data edge : e) {
				double x0= nodes.getLocation().x();
				double y0= nodes.getLocation().y();
				double x1= g.getNode(edge.getDest()).getLocation().x();
				double y1= g.getNode(edge.getDest()).getLocation().y();
						
				StdDraw.setPenColor(Color.GREEN);
				StdDraw.point(((x1 -x0) ), ((y1 - y0)));

				
			}
		}
//		for (node_data nodes : points) {
//
//			StdDraw.point((nodes.getLocation().x() - 5), (nodes.getLocation().y()-5));
//			StdDraw.setFont(new Font("Ariel", Font.ROMAN_BASELINE, 20));
		//	StdDraw.text(nodes.getLocation().x(), nodes.getLocation().y()+7, ""+ nodes.getKey());
		}
	//}
	public void drawEdges() {
		
		StdDraw.setPenRadius(0.006);
		
		Collection<node_data> points = g.getV();
		for(node_data nodes: points) {
			Collection<edge_data> e = g.getE(nodes.getKey());
			for (edge_data edge : e) {
				double x0= nodes.getLocation().x();
				double y0= nodes.getLocation().y();
				double x1= g.getNode(edge.getDest()).getLocation().x();
				double y1= g.getNode(edge.getDest()).getLocation().y();
				
				StdDraw.setPenColor(Color.RED);
				StdDraw.line(x0, y0, x1, y1);
				
				StdDraw.setFont(new Font("Ariel", Font.BOLD, 20));
				StdDraw.setPenColor(Color.black);
				StdDraw.text((x0+x1)*0.5, (y0+y1)*0.5, ""+ edge.getWeight());
			}
		}
	}
}