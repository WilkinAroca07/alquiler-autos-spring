package com.unimagdalena.Renta_Autos.service.renta;

import java.util.List;

import com.unimagdalena.Renta_Autos.entities.Auto;
import com.unimagdalena.Renta_Autos.repository.AutoRepository;
import com.unimagdalena.Renta_Autos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unimagdalena.Renta_Autos.dto.RentaDto;
import com.unimagdalena.Renta_Autos.dto.RentaToSaveDto;
import com.unimagdalena.Renta_Autos.dto.mapper.RentaMapper;
import com.unimagdalena.Renta_Autos.entities.Cliente;
import com.unimagdalena.Renta_Autos.entities.Renta;

import com.unimagdalena.Renta_Autos.repository.RentaRepository;
import com.unimagdalena.exception.NotAbleToDeleteException;
import com.unimagdalena.exception.NotFoundExceptionEntity;

@Service
public class RentaServiceImpl implements RentaService{

    private final RentaRepository rentaRepository;
    private final RentaMapper rentaMapper;
    private final AutoRepository autoRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public RentaServiceImpl(RentaRepository rentaRepository, RentaMapper rentaMapper, AutoRepository autoRepository,
                            ClienteRepository clienteRepository) {
        this.rentaRepository = rentaRepository;
        this.rentaMapper = rentaMapper;
        this.autoRepository=autoRepository;
        this.clienteRepository=clienteRepository;
    }

    @Override
    public RentaDto getRentaById(Long id) throws NotFoundExceptionEntity {
         Renta renta = rentaRepository.findById(id)
        .orElseThrow(() -> new NotFoundExceptionEntity("la renta solicitada no pudo ser encontrado, verificar que el id si exista."));
        return rentaMapper.entityToDto(renta);
    }

    @Override
    public RentaDto agregarRenta(RentaToSaveDto rentaToSaveDto) {
        Renta renta = rentaMapper.toSaveDtoToEntity(rentaToSaveDto);
        Auto auto=autoRepository.findById(rentaToSaveDto.idAuto()).get();
        Cliente cliente=clienteRepository.findById(rentaToSaveDto.idCliente()).get();

        renta.setAuto(auto);
        renta.setCliente(cliente);

        Renta guardada=rentaRepository.save(renta);

        List<Renta> rentasAuto=auto.getRentas();
        rentasAuto.add(guardada);
        List<Renta> rentasCliente=cliente.getRentas();
        rentasCliente.add(guardada);

        auto.setRentas(rentasAuto);
        cliente.setRentas(rentasCliente);

        autoRepository.save(auto);
        clienteRepository.save(cliente);

        return rentaMapper.entityToDto(guardada);
    }

    @Override
    public RentaDto actualizarRenta(Long id, RentaToSaveDto rentaToSaveDto) throws NotFoundExceptionEntity {
        Renta rentaInDb = rentaRepository.findById(id)
        .orElseThrow(() -> new NotFoundExceptionEntity("La renta no pudo ser actualizado, verificar que el id si exista."));
         rentaInDb.setAuto(autoRepository.findById(rentaToSaveDto.idAuto()).get());
         rentaInDb.setCliente(clienteRepository.findById(rentaToSaveDto.idCliente()).get());
         Renta rentaNueva=RentaMapper.instancia.toSaveDtoToEntity(rentaToSaveDto);
         rentaInDb.setFechaFinal(rentaNueva.getFechaFinal());
         rentaInDb.setFechaInicio(rentaNueva.getFechaInicio());
         rentaInDb.setTotal(rentaToSaveDto.total());
        
        Renta rentaGuardada = rentaRepository.save(rentaInDb);
        
        return rentaMapper.entityToDto(rentaGuardada);
    }

    @Override
    public void eliminarRenta(Long id) {
         Renta rentaEliminar = rentaRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("La renta a eliminar no pudo ser encontrado, verificar que el id si exista."));
        rentaRepository.delete(rentaEliminar);
    }

    @Override
    public List<RentaDto> getAllRents() {
        List<Renta> rentas = rentaRepository.findAll();
        return rentas.stream()
                .map(renta -> rentaMapper.entityToDto(renta))
                .toList();
    }

}
