package project;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

class Edge_info{
	int distance;
	List<Integer> paths = new ArrayList<>();
	public Edge_info(int distance) {
		this.distance = distance;
	}
}
public class Steiner_tree {

	
	public static void main(String[] args) {
		
		ArrayList<ArrayList<Edge_info>> distance = new ArrayList<ArrayList<Edge_info>>();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of nodes:");		
		int size = sc.nextInt();
		System.out.println("Enter the edge cost (If there is no edge then enter 9999)");
		for(int i=0;i<size;i++) {
			ArrayList<Edge_info> distance1 = new ArrayList<>(); 
			System.out.println("Edge cost from "+i+" to all other nodes:");
			for(int j=0;j<size;j++) {
				System.out.println("Edge cost from "+i+" to "+j+":");
				distance1.add(new Edge_info(sc.nextInt()));
			}
			distance.add(distance1);
		}
		System.out.println("Enter the terminal count:");
		int terminal_size = sc.nextInt();
		System.out.println("Enter the terminals:");
		List<Integer> terminals = new ArrayList<>();
		for(int i=0;i<terminal_size;i++)
			terminals.add(sc.nextInt());
		/*ArrayList<Edge_info> distance1 = new ArrayList<>(); 
		ArrayList<Edge_info> distance2 = new ArrayList<>(); 
		ArrayList<Edge_info> distance3 = new ArrayList<>(); 
		ArrayList<Edge_info> distance4 = new ArrayList<>(); 
		ArrayList<Edge_info> distance5 = new ArrayList<>(); 
		distance1.add(new Edge_info(0));distance1.add(new Edge_info(4));distance1.add(new Edge_info(2));distance1.add(new Edge_info(9999));distance1.add(new Edge_info(9999));
		distance2.add(new Edge_info(4));distance2.add(new Edge_info(0));distance2.add(new Edge_info(3));distance2.add(new Edge_info(1));distance2.add(new Edge_info(9999));
		distance3.add(new Edge_info(2));distance3.add(new Edge_info(3));distance3.add(new Edge_info(0));distance3.add(new Edge_info(2));distance3.add(new Edge_info(9999));
		distance4.add(new Edge_info(9999));distance4.add(new Edge_info(1));distance4.add(new Edge_info(2));distance4.add(new Edge_info(0));	distance4.add(new Edge_info(2));
		distance5.add(new Edge_info(9999));distance5.add(new Edge_info(9999));distance5.add(new Edge_info(9999));distance5.add(new Edge_info(2));distance5.add(new Edge_info(0));
		distance.add(distance1);
		distance.add(distance2);
		distance.add(distance3);
		distance.add(distance4);
		distance.add(distance5);*/
		distance = allPairsShortestPath(distance,5);
		
		for(int i=0;i<distance.size();i++) {
			for(int j=0;j<distance.size();j++) {
				System.out.print("\t"+distance.get(i).get(j).distance);
				for(int k=0;k<distance.get(i).get(j).paths.size();k++) {
					System.out.print(distance.get(i).get(j).paths.get(k));
				}
				
			}
			System.out.println();
		}
		
		findSteiner(distance,terminals);

	}

	private static void findSteiner(ArrayList<ArrayList<Edge_info>> distance, List<Integer> terminals) {
		List<Integer> result = new ArrayList<>();
		List<Integer> temp_result = new ArrayList<>();
		List<String> edge_result = new ArrayList<>();
		List<String>  temp_edge_result = new ArrayList<>();
		
	 	result.add(terminals.get(0));
		int minimum=Integer.MAX_VALUE;
		for(int i=0;i<terminals.size()-1;i++) {
			for(int j=0;j<result.size();j++) {
				for(int k=0;k<terminals.size();k++) {
					if(!result.contains(terminals.get(k)) && distance.get(result.get(j)).get(terminals.get(k)).distance<minimum){
						minimum = distance.get(result.get(j)).get(terminals.get(k)).distance;
						temp_result.clear();
						temp_edge_result.clear();
						temp_result.add(terminals.get(k));
						if(!distance.get(result.get(j)).get(terminals.get(k)).paths.isEmpty()) {
							temp_result.addAll(distance.get(result.get(j)).get(terminals.get(k)).paths);
							temp_edge_result.add(result.get(j)+" -> " + distance.get(result.get(j)).get(terminals.get(k)).paths.get(0));
							temp_edge_result.add(distance.get(result.get(j)).get(terminals.get(k)).paths.get(distance.get(result.get(j)).get(terminals.get(k)).paths.size()-1)+" -> " + terminals.get(k) );
							if(distance.get(result.get(j)).get(terminals.get(k)).paths.size()>1) {
								for(int z=0;z<distance.get(result.get(j)).get(terminals.get(k)).paths.size()-1;z++)
									temp_edge_result.add(distance.get(result.get(j)).get(terminals.get(k)).paths.get(z)+" -> " + distance.get(result.get(j)).get(terminals.get(k)).paths.get(z+1));
							}
						}
						else {
							temp_edge_result.add(result.get(j)+" -> " + terminals.get(k));
						}
					}
				}
			}
			edge_result.addAll(temp_edge_result);
			result.addAll(temp_result);
			
			minimum = Integer.MAX_VALUE;
		}
		System.out.println(result);
		System.out.println(edge_result);
	}

	private static ArrayList<ArrayList<Edge_info>> allPairsShortestPath(ArrayList<ArrayList<Edge_info>> distance,int number_of_nodes) {
	
		for(int k = 0;k<number_of_nodes;k++)
			for(int i=0;i<number_of_nodes;i++)
				for(int j=0;j<number_of_nodes;j++)
					if(distance.get(i).get(j).distance>distance.get(i).get(k).distance + distance.get(k).get(j).distance) {
						distance.get(i).get(j).distance= distance.get(i).get(k).distance + distance.get(k).get(j).distance;
						distance.get(i).get(j).paths.clear();
						if(!distance.get(i).get(k).paths.isEmpty())
							distance.get(i).get(j).paths.addAll(distance.get(i).get(k).paths);
						if(!distance.get(k).get(j).paths.isEmpty())
							distance.get(i).get(j).paths.addAll(distance.get(k).get(j).paths);
						distance.get(i).get(j).paths.add(k);
					}
						
		return distance;
	}

}
