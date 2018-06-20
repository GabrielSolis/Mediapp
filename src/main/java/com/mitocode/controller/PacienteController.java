package com.mitocode.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Paciente;
import com.mitocode.service.IPacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
	
	@Autowired
	private IPacienteService service;
	
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Paciente>> listar(){
		List<Paciente> pacientes = new ArrayList<>();
		try {
			pacientes = service.listar();
		}catch(Exception e) {
			return new ResponseEntity<List<Paciente>>(pacientes, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Paciente>>(pacientes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listarPageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Paciente>> listarPorFecha(Pageable pageable){
		Page<Paciente> pacientes = null;
		try {
			pacientes = service.listAllByPage(pageable);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println(pacientes.getContent());
			return new ResponseEntity<Page<Paciente>>(pacientes, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Page<Paciente>>(pacientes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Paciente> listarId(@PathVariable("id") Integer id){
		Paciente paciente = new Paciente();
		try {
			paciente = service.listarId(id);
		}catch(Exception e) {
			return new ResponseEntity<Paciente>(paciente, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
	}
	
	@PostMapping(value = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> registrar(@RequestBody Paciente paciente) {
		int rpta = 0;
		try {
			rpta = service.registrar(paciente);			
		} catch (Exception e) {
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
	
	@PutMapping(value = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> actualizar(@RequestBody Paciente paciente) {
		int rpta = 0;
		try {
			rpta = service.modificar(paciente);			
		} catch (Exception e) {
			return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> eliminar(@PathVariable Integer id) {
		int resultado = 0;
		try {
			service.eliminar(id);
			resultado = 1;
		} catch (Exception e) {
			resultado = 0;
		}

		return new ResponseEntity<Integer>(resultado, HttpStatus.OK);
	}

}
