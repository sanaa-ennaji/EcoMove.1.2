package main.java.ma.EcoMove.B1.service;

import main.java.ma.EcoMove.B1.dao.Interface.IBillet;
import main.java.ma.EcoMove.B1.entity.Billet;
import main.java.ma.EcoMove.B1.service.IService.IBilletService;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BilletService implements IBilletService {
    private final IBillet billetDAO;

    public BilletService(IBillet billetDAO) {
        this.billetDAO = billetDAO;
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
}
