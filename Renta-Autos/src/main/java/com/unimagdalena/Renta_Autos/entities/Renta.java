package com.unimagdalena.Renta_Autos.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "rentas")
public class Renta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private LocalDate fechaInicio;
    @Temporal(TemporalType.DATE)
    private LocalDate fechaFinal;
    private Double total;

    //auto de la renta
    @ManyToOne
    @JoinColumn(name = "auto_id")
    @JsonBackReference
    private Auto auto;

    //cliente de la renta
    @ManyToOne
    @JoinColumn(name = "renta_id")
    @JsonBackReference
    private Cliente cliente;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public Double getTotal() {
        return total;
    }

    public Auto getAuto() {
        return auto;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
