package com.api.country.controller;

import java.util.Collection;

import com.api.country.service.PaisServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paises")
public class PaisRecurso {

	@Autowired
	private PaisServico paisServico;

	/**
	 * Retorna os todos os paises.
	 **/
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Pais>> listarTodos(@RequestParam(defaultValue = "") String nome) {
		return ResponseEntity.ok().body(paisServico.buscarTodos(nome));
	}
	
	/**
	 * Retorna os paises por id passada como parâmetro.
	 **/
	@GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Pais> listarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(paisServico.buscarPorId(id));
	}
}
