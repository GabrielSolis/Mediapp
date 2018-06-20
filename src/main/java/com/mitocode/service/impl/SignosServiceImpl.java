package com.mitocode.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.dao.ISignosDAO;
import com.mitocode.model.Signos;
import com.mitocode.service.ISignosService;
@Service
public class SignosServiceImpl implements ISignosService{
	@Autowired
	ISignosDAO dao;
	@Override
	public void registrar(Signos signos) {
		dao.save(signos);
		
	}

	@Override
	public void modificar(Signos signos) {
		// TODO Auto-generated method stub
		dao.save(signos);
	}

	@Override
	public void eliminar(int codigoSignos) {
		// TODO Auto-generated method stub
		dao.delete(codigoSignos);
	}

	@Override
	public Signos consultaSignos(int codigoSignos) {
		// TODO Auto-generated method stub
		return dao.findOne(codigoSignos);
	}

	@Override
	public List<Signos> listarSignos() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Page<Signos> buscarPorFechaPageable(String fecha, Pageable pageable) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime fechaActual = LocalDateTime.parse(fecha, formatter);
		LocalDateTime fechaAumentada;
		fechaAumentada = fechaActual.plusDays(1);
		return dao.buscarFechaPageable(fechaActual,fechaAumentada,pageable);
	}
	
	


	
}
