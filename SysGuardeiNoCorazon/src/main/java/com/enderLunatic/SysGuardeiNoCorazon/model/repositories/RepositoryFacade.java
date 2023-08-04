package com.enderLunatic.SysGuardeiNoCorazon.model.repositories;

import java.sql.SQLException;
import java.util.List;

import com.enderLunatic.SysGuardeiNoCorazon.model.entities.Desafeto;
import com.enderLunatic.SysGuardeiNoCorazon.model.entities.Desavenca;


public class RepositoryFacade {
	
	private static RepositoryFacade myself = new RepositoryFacade();
	
	private GenericRepository<Desafeto, Integer> desafetoRepo = null;
	private GenericRepository<Desavenca, Integer> desavencaRepo=null;
	
	private RepositoryFacade() {
		this.desafetoRepo = new DesafetoRepository();
		this.desavencaRepo= new DesavencaRepository();
		
	}
	
	public static RepositoryFacade getCurrentInstance() {
		return myself;
	}
	
	public void create(Desafeto desafeto) throws SQLException {
		this.desafetoRepo.create(desafeto);
	}
	
	public void create(Desavenca desavenca) throws SQLException {
		this.desavencaRepo.create(desavenca);
	}
	
	public void update(Desafeto desafeto)throws SQLException {
		this.desafetoRepo.update(desafeto);
	}
	
	public void update(Desavenca desavenca)throws SQLException {
		this.desavencaRepo.update(desavenca);
	}
	
	public Desafeto readDesafeto(int id)throws SQLException {
		return this.desafetoRepo.read(id);
	}
	
	public Desavenca readDesavenca(int id)throws SQLException {
		return this.desavencaRepo.read(id);
	}
	
	public void deleteDesafeto(int id)throws SQLException {
		this.desafetoRepo.delete(id);
	}
	
	public void deleteDesavenca(int id)throws SQLException {
		this.desavencaRepo.delete(id);
	}
	
	public List<Desafeto> readAllDesafetos() throws SQLException{
		return this.desafetoRepo.readAll();
	}
	
	public List<Desavenca> readAllDesavencas() throws SQLException{
		return this.desavencaRepo.readAll();
	}
	
	//FILTROS
	
	public List<Desavenca> readDesavencaPorDesafeto(int id){
		return ((DesafetoRepository)this.desafetoRepo).readDesavencasPorDesafeto(id);
	}
	
}
