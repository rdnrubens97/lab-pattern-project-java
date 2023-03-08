package dio.labpatternprojectjava.service.impl;

import dio.labpatternprojectjava.model.Adress;
import dio.labpatternprojectjava.model.AdressRepository;
import dio.labpatternprojectjava.model.Client;
import dio.labpatternprojectjava.model.ClientRepository;
import dio.labpatternprojectjava.service.ClientService;
import dio.labpatternprojectjava.service.ViaZipcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AdressRepository adressRepository;
    @Autowired
    private ViaZipcodeService viaZipcodeService;

    @Override
    public Iterable<Client> findAll(){
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id){
        Optional<Client> client = clientRepository.findById(id);
        return client.get();
    }

    @Override
    public void insert(Client client){
        saveClientWithZipcode(client);
    }

    @Override
    public void update(Long id, Client client){
        Optional<Client> clientDb = clientRepository.findById(id);
        if (clientDb.isPresent()){
            saveClientWithZipcode(client);
        }
    }

    @Override
    public void delete(Long id){
        clientRepository.deleteById(id);
    }

    private void saveClientWithZipcode(Client client){
        String zipcode = client.getAdress().getZipCode();
        Adress adress = adressRepository.findById(zipcode).orElseGet(() ->
                            {Adress newAdress = viaZipcodeService.consultZipcode(zipcode);
                            adressRepository.save(newAdress);
                            return newAdress; });
        client.setAdress(adress);
        clientRepository.save(client);
    }

}
