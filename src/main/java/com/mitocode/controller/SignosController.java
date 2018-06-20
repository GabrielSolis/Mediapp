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

import com.mitocode.model.Signos;
import com.mitocode.service.ISignosService;

@RestController
@RequestMapping("/signos")
public class SignosController {
	
	@Autowired
	private ISignosService service;
	
	@PostMapping(value="/registrar",consumes=MediaType.APPLICATION_JSON_VALUE,produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> registrar(@RequestBody Signos signos)throws Exception{
		int resultado = 0;
		try {
			
			service.registrar(signos);
			resultado = 1;
		}catch(Exception e) {
			throw e;
		}
		return new ResponseEntity<Integer>(resultado,HttpStatus.OK);
		
	}
	
	@PutMapping(value="/actualizar",consumes=MediaType.APPLICATION_JSON_VALUE,produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> actualizar(@RequestBody Signos signos)throws Exception{
		int resultado = 0;
		try {
			service.modificar(signos);
			resultado = 1;
		}catch(Exception e) {
			throw e;
		}
		return new ResponseEntity<Integer>(resultado,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/listar/{codigoSignos}",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Signos> obtenerSignoPorId(@PathVariable("codigoSignos") int codigoSignos) throws Exception{
		Signos signos = new Signos();
		try {
			signos = service.consultaSignos(codigoSignos);
		}catch(Exception e) {
			throw e;
		}
		return new ResponseEntity<Signos>(signos,HttpStatus.OK);
	}
	
	@GetMapping(value="listar/",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Signos>> obtenerSignos()throws Exception{
		List<Signos> listaSignos = new ArrayList<>();
		try {
			listaSignos = service.listarSignos();
		}catch(Exception e) {
			throw e;
		}
		return new ResponseEntity<List<Signos>>(listaSignos,HttpStatus.OK);
	}
	
	@GetMapping(value="listarPageable/fecha/{fecha}/",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Signos>> listarSignosPageable(Pageable pageable,@PathVariable("fecha") String fecha)throws Exception{
		Page<Signos> signos = null;
		try {
			signos = service.buscarPorFechaPageable(fecha,pageable);
		}catch(Exception e) {
			
			return new ResponseEntity<Page<Signos>>(signos, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		return new ResponseEntity<Page<Signos>>(signos, HttpStatus.OK);
	}
	
	@DeleteMapping(value="eliminar/{codigoSignos}",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> eliminarSignos(@PathVariable("codigoSignos") int  codigoSignos) throws Exception{
		int respuesta =0;
		try {
			service.eliminar(codigoSignos);
			respuesta = 1;
		}catch(Exception e) {
			throw e;
		}
		return new ResponseEntity<Integer>(respuesta,HttpStatus.OK);
	}
	

	
	

	
	
	
}
