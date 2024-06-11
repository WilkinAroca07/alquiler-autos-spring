package com.unimagdalena.Renta_Autos.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
@Table(name = "autos")
public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private String ciudad;
    private String imagenUrl;
    private Double precio;

    //rentas que tiene el auto
    @OneToMany(mappedBy = "auto",fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Renta> rentas;

    public void setId(Long id) {
        this.id = id;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setRentas(List<Renta> rentas) {
        this.rentas = rentas;
    }

    public List<Renta> getRentas() {
        return rentas;
    }
}
