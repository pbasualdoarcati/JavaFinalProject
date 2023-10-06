package com.billing.finalproject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.finalproject.entity.Client;
import com.billing.finalproject.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(summary = "Get client by id", description = "Get client by id", tags = { "Client" })
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Optional<Client>> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.findClientById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get client invoices", description = "Get client invoices", tags = { "Client" })
    @GetMapping(value = "/{id}/invoices", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Optional<Client>> getClientInvoices(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findClientById(id));
    }

    @Operation(summary = "Get all clients", description = "Get all clients", tags = { "Client" })
    @GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Iterable<Client>> getClients() {
        return ResponseEntity.ok(clientService.findAll());

    }

    @Operation(summary = "Save client", description = "Save client, the id is autogenerated", tags = { "Client" })
    @PostMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Client> save(@RequestBody Client client) {
        try {
            Client clientSaved = clientService.save(client);
            return ResponseEntity.ok(clientSaved);
        } catch (Exception e) {
            System.out.println("client: " + client);
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
