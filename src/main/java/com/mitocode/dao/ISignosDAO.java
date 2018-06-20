	package com.mitocode.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mitocode.model.Signos;

@Repository
public interface ISignosDAO extends JpaRepository<Signos,Integer>{
	
	@Query("from Signos s where s.fecha between  :fechaConsulta and :fechaAumentadaConsulta")
	Page<Signos> buscarFechaPageable(@Param("fechaConsulta")LocalDateTime fecha, @Param("fechaAumentadaConsulta")LocalDateTime fechaAumentada,Pageable pageable );
	
	@Query("from Signos s where s.fecha between :fechaConsulta and :fechaAumentadaConsulta")
	List<Signos> buscarFecha(@Param("fechaConsulta")LocalDateTime fecha, @Param("fechaAumentadaConsulta")LocalDateTime fechaAumentada);
}
