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
import com.enderLunatic.SysGuardeiNoCorazon.model.repositories.RepositoryFacade;
@CrossOrigin(origins = "*")
@RestController
public class DesafetoController {
	
	
	@PostMapping("/desafeto")
	public ResponseEntity<?> create(@RequestBody Desafeto desafeto){
		try {
			RepositoryFacade.getCurrentInstance().create(desafeto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/desafeto")
	public void update(@RequestBody Desafeto desafeto) {
		try {
			RepositoryFacade.getCurrentInstance().update(desafeto);
		} catch(SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao alterar registro");
		}
	}
	
	@GetMapping("/desafeto/{id}")
	public ResponseEntity<Desafeto>read(@PathVariable("id") int id){
		try {
			Desafeto desafeto = RepositoryFacade.getCurrentInstance().readDesafeto(id);
			if(desafeto==null) {
				return new ResponseEntity<Desafeto>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<Desafeto>(desafeto,HttpStatus.OK);
			} 
		}catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao buscar Entidade");
		}
	}
	
	@DeleteMapping("/desafeto/{id}")
	public void delete(@PathVariable("id") int id) {
		try {
			RepositoryFacade.getCurrentInstance().deleteDesafeto(id);
		}catch (SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao deletar");
		}
	}
	
	@GetMapping("/desafeto")
	public ResponseEntity<List<Desafeto>>readAll(){
		try {
			List<Desafeto> desafetos = new ArrayList<>();
			desafetos= RepositoryFacade.getCurrentInstance().readAllDesafetos();
			return new ResponseEntity<List<Desafeto>>(desafetos,HttpStatus.OK);
		}catch(SQLException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao buscar lista de registros");
		}
	}
	
	
	
}
