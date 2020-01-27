package com.RJ45.demo.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="persona", schema = "deisy")
public class PersonaEntity {
    @Id
    private Integer id;

    @Column(name="primer_nombre")
    private String primerNombre;
    @Column(name="segundo_nombre")
    private String segundoNombre;
    @Column(name="primer_apellido")
    private String primerApellido;
    @Column(name="segundo_apellido")
    private String segundoApellido;
    @Column(name="fecha_nacimiento")
    private Date fechaNacimiento;
    @Column(name="genero")
    private String genero;
}
