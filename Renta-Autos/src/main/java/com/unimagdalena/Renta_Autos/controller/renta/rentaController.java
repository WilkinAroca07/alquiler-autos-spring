package com.unimagdalena.Renta_Autos.controller.renta;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unimagdalena.Renta_Autos.dto.RentaDto;
import com.unimagdalena.Renta_Autos.dto.RentaToSaveDto;
import com.unimagdalena.Renta_Autos.service.renta.RentaService;
import com.unimagdalena.exception.NotFoundExceptionEntity;

@RestController
@RequestMapping("/api/v1/renta")
public class rentaController {
    
    private final RentaService rentaService;

    public rentaController(RentaService rentaService) {
        this.rentaService = rentaService;
    }

     @GetMapping
    public ResponseEntity<List<RentaDto>> obtenerRentas(){
        List<RentaDto> rentasDto=rentaService.getAllRents();
        return ResponseEntity.ok().body(rentasDto);
    }

    @PostMapping
    public ResponseEntity<RentaDto> agregarRenta(@RequestBody RentaToSaveDto renta){
        RentaDto guardado=rentaService.agregarRenta(renta);
        return ResponseEntity.ok().body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentaDto> actualizarRentaPorId(@PathVariable Long id,@RequestBody RentaToSaveDto renta) throws NotFoundExceptionEntity{
        RentaDto actualizado= rentaService.actualizarRenta(id, renta);
        return ResponseEntity.ok().body(actualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentaDto> obtenerRentaPorId(@PathVariable Long id){
        try{
            RentaDto rentaDto=rentaService.getRentaById(id);
            return ResponseEntity.ok().body(rentaDto);
        }catch (NotFoundExceptionEntity e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarRentaPorId(@PathVariable Long id){
        rentaService.eliminarRenta(id);
        return ResponseEntity.noContent().build();
    }

}
