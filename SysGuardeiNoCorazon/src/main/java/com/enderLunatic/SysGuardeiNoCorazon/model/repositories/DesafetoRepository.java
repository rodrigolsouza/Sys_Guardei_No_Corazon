package com.enderLunatic.SysGuardeiNoCorazon.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.enderLunatic.SysGuardeiNoCorazon.model.entities.Desafeto;
import com.enderLunatic.SysGuardeiNoCorazon.model.entities.Desavenca;


public class DesafetoRepository implements GenericRepository<Desafeto, Integer> {

	public DesafetoRepository() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public void create(Desafeto desafeto) {
		String sql ="INSERT INTO desafeto (nome, faixa_etaria, altura, peso, grau_convivio, descricao) VALUES (?, ?, ?, ?, ?, ?)";	
				
        try {
        	
        	PreparedStatement stmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);
            stmt.setString(1, desafeto.getNome());
            stmt.setString(2, desafeto.getFaixaEtaria());
            stmt.setFloat(3, desafeto.getAltura());
            stmt.setFloat(4, desafeto.getPeso());
            stmt.setString(5, desafeto.getGrauConvivio());
            stmt.setString(6, desafeto.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
			
	}
	
	@Override
    public void update(Desafeto desafeto) {
		String sql = "UPDATE desafeto SET nome = ?, faixa_etaria = ?, altura = ?, peso = ?, grau_convivio = ?, descricao = ? WHERE id = ?";
        
		try {
			
			PreparedStatement stmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);
            stmt.setString(1, desafeto.getNome());
            stmt.setString(2, desafeto.getFaixaEtaria());
            stmt.setFloat(3, desafeto.getAltura());
            stmt.setFloat(4, desafeto.getPeso());
            stmt.setString(5, desafeto.getGrauConvivio());
            stmt.setString(6, desafeto.getDescricao());
            stmt.setInt(7, desafeto.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	@Override
    public Desafeto read(Integer id) {
		String sql= "SELECT * FROM desafeto WHERE id = ?";
        Desafeto desafeto = null;
        try {
            PreparedStatement stmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                desafeto = new Desafeto();
                desafeto.setId(rs.getInt("id"));
                desafeto.setNome(rs.getString("nome"));
                desafeto.setFaixaEtaria(rs.getString("faixa_etaria"));
                desafeto.setAltura(rs.getFloat("altura"));
                desafeto.setPeso(rs.getFloat("peso"));
                desafeto.setGrauConvivio(rs.getString("grau_convivio"));
                desafeto.setDescricao(rs.getString("descricao"));
                
                desafeto.setDesavencas(readDesavencasPorDesafeto(id));
                
                return desafeto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	@Override
    public void delete(Integer id) {
		String sql="DELETE FROM desafeto WHERE id = ?";
		
        try {
        	PreparedStatement stmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	@Override
    public List<Desafeto> readAll() {
		
		String sql ="SELECT * FROM desafeto";
        List<Desafeto> desafetos = new ArrayList<>();
        
        try {
        	PreparedStatement stmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Desafeto desafeto = new Desafeto();
                desafeto.setId(rs.getInt("id"));
                desafeto.setNome(rs.getString("nome"));
                desafeto.setFaixaEtaria(rs.getString("faixa_etaria"));
                desafeto.setAltura(rs.getFloat("altura"));
                desafeto.setPeso(rs.getFloat("peso"));
                desafeto.setGrauConvivio(rs.getString("grau_convivio"));
                desafeto.setDescricao(rs.getString("descricao"));
                
                desafeto.setDesavencas(readDesavencasPorDesafeto(desafeto.getId()));
                desafetos.add(desafeto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return desafetos;
    }
	
	
    public List<Desavenca> readDesavencasPorDesafeto(Integer id) {
		String sql= "select * from desavenca where desafeto_id = ?";
    
        List<Desavenca> desavencas= new ArrayList<>();
        Desavenca desavenca = null;
        try {
            PreparedStatement stmt = ConnectionManager.getCurrentConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	desavenca = new Desavenca();
                desavenca.setId(rs.getInt("id"));
                desavenca.setTipo(rs.getString("tipo"));
                if(rs.getBoolean("violencia_fisica")){
                	desavenca.setViolenciaFisica(true);
                }else {
                	desavenca.setViolenciaFisica(false);
                }
                desavenca.setLocal(rs.getString("local"));
                desavenca.setDescricao(rs.getString("descricao"));
                desavenca.setData(rs.getString("data"));
                desavenca.setHora(rs.getString("hora"));
                
                
            	desavencas.add(desavenca);
            }
            return desavencas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
}
