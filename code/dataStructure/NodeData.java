package dataStructure;

import java.util.HashMap;
import java.util.List;

import utils.Point3D;

public class NodeData implements node_data {
	
	
	
	private int key=0,tag=0;
	public static int num =0;
	public static final double INFINITY= Double.POSITIVE_INFINITY;

	private double weight = INFINITY ;
	private Point3D location;
	private String Info;
	
	public  HashMap<Integer, edge_data>  enteringEdges  = new HashMap<Integer, edge_data>();
	public  HashMap<Integer, edge_data> OutEdges = new HashMap<Integer, edge_data>();
	
/////////////////////////////////////////////////////////////////////////////////////////

	
	/////////////////////////////////////////////////////////////////
    ///////////////////     Constructor     /////////////////////////
    /////////////////////////////////////////////////////////////////
	
	public NodeData( Point3D p) {
		this.key = num++;
		setLocation(p);
		enteringEdges = new HashMap<Integer, edge_data>();
		OutEdges = new HashMap<Integer, edge_data>();
		
	}
	public 	NodeData(NodeData n) {
		this.key=n.getKey();
		this.Info=n.getInfo();
		this.location = n.getLocation();
		this.weight = n.getWeight();
		this.tag = n.getTag();
		this.OutEdges = n.OutEdges;
		this.enteringEdges = n.enteringEdges;
	
		
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public Point3D getLocation() {
		
		return this.location;
	}

	@Override
	public void setLocation(Point3D p) {
		this.location = p;

	}

	@Override
	public double getWeight() {
		
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight = w;
	}

	@Override
	public String getInfo() {
		
		return this.Info;
	}

	@Override
	public void setInfo(String s) {
		this.Info = s;
	}

	@Override
	public int getTag() {
		
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag=t;
	}

}
