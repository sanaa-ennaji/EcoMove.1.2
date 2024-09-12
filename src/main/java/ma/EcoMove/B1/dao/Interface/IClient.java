package main.java.ma.EcoMove.B1.dao.Interface;

import main.java.ma.EcoMove.B1.entity.Client;

import java.util.Optional;

public interface IClient {

    Optional<Client> findByEmail(String email);
    void save (Client client);





}
