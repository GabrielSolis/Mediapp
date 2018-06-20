package com.mitocode.service;

import java.util.List;

import com.mitocode.model.ConsultaExamen;

public interface IConsultaExamenService {

	void modificar(ConsultaExamen consultaExamen);

	ConsultaExamen listarId(Integer idConsulta, Integer idExamen);

	List<ConsultaExamen> listarExamenesPorConsulta(Integer idconsulta);
	
	List<ConsultaExamen> listar();

}
