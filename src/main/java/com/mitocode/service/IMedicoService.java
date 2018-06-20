package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Medico;

public interface IMedicoService {

	void registrar(Medico medico);

	void modificar(Medico medico);

	void eliminar(int idMedico);

	Medico listarId(int idMedico);

	List<Medico> listar();
}
