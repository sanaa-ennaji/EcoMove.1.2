package main.java.ma.EcoMove.B1.dao;

import main.java.ma.EcoMove.B1.dao.Interface.IBillet;
import main.java.ma.EcoMove.B1.entity.Billet;
import main.java.ma.EcoMove.B1.enums.TypeTransport;
import main.java.ma.EcoMove.B1.enums.StatutBillet;
import main.java.ma.EcoMove.B1.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class BilletDAO implements IBillet {
    private final Connection connection;

    public BilletDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }



    @Override
    public void createBillet(Billet billet) throws SQLException {
        String sql = "INSERT INTO billets (id, typeTransport, prixAchat, prixVente, dateVente, statutBillet, depart, destination, dateDepart, dateArrive, contrat_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, billet.getId());
            stmt.setString(2, billet.getTypeTransport().name().toLowerCase());
            stmt.setBigDecimal(3, billet.getPrixAchat());
            stmt.setBigDecimal(4, billet.getPrixVente());
            stmt.setDate(5, java.sql.Date.valueOf(billet.getDateVente()));
            stmt.setString(6, billet.getStatutBillet().name().toLowerCase());
            stmt.setString(7, billet.getDepart());
            stmt.setString(8, billet.getDestination());
           // stmt.setDate(9, Date.valueOf(billet.getDateDepart()));
           // stmt.setDate(10, Date.valueOf(billet.getDateArrive()));
            if (billet.getDateDepart() != null) {
                stmt.setDate(9, java.sql.Date.valueOf(billet.getDateDepart()));
            } else {
                stmt.setNull(9, java.sql.Types.DATE);
            }

            if (billet.getDateArrive() != null) {
                stmt.setDate(10, java.sql.Date.valueOf(billet.getDateArrive()));
            } else {
                stmt.setNull(10, java.sql.Types.DATE);
            }
            stmt.setObject(11, billet.getContrat().getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Billet getBilletById(UUID id) throws SQLException {
        return findBilletById(id);
    }

    @Override
    public List<Billet> getAllBillets() throws SQLException {
        String sql = "SELECT * FROM billets";
        List<Billet> billets = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                billets.add(mapResultSetToBillet(rs));
            }
        }
        return billets;
    }

    @Override
    public void updateBillet(Billet billet) throws SQLException {
        Billet existingBillet = findBilletById(billet.getId());
        if (existingBillet == null) {
            throw new SQLException("Billet with ID " + billet.getId() + " not found.");
        }

        String sql = "UPDATE billets SET typeTransport = ?, prixAchat = ?, prixVente = ?, dateVente = ?, statutBillet = ?, depart = ?, destination = ?, dateDepart = ?, dateArrive = ?, contrat_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, billet.getTypeTransport().name());
            stmt.setBigDecimal(2, billet.getPrixAchat());
            stmt.setBigDecimal(3, billet.getPrixVente());
            stmt.setDate(5, java.sql.Date.valueOf(billet.getDateVente()));
            stmt.setString(6, billet.getStatutBillet().name().toLowerCase());
            stmt.setString(7, billet.getDepart());
            stmt.setString(8, billet.getDestination());
            stmt.setDate(9, java.sql.Date.valueOf(billet.getDateDepart()));
            stmt.setDate(10, java.sql.Date.valueOf(billet.getDateArrive()));
            stmt.setObject(11, billet.getContrat() != null ? billet.getContrat().getId() : null);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteBillet(UUID id) throws SQLException {
        Billet existingBillet = findBilletById(id);
        if (existingBillet == null) {
            throw new SQLException("Billet with ID " + id + " not found.");
        }

        String sql = "DELETE FROM billets WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
    }


    private Billet findBilletById(UUID id) throws SQLException {
        String sql = "SELECT * FROM billets WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToBillet(rs);
            } else {
                return null;
            }
        }
    }


    private Billet mapResultSetToBillet(ResultSet rs) throws SQLException {
        Billet billet = new Billet();
        billet.setId((UUID) rs.getObject("id"));
        billet.setTypeTransport(TypeTransport.valueOf(rs.getString("typeTransport").toUpperCase()));
        billet.setPrixAchat(rs.getBigDecimal("prixAchat"));
        billet.setPrixVente(rs.getBigDecimal("prixVente"));
        java.sql.Date sqlDateVente = rs.getDate("dateVente");
        billet.setDateVente(sqlDateVente != null ? sqlDateVente.toLocalDate() : null);

        billet.setStatutBillet(StatutBillet.valueOf(rs.getString("statutBillet").toUpperCase()));
        UUID contratId = (UUID) rs.getObject("contrat_id");
        if (contratId != null) {
            ContratDAO contratDAO = new ContratDAO();
            billet.setContrat(contratDAO.getContratById(contratId));
        }
        billet.setDepart(rs.getString("depart"));
        billet.setDestination(rs.getString("destination"));
        java.sql.Date sqlDateDepart = rs.getDate("dateDepart");
        billet.setDateDepart(sqlDateDepart != null ? sqlDateDepart.toLocalDate() : null);

        java.sql.Date sqlDateArrive = rs.getDate("dateArrive");
        billet.setDateArrive(sqlDateArrive != null ? sqlDateArrive.toLocalDate() : null);

        return billet;
    }

}
