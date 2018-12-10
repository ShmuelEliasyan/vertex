
public class SocialNetwork {
	Graph<String> network;
	//An empty constructor that initializes the list.
	public SocialNetwork(){
		network=new Graph<String>();
	}
	//The method accepts a String object and adds it to the graph.
	public void addUser(String user) throws UserExistException{
		try {
			network.addVertex(user);
		}
		catch(VertexExistException e) {
			throw new UserExistException("The user allready exist at the network.");
		}
	}
	//The method accepts two String objects and adds a friendship link.
	public void addFriends(String user1, String user2) throws UserNotFoundException{
		try {
			network.addEdge(user1, user2);
		}
		catch(VertexNotExistException c) {
			throw new UserNotFoundException("One of the users dosent exict.");
		}
	}
	//A method that accepts two String objects that represent two users. The method returns true if users know each other
	public boolean knows(String user1, String user2) throws UserNotFoundException{
		try {
			network.getEdges(user1);
			network.getEdges(user2);
		}
		catch(VertexNotExistException d){
			throw new UserNotFoundException("One of the users dosent exict.");
		}
		if(network.bfs(user1, user2).size()==0) {
			return false;
		}
		return true;
	}
}
