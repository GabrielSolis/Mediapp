package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IPacienteDAO;
import com.mitocode.model.Paciente;
import com.mitocode.service.IPacienteService;

@Service
public class PacienteServiceImpl implements IPacienteService {

	@Autowired
	private IPacienteDAO dao;
	
	@Override
	public int registrar(Paciente paciente) {
		int rpta = 0;
		rpta = dao.save(paciente) != null ? paciente.getIdPaciente() : 0;
		return rpta > 0 ? 1 : 0;
	}

	@Override
	public int modificar(Paciente paciente) {
		int rpta = 0;
		rpta = dao.save(paciente) != null ? paciente.getIdPaciente() : 0;
		return rpta > 0 ? 1 : 0;
	}

	@Override
	public void eliminar(int idPaciente) {
		dao.delete(idPaciente);

	}

	@Override
	public Paciente listarId(int idPaciente) {
		return dao.findOne(idPaciente);
	}

	@Override
	public List<Paciente> listar() {
		/*List<Paciente> lista = new ArrayList<>();

		for (int i = 0; i < 599999; i++) {
			Paciente p = new Paciente();
			p.setIdPaciente(i);
			p.setNombres("Jaime");
			p.setApellidos("Medina");
			p.setDireccion("Lima");
			p.setDni("77777777");
			p.setTelefono("949494444");
			lista.add(p);
		}*/
		return dao.findAll();
	}

	@Override
	public Page<Paciente> listAllByPage(Pageable pageable) {
		return dao.findAll(pageable);
	}

}
