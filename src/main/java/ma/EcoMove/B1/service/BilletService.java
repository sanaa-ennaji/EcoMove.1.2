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
        System.out.println("Creating Billet with details:");
        System.out.println("Arrival Date: " + billet.getDateArrive());

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
    public List<BilletNode> searchTickets(String startPoint, String destination, LocalDate startDate) {
        try {
            List<Billet> billets = billetDAO.getAllBillets();

            BilletGraph graph = new BilletGraph();
            graph.buildGraph(billets);

            List<BilletNode> path = ticketFinder.findShortestPath(startPoint, destination, startDate, graph);

            if (!path.isEmpty()) {
                System.out.println("Tickets found for your journey:");
                for (BilletNode billetNode : path) {
                    System.out.println(billetNode);
                }
            } else {
                System.out.println("No tickets found for the provided route.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
