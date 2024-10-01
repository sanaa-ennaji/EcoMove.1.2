package main.java.ma.EcoMove.B1.service.IService;

import main.java.ma.EcoMove.B1.entity.Billet;
import main.java.ma.EcoMove.B1.entity.BilletNode;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IBilletService {
    void createBillet(Billet billet) throws SQLException;
    Billet getBilletById(UUID id) throws SQLException;
    List<Billet> getAllBillets() throws SQLException;
    void updateBillet(Billet billet) throws SQLException;
    void deleteBillet(UUID id) throws SQLException;
    List<BilletNode> searchTickets(String startPoint, String destination, LocalDate startDate);
}
