package main.java.ma.EcoMove.B1.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BilletNode {
    private String depart;
    private String destination;
    private LocalDate dateDepart;
    private LocalDate dateArrive;
    private BigDecimal prixVente;

    public BilletNode(Billet billet) {
        this.depart = billet.getDepart();
        this.destination = billet.getDestination();
        this.dateDepart = billet.getDateDepart();
        this.dateArrive = billet.getDateArrive();
        this.prixVente = billet.getPrixVente();
    }

    public String getDepart() {
        return depart;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public LocalDate getDateArrive() {
        return dateArrive;
    }

    public BigDecimal getPrixVente() {
        return prixVente;
    }
}
