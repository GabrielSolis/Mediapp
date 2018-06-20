package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IConsultaExamenDAO;
import com.mitocode.model.ConsultaExamen;
import com.mitocode.service.IConsultaExamenService;

@Service
public class ConsultaExamenServiceImpl implements IConsultaExamenService {

	@Autowired
	private IConsultaExamenDAO dao;

	@Override
	public void modificar(ConsultaExamen consultaExamen) {
		dao.save(consultaExamen);
	}

	@Override
	public List<ConsultaExamen> listar() {
		return dao.findAll();
	}

	@Override
	public ConsultaExamen listarId(Integer idConsulta, Integer idExamen) {
		return dao.listarId(idConsulta, idExamen);
	}

	@Override
	public List<ConsultaExamen> listarExamenesPorConsulta(Integer idconsulta) {
		return dao.listarExamenesPorConsulta(idconsulta);
	}

}
