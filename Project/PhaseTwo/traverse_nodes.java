Stack connectionPath = new Stack();

List<Stack> connectionPaths = new ArrayList<>();

// Push to connectionsPath the object that would be passed as the parameter 'node' into the method below
void findAllPaths(Object node, Object targetNode) {
	
    for (Object nextNode : nextNodes(node)) {
    	
       if (nextNode.equals(targetNode)) {
    	   
           Stack temp = new Stack();
           
           for (Object node1 : connectionPath)
   
               temp.add(node1);
           
           connectionPaths.add(temp);
           
       } else if (!connectionPath.contains(nextNode)) {
    	   
           connectionPath.push(nextNode);
           
           findAllPaths(nextNode, targetNode);
           
           connectionPath.pop();
        }
    }
}