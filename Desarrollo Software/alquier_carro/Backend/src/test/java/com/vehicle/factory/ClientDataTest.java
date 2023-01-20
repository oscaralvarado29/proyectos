package com.vehicle.factory;

import com.vehicle.dto.CategoryRequest;
import com.vehicle.dto.ClientRequest;
import com.vehicle.dto.ClientResponse;
import com.vehicle.dto.ClientUpdate;

public class ClientDataTest {
    public static ClientResponse getClientResponse(){
        ClientResponse clientResponse = new ClientResponse();
         clientResponse.setEmail("correo@hotmail.com");
         clientResponse.setName("Carlos");
         clientResponse.setAge(25);
         return clientResponse;
    }
    public static ClientRequest getClientRequest(){
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setEmail("correo@hotmail.com");
        clientRequest.setName("Carlos");
        clientRequest.setAge(25);
        return clientRequest;
    }
    public static ClientUpdate getClientUpdate(){
        ClientUpdate clientUpdate = new ClientUpdate();
        clientUpdate.setIdClient(1);
        clientUpdate.setEmail("correo@hotmail.com");
        clientUpdate.setName("Carlos");
        clientUpdate.setAge(25);
        return clientUpdate;
    }
}
