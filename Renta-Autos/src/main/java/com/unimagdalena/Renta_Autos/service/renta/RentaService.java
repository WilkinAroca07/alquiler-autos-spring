package com.unimagdalena.Renta_Autos.service.renta;

import java.util.List;
import com.unimagdalena.Renta_Autos.dto.RentaDto;
import com.unimagdalena.Renta_Autos.dto.RentaToSaveDto;
import com.unimagdalena.Renta_Autos.exception.NotFoundExceptionEntity;

public interface RentaService {

    RentaDto getRentaById(Long id) throws NotFoundExceptionEntity;
    RentaDto agregarRenta(RentaToSaveDto rentaToSaveDto);
    RentaDto actualizarRenta(Long id, RentaToSaveDto rentaToSaveDto) throws NotFoundExceptionEntity;
    void eliminarRenta(Long id);
    List<RentaDto> getAllRents();

}
