package main.java.ma.EcoMove.B1.service;


import main.java.ma.EcoMove.B1.dao.ReservationDAO;
import main.java.ma.EcoMove.B1.entity.Reservation;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class  ReservationService {

    private final ReservationDAO reservationDAO;


    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }


    public void createReservation(UUID clientId, String statutReservation, BigDecimal prix) {
        try {
            Reservation reservation = new Reservation(clientId, statutReservation, prix);
            reservationDAO.createReservation(reservation);
            System.out.println("Reservation created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating reservation: " + e.getMessage());
        }
    }


    public Reservation getReservationById(UUID id) {
        try {
            return reservationDAO.getReservationById(id);
        } catch (SQLException e) {
            System.out.println("Error fetching reservation: " + e.getMessage());
            return null;
        }
    }


    public List<Reservation> getAllReservations() {
        try {
            return reservationDAO.getAllReservations();
        } catch (SQLException e) {
            System.out.println("Error fetching reservations: " + e.getMessage());
            return null;
        }
    }


    public void updateReservation(UUID id, UUID clientId, String statutReservation, BigDecimal prix) {
        try {
            Reservation reservation = reservationDAO.getReservationById(id);
            if (reservation != null) {
                reservation.setClientId(clientId);
                reservation.setStatutReservation(statutReservation);
                reservation.setPrix(prix);
                reservationDAO.updateReservation(reservation);
                System.out.println("Reservation updated successfully.");
            } else {
                System.out.println("Reservation with the given ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating reservation: " + e.getMessage());
        }
    }


    public void deleteReservation(UUID id) {
        try {
            reservationDAO.deleteReservation(id);
            System.out.println("Reservation deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting reservation: " + e.getMessage());
        }
    }
}
