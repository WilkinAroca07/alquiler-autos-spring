package com.unimagdalena.Renta_Autos.service.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unimagdalena.Renta_Autos.dto.ClienteDto;
import com.unimagdalena.Renta_Autos.dto.ClienteToSaveDto;
import com.unimagdalena.Renta_Autos.dto.mapper.ClienteMapper;
import com.unimagdalena.Renta_Autos.entities.Cliente;
import com.unimagdalena.Renta_Autos.repository.ClienteRepository;
import com.unimagdalena.exception.NotAbleToDeleteException;
import com.unimagdalena.exception.NotFoundExceptionEntity;

@Service
public class ClienteServiceImpl implements ClienteService{

     private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }


    @Override
    public ClienteDto obtenerClienteById(Long id) throws NotFoundExceptionEntity {
       Cliente cliente = clienteRepository.findById(id)
        .orElseThrow(() -> new NotFoundExceptionEntity("El cliente no pudo ser encontrado, verificar que el id si exista."));
        return clienteMapper.entityToDto(cliente);
    }

    @Override
    public ClienteDto agregarCliente(ClienteToSaveDto clienteToSaveDto) {
        Cliente cliente = clienteMapper.toSaveDtoToEntity(clienteToSaveDto);
        return clienteMapper.entityToDto(clienteRepository.save(cliente));
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
        
        return clienteMapper.entityToDto(clienteGuardado);
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
                .map(cliente -> clienteMapper.entityToDto(cliente))
                .toList();
    }

}
