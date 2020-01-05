package gui;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.Collection;

import com.sun.org.apache.xml.internal.security.Init;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;
import utils.StdDraw;

public class graphGUI  implements Serializable {
	
public graph g=new DGraph();
public Graph_Algo algo=new Graph_Algo(this.g);

	
	public graphGUI(graph g) {
		this.g = g;	
		this.algo = new Graph_Algo(g);
		StdDraw.setGui(this);
	}
	public graphGUI() {
		algo=new Graph_Algo(this.g);
		g=new DGraph();
		StdDraw.setGui(this);
	}
	public void init(String file_name) {
		this.algo.init(file_name);
		this.g= algo.g;
		drawAll();
		
		
	}
	public void drawAll() {
		
		drawCanvas();
		drawEdges();
		drawNodes();
	}
	
	
	public void drawCanvas() {
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(-200, 200);
		StdDraw.setYscale(-200, 200);


	}
	public void drawNodes() {
		StdDraw.setPenColor(Color.BLUE);
		StdDraw.setPenRadius(0.03);

		for (node_data nodes : g.getV()) {

			StdDraw.point(nodes.getLocation().x(), nodes.getLocation().y());
			StdDraw.setFont(new Font("Ariel", Font.ROMAN_BASELINE, 20));
			StdDraw.text(nodes.getLocation().x(), nodes.getLocation().y()+7, ""+ nodes.getKey());
		}
	}

	public void drawEdges() {

		StdDraw.setPenRadius(0.008);
		Collection<node_data> points = g.getV();
		for(node_data nodes: points) {
			Collection<edge_data> e = g.getE(nodes.getKey());
		try {	for (edge_data edge : e) {
				double x0= nodes.getLocation().x();
				double y0= nodes.getLocation().y();
				
				double x1= g.getNode(edge.getDest()).getLocation().x();
				double y1= g.getNode(edge.getDest()).getLocation().y();
				StdDraw.setPenRadius(0.005);

				StdDraw.setPenColor(Color.RED);
				StdDraw.line(x0, y0, x1, y1);

				StdDraw.setFont(new Font("Ariel", Font.BOLD, 15));

				StdDraw.setPenColor(Color.gray);
				StdDraw.setPenRadius(0.05);
				StdDraw.point((x0+x1*3)/4, (y0+y1*3)/4);

				StdDraw.setPenColor(Color.black);
				StdDraw.text((x0+x1*3)/4, (y0+y1*3)/4, ""+ edge.getWeight());
			}
		}
			catch (Exception ea) {
			}
		}
	}
	
	}
