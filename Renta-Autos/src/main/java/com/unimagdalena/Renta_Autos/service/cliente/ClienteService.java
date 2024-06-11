package com.unimagdalena.Renta_Autos.service.cliente;

import java.util.List;

import com.unimagdalena.Renta_Autos.dto.ClienteDto;
import com.unimagdalena.Renta_Autos.dto.ClienteToSaveDto;
import com.unimagdalena.Renta_Autos.exception.NotFoundExceptionEntity;

public interface ClienteService {
    ClienteDto obtenerClienteById(Long id)throws NotFoundExceptionEntity;
    ClienteDto agregarCliente(ClienteToSaveDto clienteToSaveDto);
    ClienteDto actualizarCliente(Long id, ClienteToSaveDto clienteToSaveDto) throws NotFoundExceptionEntity;
    void eliminarCliente(Long id);
    List<ClienteDto> obtenerTodosLosClientes();
}
