package com.unimagdalena.Renta_Autos.controller.auto;

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

import com.unimagdalena.Renta_Autos.dto.AutoDto;
import com.unimagdalena.Renta_Autos.dto.AutoToSaveDto;


import com.unimagdalena.Renta_Autos.service.auto.AutoService;
import com.unimagdalena.Renta_Autos.exception.NotFoundExceptionEntity;


@RestController
@RequestMapping("/api/v1/auto")
public class autoController {
    private final AutoService autoService;

    public autoController(AutoService autoService) {
        this.autoService = autoService;
    }
    
      @GetMapping
    public ResponseEntity<List<AutoDto>> obtenerAutos(){
        List<AutoDto> autosDto =autoService.obtenerTodosAutos();
        return ResponseEntity.ok().body(autosDto);
    }

    @PostMapping
    public ResponseEntity<AutoDto> agregarAuto(@RequestBody AutoToSaveDto auto){
        AutoDto guardado=autoService.agregarAuto(auto);
        return ResponseEntity.ok().body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutoDto> actualizarAutoPorId(@PathVariable Long id,@RequestBody AutoToSaveDto auto) throws NotFoundExceptionEntity{
        AutoDto actualizado= autoService.actualizarAuto(id, auto);
        return ResponseEntity.ok().body(actualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoDto> obtenerAutoPorId(@PathVariable Long id){
        try{
            AutoDto autoDto=autoService.obtenerAutoById(id);
            return ResponseEntity.ok().body(autoDto);
        }catch (NotFoundExceptionEntity e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarAutoPorId(@PathVariable Long id){
        autoService.eliminarAuto(id);
        return ResponseEntity.noContent().build();
    }
    
}
