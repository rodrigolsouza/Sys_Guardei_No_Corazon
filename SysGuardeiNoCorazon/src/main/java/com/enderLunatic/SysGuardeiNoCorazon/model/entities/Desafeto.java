package com.enderLunatic.SysGuardeiNoCorazon.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Desafeto {

	private int id;
	private String nome;
	private String faixaEtaria;
	private float altura;
	private float peso;
	private String grauConvivio;
	private String descricao;
	private List<Desavenca> desavencas;
	
	
	
	public Desafeto() {
		super();
		this.desavencas=new ArrayList<>();
	}
	public Desafeto(int id, String nome, String faixaEtaria, float altura, float peso, String grauConvivio,
			String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.faixaEtaria = faixaEtaria;
		this.altura = altura;
		this.peso = peso;
		this.grauConvivio = grauConvivio;
		this.descricao = descricao;
		this.desavencas = new ArrayList<>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFaixaEtaria() {
		return faixaEtaria;
	}
	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}
	public float getAltura() {
		return altura;
	}
	public void setAltura(float altura) {
		this.altura = altura;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public String getGrauConvivio() {
		return grauConvivio;
	}
	public void setGrauConvivio(String grauConvivio) {
		this.grauConvivio = grauConvivio;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Desavenca> getDesavencas() {
		return desavencas;
	}
	public void setDesavencas( List<Desavenca> desavencas) {
		this.desavencas= desavencas;
	}
	
	
	
	
	
}
