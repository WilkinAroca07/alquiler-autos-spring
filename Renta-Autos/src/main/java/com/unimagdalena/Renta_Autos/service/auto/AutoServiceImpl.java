package com.unimagdalena.Renta_Autos.service.auto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unimagdalena.Renta_Autos.dto.AutoDto;
import com.unimagdalena.Renta_Autos.dto.AutoToSaveDto;
import com.unimagdalena.Renta_Autos.dto.mapper.AutoMapper;
import com.unimagdalena.Renta_Autos.entities.Auto;
import com.unimagdalena.Renta_Autos.repository.AutoRepository;
import com.unimagdalena.exception.NotAbleToDeleteException;
import com.unimagdalena.exception.NotFoundExceptionEntity;


@Service
public class AutoServiceImpl implements AutoService{
    private final AutoRepository autoRepository;
    private final AutoMapper autoMapper;

    @Autowired
    public AutoServiceImpl(AutoRepository autoRepository, AutoMapper autoMapper) {
        this.autoRepository = autoRepository;
        this.autoMapper = autoMapper;
    }

    

    @Override
    public AutoDto obtenerAutoById(Long id) throws NotFoundExceptionEntity {
        Auto auto = autoRepository.findById(id)
        .orElseThrow(() -> new NotFoundExceptionEntity("El auto no pudo ser encontrado, verificar que el id si exista."));
        return autoMapper.entityToDto(auto);
        
    }

    @Override
    public AutoDto agregarAuto(AutoToSaveDto autoToSaveDto) {
        Auto auto = autoMapper.toSaveDtoToEntity(autoToSaveDto);
        return autoMapper.entityToDto(autoRepository.save(auto));
    }

    @Override
    public AutoDto actualizarAuto(Long id, AutoToSaveDto autoDto) throws NotFoundExceptionEntity  {
        Auto autoInDb = autoRepository.findById(id)
        .orElseThrow(() -> new NotFoundExceptionEntity("El auto no pudo ser encontrado, verificar que el id si exista."));
        autoInDb.setCiudad(autoDto.ciudad());
        autoInDb.setImagenUrl(autoDto.imagenUrl());
        autoInDb.setModelo(autoDto.modelo());
        autoInDb.setPrecio(autoDto.precio());

        Auto autoGuardado = autoRepository.save(autoInDb);
        
        return autoMapper.entityToDto(autoGuardado);
    }

    @Override
    public void eliminarAuto(Long id) {
         Auto autoEliminar = autoRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("El auto no pudo ser encontrado, verificar que el id si exista."));
        autoRepository.delete(autoEliminar);
    }

    @Override
    public List<AutoDto> obtenerTodosAutos() {
        List<Auto> autos = autoRepository.findAll();
        return autos.stream()
                .map(auto -> autoMapper.entityToDto(auto))
                .toList();
        
    }

}
