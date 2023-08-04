package com.enderLunatic.SysGuardeiNoCorazon.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.enderLunatic.SysGuardeiNoCorazon.model.entities.Desafeto;
import com.enderLunatic.SysGuardeiNoCorazon.model.entities.Desavenca;
import com.enderLunatic.SysGuardeiNoCorazon.model.repositories.RepositoryFacade;

@CrossOrigin(origins = "*")
@RestController
public class DesavencaController {
	
	@CrossOrigin(origins = "*")
	@PostMapping("/desavenca/{desafeto_id}")
	public ResponseEntity<?> create(@RequestBody Desavenca desavenca,@PathVariable("desafeto_id") int id){
		
		Desafeto desafeto;
		try {
			desafeto=RepositoryFacade.getCurrentInstance().readDesafeto(id);
			desavenca.setDesafeto(desafeto);
			RepositoryFacade.getCurrentInstance().create(desavenca);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/desavenca")
	public void update(@RequestBody Desavenca desavenca) {
		try {
			RepositoryFacade.getCurrentInstance().update(desavenca);
		} catch(SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao alterar registro");
		}
	}
	
	@GetMapping("/desavenca/{id}")
	public ResponseEntity<Desavenca>read(@PathVariable("id") int id){
		try {
			Desavenca desavenca = RepositoryFacade.getCurrentInstance().readDesavenca(id);
			if(desavenca==null) {
				return new ResponseEntity<Desavenca>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<Desavenca>(desavenca,HttpStatus.OK);
			} 
		}catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao buscar Entidade");
		}
	}
	
	@DeleteMapping("/desavenca/{id}")
	public void delete(@PathVariable("id") int id) {
		try {
			RepositoryFacade.getCurrentInstance().deleteDesafeto(id);
		}catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao deletar");
		}
	}
	
	@GetMapping("/desavenca")
	public ResponseEntity<List<Desavenca>>readAll(){
		try {
			List<Desavenca> desavencas = new ArrayList<>();
			desavencas= RepositoryFacade.getCurrentInstance().readAllDesavencas();
			return new ResponseEntity<List<Desavenca>>(desavencas,HttpStatus.OK);
		}catch(SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao buscar lista de registros");
		}
	}
}
