package main.java.ma.EcoMove.B1.service;

import main.java.ma.EcoMove.B1.dao.Interface.IClient;
import main.java.ma.EcoMove.B1.entity.Client;
import main.java.ma.EcoMove.B1.service.IService.IClientService;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class ClientService  implements IClientService {
    private final IClient clientDAO ;

    public ClientService (IClient clientDAO){
        this.clientDAO = clientDAO;

    }
    @Override
    public Optional<Client> findByEmail(String email){
        return clientDAO.findByEmail(email);
    }

    public Client registerClient (String nom , String prenom, String email, String telephone , LocalDate dateInscription ){

        Client client = new Client (
                UUID.randomUUID(),
                nom,
                prenom,
                email,
                telephone,
                LocalDate.now(),
                null



        );
        clientDAO.save(client);
                return client;

    }


}
