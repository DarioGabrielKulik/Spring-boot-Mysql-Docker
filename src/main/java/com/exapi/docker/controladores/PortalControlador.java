package com.exapi.docker.controladores;



import com.exapi.docker.modelos.Cliente;
import com.exapi.docker.modelos.ClienteDto;
import com.exapi.docker.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class PortalControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/findall")
    public ResponseEntity<?> todos(){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(clienteServicio.buscarTodos());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/findid/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(clienteServicio.buscarCliente(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
        try {
            clienteServicio.crear(clienteDto);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/edit")
    private ResponseEntity<?> edit(@RequestBody Cliente cliente) {
        try {
            clienteServicio.editar(cliente);
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            clienteServicio.eliminar(id);
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
