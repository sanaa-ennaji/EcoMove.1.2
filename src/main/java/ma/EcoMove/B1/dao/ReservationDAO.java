package main.java.ma.EcoMove.B1.dao;

import main.java.ma.EcoMove.B1.dao.Interface.IReservation;
import main.java.ma.EcoMove.B1.entity.Reservation;
import main.java.ma.EcoMove.B1.util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservationDAO implements IReservation {

    private final Connection connection;


    public ReservationDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

@Override
    public void createReservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO reservations (id, client_id, dateReservation, statutReservation, prix) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, reservation.getId());
            stmt.setObject(2, reservation.getClientId());
            stmt.setTimestamp(3, Timestamp.valueOf(reservation.getDateReservation()));
            stmt.setString(4, reservation.getStatutReservation());
            stmt.setBigDecimal(5, reservation.getPrix());
            stmt.executeUpdate();
        }
    }
@Override
    public Reservation getReservationById(UUID id) throws SQLException {
        String sql = "SELECT * FROM reservations WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToReservation(rs);
            }
        }
        return null;
    }
    @Override
    public List<Reservation> getAllReservations() throws SQLException {
        String sql = "SELECT * FROM reservations";
        List<Reservation> reservations = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                reservations.add(mapResultSetToReservation(rs));
            }
        }
        return reservations;
    }
@Override
    public void updateReservation(Reservation reservation) throws SQLException {
        String sql = "UPDATE reservations SET client_id = ?, dateReservation = ?, statutReservation = ?, prix = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, reservation.getClientId());
            stmt.setTimestamp(2, Timestamp.valueOf(reservation.getDateReservation()));
            stmt.setString(3, reservation.getStatutReservation());
            stmt.setBigDecimal(4, reservation.getPrix());
            stmt.setObject(5, reservation.getId());
            stmt.executeUpdate();
        }
    }

@Override
    public void deleteReservation(UUID id) throws SQLException {
        String sql = "DELETE FROM reservations WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
    }


    private Reservation mapResultSetToReservation(ResultSet rs) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        UUID clientId = (UUID) rs.getObject("client_id");
        Timestamp timestamp = rs.getTimestamp("dateReservation");
        String statutReservation = rs.getString("statutReservation");
        BigDecimal prix = rs.getBigDecimal("prix");

        return new Reservation(id, clientId, timestamp.toLocalDateTime(), statutReservation, prix);
    }
}

