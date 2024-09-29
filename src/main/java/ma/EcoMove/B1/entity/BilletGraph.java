package main.java.ma.EcoMove.B1.entity;
import java.util.*;

public class BilletGraph {
    private Map<String, List<BilletNode>> graph = new HashMap<>();

    public void buildGraph(List<Billet> billets) {
        for (Billet billet : billets) {
            BilletNode node = new BilletNode(billet);
            graph.computeIfAbsent(billet.getDepart(), k -> new ArrayList<>()).add(node);
        }
    }

    public List<BilletNode> getNeighbors(String depart) {
        return graph.getOrDefault(depart, new ArrayList<>());
    }
}

