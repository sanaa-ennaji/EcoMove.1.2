package main.java.ma.EcoMove.B1.dao.Interface;

import main.java.ma.EcoMove.B1.entity.Reservation;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface IReservation {

    void createReservation(Reservation reservation) throws SQLException;
    Reservation getReservationById(UUID id) throws SQLException ;
  List<Reservation> getAllReservations() throws SQLException;
    void updateReservation(Reservation reservation) throws SQLException;
    void deleteReservation(UUID id) throws SQLException;
}
