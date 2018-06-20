package com.mitocode.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mitocode.model.Archivo;
import com.mitocode.model.Consulta;
import com.mitocode.service.IArchivoService;
import com.mitocode.service.IConsultaService;
import com.mitocode.util.ConsultaListaExamen;
import com.mitocode.util.ConsultaResumen;
import com.mitocode.util.FiltroConsulta;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

	@Autowired
	private IConsultaService service;

	@Autowired
	private IArchivoService serviceArchivo;
	
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Consulta>> listar() {
		List<Consulta> consultas = new ArrayList<>();
		try {
			consultas = service.listar();
		} catch (Exception e) {
			return new ResponseEntity<List<Consulta>>(consultas, HttpStatus.OK);
		}

		return new ResponseEntity<List<Consulta>>(consultas, HttpStatus.OK);
	}

	@PostMapping(value = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Consulta> registrar(@RequestBody ConsultaListaExamen consulta) {
		Consulta c = new Consulta();
		try {
			c = service.registrar(consulta);
		} catch (Exception e) {
			return new ResponseEntity<Consulta>(c, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Consulta>(c, HttpStatus.OK);
	}

	@PostMapping(value = "/buscar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Consulta>> buscar(@RequestBody FiltroConsulta filtro) {
		List<Consulta> consultas = new ArrayList<>();

		if (filtro != null) {
			if (filtro.getFechaConsulta() != null) {
				consultas = service.buscarfecha(filtro);
			} else {
				consultas = service.buscar(filtro);
			}
		}

		return new ResponseEntity<List<Consulta>>(consultas, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listarResumen", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ConsultaResumen>> listarResumen() {
		List<ConsultaResumen> consultas = new ArrayList<>();
		try {			
			consultas = service.listarResumen();	
		}catch(Exception e) {
			return new ResponseEntity<List<ConsultaResumen>>(consultas, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return new ResponseEntity<List<ConsultaResumen>>(consultas, HttpStatus.OK);
	}
	
	@GetMapping(value = "/generarReporte", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generarReporte() {
		byte[] data = null;
		try {
			data = service.generarReporte();
		} catch (Exception e) {
			return new ResponseEntity<byte[]>(data, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
	
	@PostMapping(value = "/guardarArchivo")
	public ResponseEntity<Integer> guardarArchivo(@RequestParam("file") MultipartFile file) throws IOException {
		
		int rpta = 0;
		Archivo ar = new Archivo();
		ar.setValue(file.getBytes());
		rpta = serviceArchivo.guardar(ar); 

		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
	
	@GetMapping(value = "/leerArchivo/{idArchivo}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> leerArchivo(@PathVariable("idArchivo") Integer idArchivo) throws IOException {
				
		byte[] arr = serviceArchivo.leerArchivo(idArchivo); 

		return new ResponseEntity<byte[]>(arr, HttpStatus.OK);
	}

}
