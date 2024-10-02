package main.java.ma.EcoMove.B1.service.IService;

import main.java.ma.EcoMove.B1.entity.Reservation;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IReservationService {

    void createReservation(UUID id ,UUID clientId, LocalDateTime dateReservation , String statutReservation, BigDecimal prix)  throws SQLException;
    Reservation getReservationById(UUID id) ;
    List<Reservation> getAllReservations();
     void updateReservation(UUID id, UUID clientId, String statutReservation, BigDecimal prix) ;
    void deleteReservation(UUID id) ;
}
