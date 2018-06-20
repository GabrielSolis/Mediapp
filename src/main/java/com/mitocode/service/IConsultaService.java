package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Consulta;
import com.mitocode.util.ConsultaListaExamen;
import com.mitocode.util.ConsultaResumen;
import com.mitocode.util.FiltroConsulta;

public interface IConsultaService {

	Consulta registrar(ConsultaListaExamen consulta);

	void modificar(Consulta consulta);

	void eliminar(int idConsulta);

	Consulta listarId(int idConsulta);

	List<Consulta> listar();
	
	List<Consulta> buscar(FiltroConsulta filtro);

	List<Consulta> buscarfecha(FiltroConsulta filtro);
	
	List<ConsultaResumen> listarResumen();
	
	byte[] generarReporte() throws Exception ;
}
