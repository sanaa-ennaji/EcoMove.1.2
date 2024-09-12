package main.java.ma.EcoMove.B1.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Client {

    private UUID id;
    private String nom ;
    private String prenom ;
    private String email ;
    private String telephone ;
    private LocalDate dateInscription ;
    private List<Reservation> reservations ;


    public Client() {

    }

    public Client ( UUID id , String nom , String prenom , String email , String telephone , LocalDate dateInscription , List<Reservation> reservations ) {
        this.id = id ;
        this.nom = nom ;
        this.prenom = prenom ;
        this.email = email ;
        this.telephone = telephone ;
        this.dateInscription = dateInscription ;
//        this.reservation = new ArrayList<Reservation>();
        this.reservations = reservations;

    }

    public UUID getId () {return id ;}
    public String getNom () {return nom ;}
    public String getPrenom () {return prenom ;}
    public String getEmail () {return email ;}
    public String getTelephone () {return telephone ;}
    public LocalDate getDateInscription () {return dateInscription ;}
    public List<Reservation> getReservations () {return reservations ;}
    public void setId (UUID id ) {this.id = id;}
    public void setNom (String nom) {this.nom = nom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public void setEmail (String email) {this.email = email;}
    public void setTelephone (String telephone) {this.telephone = telephone;}
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations ;
    }


}
