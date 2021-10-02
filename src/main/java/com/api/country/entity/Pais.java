package com.api.country.entity

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;

@Entity
public class Pais {
	private static final long serialVersionUID = 1L;

	@Id
	@SerializedName("numericCode")
	private Long id;

	private String nome;

	@SerializedName("alpha3Code")
	private String sigla;

	@JsonIgnore
	@Transient
	public Translations translations;

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Translations getTranslations() {
		return translations;
	}

	public void setTranslations(Translations translations) {
		this.translations = translations;
	}
}
