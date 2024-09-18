package main.java.ma.EcoMove.B1.entity;

import main.java.ma.EcoMove.B1.enums.TypeTransport;
import main.java.ma.EcoMove.B1.enums.StatutBillet;
import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Billet {
    private UUID id;
    private TypeTransport typeTransport;
    private BigDecimal prixAchat;
    private BigDecimal prixVente;
    private LocalDate dateVente;
    private StatutBillet statutBillet;
    private String depart ;
    private String destination;
    private  LocalDate dateDepart ;
    private  LocalDate dateArrive ;
    private Contrat contrat;


    public Billet() {}


    public Billet(UUID id, TypeTransport typeTransport, BigDecimal prixAchat, BigDecimal prixVente,
                  LocalDate dateVente, StatutBillet statutBillet, String depart , String destination , LocalDate dateDepart , LocalDate dateArrive ,  Contrat contrat) {
        this.id = id;
        this.typeTransport = typeTransport;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.dateVente = dateVente;
        this.statutBillet = statutBillet;
        this.depart = depart ;
        this.destination = destination;
        this.dateDepart = dateDepart ;
        this.dateArrive = dateArrive ;
        this.contrat = contrat;

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TypeTransport getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(TypeTransport typeTransport) {
        this.typeTransport = typeTransport;
    }

    public BigDecimal getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(BigDecimal prixAchat) {
        this.prixAchat = prixAchat;
    }

    public BigDecimal getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(BigDecimal prixVente) {
        this.prixVente = prixVente;
    }

    public LocalDate getDateVente() {
        return dateVente;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public LocalDate getDateArrive() {
        return dateArrive;
    }
    public void setDateArrive(LocalDate dateArrive) {
        this.dateDepart = dateArrive;
    }

    public void setDateVente(LocalDate dateVente) {
        this.dateVente = dateVente;
    }

    public StatutBillet getStatutBillet() {
        return statutBillet;
    }

    public void setStatutBillet(StatutBillet statutBillet) {
        this.statutBillet = statutBillet;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }


}
