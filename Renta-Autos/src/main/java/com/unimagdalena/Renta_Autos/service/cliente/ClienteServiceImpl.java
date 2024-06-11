package com.unimagdalena.Renta_Autos.service.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unimagdalena.Renta_Autos.dto.ClienteDto;
import com.unimagdalena.Renta_Autos.dto.ClienteToSaveDto;
import com.unimagdalena.Renta_Autos.dto.mapper.ClienteMapper;
import com.unimagdalena.Renta_Autos.entities.Cliente;
import com.unimagdalena.Renta_Autos.repository.ClienteRepository;
import com.unimagdalena.Renta_Autos.exception.NotAbleToDeleteException;
import com.unimagdalena.Renta_Autos.exception.NotFoundExceptionEntity;

@Service
public class ClienteServiceImpl implements ClienteService{

     private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    public ClienteDto obtenerClienteById(Long id) throws NotFoundExceptionEntity {
       Cliente cliente = clienteRepository.findById(id)
        .orElseThrow(() -> new NotFoundExceptionEntity("El cliente no pudo ser encontrado, verificar que el id si exista."));
        return ClienteMapper.instancia.entityToDto(cliente);
    }

    @Override
    public ClienteDto agregarCliente(ClienteToSaveDto clienteToSaveDto) {
        Cliente cliente = ClienteMapper.instancia.toSaveDtoToEntity(clienteToSaveDto);
        return ClienteMapper.instancia.entityToDto(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDto actualizarCliente(Long id, ClienteToSaveDto clienteDto) throws NotFoundExceptionEntity {
        Cliente userInDb = clienteRepository.findById(id)
        .orElseThrow(() -> new NotFoundExceptionEntity("El cliente no pudo ser actualizado, verificar que el id si exista."));
           userInDb.setNombre(clienteDto.nombre());
           userInDb.setApellido(clienteDto.apellido());
           userInDb.setCedula(clienteDto.cedula());
           userInDb.setDireccion(clienteDto.direccion());
           userInDb.setTelefono(clienteDto.telefono());

        Cliente clienteGuardado = clienteRepository.save(userInDb);
        
        return ClienteMapper.instancia.entityToDto(clienteGuardado);
    }

    @Override
    public void eliminarCliente(Long id) {
         Cliente clienteEliminar = clienteRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("El cliente a eliminar no pudo ser encontrado, verificar que el id si exista."));
        clienteRepository.delete(clienteEliminar);
    }

    @Override
    public List<ClienteDto> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> ClienteMapper.instancia.entityToDto(cliente))
                .toList();
    }

}
