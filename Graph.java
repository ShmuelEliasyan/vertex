import java.util.ArrayList;
//208623678 Shmuel Eliasyan
public class Graph <E> {
	protected ArrayList<ArrayList<E>> data;
	//An empty constructor that initializes the list.
	public Graph() {
		data=new ArrayList<ArrayList<E>>();	
	}
	//The method accepts an E type object and adds it to the graph vector.
	public void addVertex(E ver) throws VertexExistException 	{
		for (ArrayList<E> vectors: data) {
			if(vectors.contains(ver)==true) 
				throw new VertexExistException("The vertex allready exist.");
		}
		ArrayList<E> newVertex=new ArrayList<E>();
		newVertex.add(ver);
		data.add(newVertex);
	}
	//The method accepts two nodes and adds an arc between the two.
	public void addEdge(E ver1, E ver2) throws VertexNotExistException{

		boolean exeption1=false,exeption2=false;
		for (ArrayList<E> vectors: data) {
			if(vectors.contains(ver1)==true) 
				exeption1=true;		}
		for (ArrayList<E> vectors: data) {
			if(vectors.contains(ver2)==true) 
				exeption2=true;
		}
		if(exeption1==false) {
			throw new VertexNotExistException("The first vertex dosent exist");
		}
		if(exeption2==false) {
			throw new VertexNotExistException("The second vertex dosent exist");
		}
		for (ArrayList<E> vectors: data) {
			if(vectors.get(0).equals(ver1)) {
				vectors.add(ver2);
			}
			if(vectors.get(0).equals(ver2)) {
				vectors.add(ver1);
			}
		}

	}
	//The method receives a node, and returns a list of all the nodes having a cross between ver to them.
	public ArrayList<E> getEdges(E ver) throws VertexNotExistException{
		boolean exception=false;
		ArrayList<E> connections = new ArrayList<E>();
		for (ArrayList<E> vectors: data) {
			if(vectors.contains(ver)==true) {
				exception=true;
			}
		}
		if(exception==false) {
			throw new VertexNotExistException("The vertex dosent exist");
		}
		for (ArrayList<E> vectors: data) {
			if(vectors.get(0).equals(ver)==true) {
				connections=new ArrayList<E>(vectors);
				break;
			}
		}
		connections.remove(0);
		return connections;
	}
	//The method returns an ArrayList object that will contain all the nodes in the graph.
	public ArrayList<E> getVertices(){
		ArrayList<E> vertices=new ArrayList<E>();
		for(ArrayList<E> vertex:data) {
			vertices.add(vertex.get(0));
		}
		return vertices;
	}
	//The method will receive two-node parameters. The method returns a list of nodes that constitute the route from-"from" to-"to".
	public ArrayList<E> bfs(E from, E to){
		boolean fromIn=false,toIn=false,found=false;
		ArrayList<ArrayList<E>> ways=new ArrayList<ArrayList<E>>();
		ArrayList<E> startWay=new ArrayList<E>();
		ways.add(startWay);
		ArrayList<E> bestWay=new ArrayList<E>();
		ArrayList<E> temp=new ArrayList<E>();
		for(ArrayList<E> vertex:data) {
			if(vertex.get(0).equals(from)) {
				ways.get(0).add(from);
				fromIn=true;
			}
		}
		for(ArrayList<E> vertex:data) {
			if(vertex.get(0).equals(to)) {
				toIn=true;
			}
		}
		if(fromIn==false||toIn==false) {
			return bestWay;
		}
		while(found==false&&(ways.size()!=0)) {
			temp=ways.get(0);
			if(temp.get(temp.size()-1)==to) {
				return temp;
			}
			ways.remove(0);
			for (ArrayList<E> vectors: data) {
				if(vectors.get(0).equals(temp.get(temp.size()-1))==true) {
					for(E temp2:vectors) {
						if(temp.contains(temp2)==false) {
							temp.add(temp2);
							ArrayList<E> temp3=new ArrayList<E>(temp);
							ways.add(temp3);
							temp.remove(temp.size()-1);
						}
					}
				}
			}
		}
		return bestWay;
	}
}

