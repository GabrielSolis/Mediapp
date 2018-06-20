package com.mitocode.service.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitocode.dao.IConsultaDAO;
import com.mitocode.dao.IConsultaExamenDAO;
import com.mitocode.model.Consulta;
import com.mitocode.service.IConsultaService;
import com.mitocode.util.ConsultaListaExamen;
import com.mitocode.util.ConsultaResumen;
import com.mitocode.util.FiltroConsulta;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

//@Transactional
@Service
public class ConsultaServiceImpl implements IConsultaService{
	
	@Autowired
	private IConsultaDAO dao;
	
	@Autowired
	private IConsultaExamenDAO ceDAO;

	@Transactional
	@Override
	public Consulta registrar(ConsultaListaExamen dto) {
		Consulta cons = new Consulta();
		try {
			dto.getConsulta().getDetalleConsulta().forEach(d -> d.setConsulta(dto.getConsulta()));
			cons = dao.save(dto.getConsulta());
			/*for(Examen ex : dto.getLstExamen()) {
				ceDAO.registrar(idConsulta, idExamen)
			}*/			
			dto.getLstExamen().forEach(e -> ceDAO.registrar(dto.getConsulta().getIdConsulta(), e.getIdExamen()));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return cons;
	}

	@Override
	public void modificar(Consulta consulta) {
		dao.save(consulta);
		
	}

	@Override
	public void eliminar(int idConsulta) {
		dao.delete(idConsulta);		
	}

	@Override
	public Consulta listarId(int idConsulta) {
		return dao.findOne(idConsulta);
	}

	@Override
	public List<Consulta> listar() {
		return dao.findAll();
	}
	
	@Override
	public List<Consulta> buscar(FiltroConsulta filtro) {
		return dao.buscar(filtro.getDni(), filtro.getNombreCompleto());
	}

	@Override
	public List<Consulta> buscarfecha(FiltroConsulta filtro) {
		LocalDateTime fechaSgte = filtro.getFechaConsulta().plusDays(1);
		return dao.buscarPorFecha(filtro.getFechaConsulta(), fechaSgte);
	}

	@Override
	public List<ConsultaResumen> listarResumen() {
		List<ConsultaResumen> consulta = new ArrayList<>();
		dao.listarResumen(3).forEach( x -> {
			ConsultaResumen cr = new ConsultaResumen();
			cr.setCantidad(Integer.parseInt(String.valueOf(x[0])));
			cr.setFecha(String.valueOf(x[1]));
			consulta.add(cr);
		});
		return consulta;
	}

	@Override
	public byte[] generarReporte() throws Exception {
		File file = new ClassPathResource("/reports/consultas.jasper").getFile();

		JasperPrint print = JasperFillManager.fillReport(file.getPath(), null, new JRBeanCollectionDataSource(this.listarResumen()));
		return JasperExportManager.exportReportToPdf(print);	
	}

}
