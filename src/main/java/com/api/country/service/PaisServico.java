package com.api.country.service;

import java.util.Collection;

import com.api.country.dao.IPaisRepositorio;
import com.api.country.entity.Pais;
import com.api.country.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisServico {

	@Autowired
	private IPaisRepositorio dao;

	public boolean naoExistePorId(Long id) {
		if(id != null) {
			return !dao.existsById(id);
		} else {
			return false;
		}
	}

	public void salvar(Pais municipio) {
		dao.save(municipio);
	}

	public Pais buscarPorId(Long id) {
		return dao.findById(id).get();
	}

	public Collection<Pais> buscarTodos(String nome) {
		return dao.buscaPaises(StringUtil.removerAcentosUpperCase(nome));
	}
}
