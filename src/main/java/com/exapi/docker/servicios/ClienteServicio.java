package com.exapi.docker.servicios;

import com.exapi.docker.modelos.Cliente;
import com.exapi.docker.modelos.ClienteDto;
import com.exapi.docker.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public void crear(ClienteDto clienteDto){
        Cliente cliente = new Cliente();
        cliente.setName(clienteDto.getName());
        cliente.setLast_name(clienteDto.getLast_name());
        clienteRepositorio.save(cliente);
    }

    public Cliente buscarCliente(Long id ){
        return clienteRepositorio.findById(id).get();
    }

    public List<Cliente> buscarTodos(){
        return clienteRepositorio.findAll();
    }

    public void editar(Cliente cliente){
        Optional<Cliente> response = clienteRepositorio.findById(cliente.getId());
        Cliente cliente1;
        if(response.isPresent()){
            cliente1 = response.get();
            cliente1.setName(cliente.getName());
            cliente1.setLast_name(cliente.getLast_name());
            clienteRepositorio.save(cliente1);
        }
    }

    public void eliminar(Long id){
        clienteRepositorio.deleteById(id);
    }
}
