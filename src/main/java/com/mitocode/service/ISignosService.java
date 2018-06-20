package com.mitocode.service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mitocode.model.Signos;

public interface ISignosService {
	void registrar(Signos signos);
	void modificar(Signos signos);
	void eliminar(int codigoSignos);
	Signos consultaSignos(int codigoSignos);
	List<Signos> listarSignos();
	Page<Signos> buscarPorFechaPageable(String fecha,Pageable pageable);
	
}
