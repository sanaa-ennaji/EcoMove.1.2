package main.java.ma.EcoMove.B1.service;

import main.java.ma.EcoMove.B1.dao.Interface.IBillet;
import main.java.ma.EcoMove.B1.entity.Billet;
import main.java.ma.EcoMove.B1.entity.BilletGraph;
import main.java.ma.EcoMove.B1.entity.BilletNode;
import main.java.ma.EcoMove.B1.service.IService.IBilletService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class BilletService implements IBilletService {
    private final IBillet billetDAO;
    private final TicketFinder ticketFinder;

    public BilletService(IBillet billetDAO) {
        this.billetDAO = billetDAO;
        this.ticketFinder = new TicketFinder();
    }

    @Override
    public void createBillet(Billet billet) throws SQLException {
        if (billet.getContrat() == null || billet.getContrat().getId() == null) {
            throw new IllegalArgumentException("Contrat not found or invalid.");
        }
        billetDAO.createBillet(billet);
    }

    @Override
    public Billet getBilletById(UUID id) throws SQLException {
        return billetDAO.getBilletById(id);
    }

    @Override
    public List<Billet> getAllBillets() throws SQLException {
        return billetDAO.getAllBillets();
    }

    @Override
    public void updateBillet(Billet billet) throws SQLException {

        billetDAO.updateBillet(billet);
    }

    @Override
    public void deleteBillet(UUID id) throws SQLException {
        billetDAO.deleteBillet(id);
    }
@Override
    public void searchTickets(String startPoint, String destination, LocalDate startDate) {
        try {

            List<Billet> billets = billetDAO.getAllBillets();


            BilletGraph graph = new BilletGraph();
            graph.buildGraph(billets);


            List<BilletNode> path = ticketFinder.findShortestPath(startPoint, destination, startDate, graph);


            if (!path.isEmpty()) {
                System.out.println("Found path: " + path);
            } else {
                System.out.println("No path found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
