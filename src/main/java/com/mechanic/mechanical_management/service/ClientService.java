package com.mechanic.mechanical_management.service;

import com.mechanic.mechanical_management.dto.client.CreateClientRequest;
import com.mechanic.mechanical_management.model.entities.Client;
import com.mechanic.mechanical_management.model.entities.User;
import com.mechanic.mechanical_management.repository.ClientRepository;
import com.mechanic.mechanical_management.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    public ClientService(ClientRepository repo, UserRepository userRepository, PasswordEncoder passwordEncoder  ) {
        this.repo = repo;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    private final ClientRepository repo;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public Client save(CreateClientRequest request)   {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);

        Client client = new Client();
        client.setName(request.getName());
        client.setCpf(request.getCpf());
        client.setPhone(request.getPhone());
        client.setEmail(request.getEmail());
        client.setAddress(request.getAddress());
        client.setUser(user);

        return repo.save(client);
    }

    public List<Client> findAll() { return repo.findAll(); }
    public Client findById(Long id) { return repo.findById(id).orElseThrow(); }
    public void delete(Long id) { repo.deleteById(id); }
}