package main.java.ma.EcoMove.B1.service.IService;

import main.java.ma.EcoMove.B1.entity.Client;

import java.util.Optional;

public interface IClientService {

  Optional<Client> findByEmail(String email);
  Client registerClient (String nom , String prenom , String email , String telephone);


}
