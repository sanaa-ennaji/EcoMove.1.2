package main.java.ma.EcoMove.B1.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {
    private UUID id;
    private UUID clientId;
    private LocalDateTime dateReservation;
    private String statutReservation;
    private BigDecimal prix;

    public Reservation() {
    }
    public Reservation(UUID id, UUID clientId, LocalDateTime dateReservation, String statutReservation, BigDecimal prix) {
        this.id = id;
        this.clientId = clientId;
        this.dateReservation = dateReservation;
        this.statutReservation = statutReservation;
        this.prix = prix;
    }

    public Reservation(UUID clientId, String statutReservation, BigDecimal prix) {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getStatutReservation() {
        return statutReservation;
    }

    public void setStatutReservation(String statutReservation) {
        if (statutReservation.equals("confirmee") || statutReservation.equals("annulee") || statutReservation.equals("enattente")) {
            this.statutReservation = statutReservation;
        } else {
            throw new IllegalArgumentException("Invalid statutReservation value. Must be 'confirmee', 'annulee', or 'enattente'.");
        }
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }
}
