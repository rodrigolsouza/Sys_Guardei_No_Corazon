package com.enderLunatic.SysGuardeiNoCorazon.model.entities;



public class Desavenca {
	private int id;
	private String tipo;
	private boolean violenciaFisica;
	private String local;
	private String descricao;
	private String data;
	private String hora;
	private Desafeto desafeto;
	
	
	
	public Desavenca() {
		super();
	}
	public Desavenca(int id, String tipo, boolean violenciaFisica, String local,String descricao, String data, String hora,Desafeto desafeto) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.violenciaFisica = violenciaFisica;
		this.local = local;
		this.descricao = descricao;
		this.data = data;
		this.hora = hora;
		this.desafeto=desafeto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isViolenciaFisica() {
		return violenciaFisica;
	}
	public void setViolenciaFisica(boolean violenciaFisica) {
		this.violenciaFisica = violenciaFisica;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data=data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Desafeto getDesafeto() {
		return desafeto;
	}
	public void setDesafeto(Desafeto desafeto) {
		this.desafeto = desafeto;
	}
	
}
