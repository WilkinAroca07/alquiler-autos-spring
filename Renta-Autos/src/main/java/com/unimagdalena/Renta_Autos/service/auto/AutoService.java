package com.unimagdalena.Renta_Autos.service.auto;
import java.util.List;

import com.unimagdalena.Renta_Autos.dto.AutoDto;
import com.unimagdalena.Renta_Autos.dto.AutoToSaveDto;
import com.unimagdalena.Renta_Autos.exception.NotFoundExceptionEntity;

public interface AutoService {
    AutoDto obtenerAutoById(Long id)throws NotFoundExceptionEntity;
    AutoDto agregarAuto(AutoToSaveDto autoToSaveDto);
    AutoDto actualizarAuto(Long id, AutoToSaveDto autoToSaveDto) throws NotFoundExceptionEntity;
    void eliminarAuto(Long id);
    List<AutoDto> obtenerTodosAutos();
    
}
