package main.java.ma.EcoMove.B1.service;

import main.java.ma.EcoMove.B1.entity.BilletNode;
import main.java.ma.EcoMove.B1.entity.BilletGraph;

import java.time.LocalDate;
import java.util.*;

public class TicketFinder {


    public List<BilletNode> findShortestPath(String start, String destination, LocalDate startDate, BilletGraph graph) {
        Queue<BilletNode> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();


        Map<BilletNode, BilletNode> parentMap = new HashMap<>();


        queue.addAll(graph.getNeighbors(start));


        while (!queue.isEmpty()) {
            BilletNode current = queue.poll();


            if (current.getDestination().equals(destination) && !visited.contains(current.getDepart())) {
                return reconstructPath(parentMap, current);
            }


            visited.add(current.getDepart());


            for (BilletNode neighbor : graph.getNeighbors(current.getDestination())) {
                if (!visited.contains(neighbor.getDepart())) {
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return Collections.emptyList();
    }


    private List<BilletNode> reconstructPath(Map<BilletNode, BilletNode> parentMap, BilletNode destinationNode) {
        List<BilletNode> path = new ArrayList<>();
        BilletNode current = destinationNode;

        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }

        Collections.reverse(path);
        return path;
    }
}
