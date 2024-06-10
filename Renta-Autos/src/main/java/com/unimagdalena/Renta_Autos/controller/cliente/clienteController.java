package com.unimagdalena.Renta_Autos.controller.cliente;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unimagdalena.Renta_Autos.dto.ClienteDto;
import com.unimagdalena.Renta_Autos.dto.ClienteToSaveDto;
import com.unimagdalena.Renta_Autos.service.cliente.ClienteService;
import com.unimagdalena.exception.NotFoundExceptionEntity;

@RestController
@RequestMapping("/api/v1/clientes")
public class clienteController {

    private final ClienteService clienteService;

    public clienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

     @GetMapping
    public ResponseEntity<List<ClienteDto>> obtenerClientes(){
        List<ClienteDto> clientesDto=clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok().body(clientesDto);
    }

    @PostMapping
    public ResponseEntity<ClienteDto> agregarCliente(@RequestBody ClienteToSaveDto cliente){
        ClienteDto guardado=clienteService.agregarCliente(cliente);
        return ResponseEntity.ok().body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> actualizarClientePorId(@PathVariable Long id,@RequestBody ClienteToSaveDto cliente) throws NotFoundExceptionEntity{
        ClienteDto actualizado= clienteService.actualizarCliente(id, cliente);
        return ResponseEntity.ok().body(actualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerClientePorId(@PathVariable Long id){
        try{
            ClienteDto clienteDto=clienteService.obtenerClienteById(id);
            return ResponseEntity.ok().body(clienteDto);
        }catch (NotFoundExceptionEntity e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarClientePorId(@PathVariable Long id){
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }




}
