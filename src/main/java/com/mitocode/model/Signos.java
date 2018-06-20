package com.mitocode.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "signos")
public class Signos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigoSignos;
	
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime fecha;
	
	@Column(name="temperatura" ,nullable=false)
	private Double temperatura;
	
	@Column(name="pulso",nullable=false)
	private Double pulso;
	
	@Column(name="ritmo_respiratorio",nullable=false)
	private Double ritmoRespiratorio;
	
	@ManyToOne
	@JoinColumn(name="codigo_paciente",nullable=false)
	private Paciente paciente;

	public int getCodigoSignos() {
		return codigoSignos;
	}

	public void setCodigoSignos(int codigoSignos) {
		this.codigoSignos = codigoSignos;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public Double getPulso() {
		return pulso;
	}

	public void setPulso(Double pulso) {
		this.pulso = pulso;
	}

	public Double getRitmoRespiratorio() {
		return ritmoRespiratorio;
	}

	public void setRitmoCardiaco(Double ritmoRespiratorio) {
		this.ritmoRespiratorio = ritmoRespiratorio;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	
}
