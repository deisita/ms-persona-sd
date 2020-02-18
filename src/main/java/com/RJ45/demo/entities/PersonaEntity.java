package com.RJ45.demo.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@Entity
@Table(name="persona", schema = "deisy")
public class PersonaEntity implements Serializable {

    @EmbeddedId
    private TipoNumDocPK tipoNumDocPK;

    //@Id
    private Integer id;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotEmpty

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

  /**  @Column(name="telefono")
    private String telefono;

    @Column(name="celular")
    private String celular="00000";*/

    public PersonaEntity() {
        super();
    }
    public PersonaEntity(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, Date fechaNacimiento, String genero ) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String primerApellido) {
        this.segundoApellido = primerApellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }



}

@Embeddable
class TipoNumDocPK implements Serializable {
    private String numeroDocumento;
    private int tipoDocumento;

    public String getNumeroDocumento() {
        return numeroDocumento;
    }
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TipoNumDocPK(String numeroDocumento, int tipoDocumento) {
        super();
        this.numeroDocumento = numeroDocumento;
        this.tipoDocumento = tipoDocumento;
    }

}