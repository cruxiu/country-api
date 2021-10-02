package com.api.country.dao;

import java.util.Collection;

import com.api.country.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IPaisRepositorio extends JpaRepository<Pais, Long> {

	@Query(value = "SELECT * FROM pais m WHERE upper(unaccent(m.nome)) LIKE %:nome% ORDER BY m.nome ASC", nativeQuery = true)
	Collection<Pais> buscaPaises(@Param("nome") String nome);

}
