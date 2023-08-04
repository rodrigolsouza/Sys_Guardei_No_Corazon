package com.enderLunatic.SysGuardeiNoCorazon.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.enderLunatic.SysGuardeiNoCorazon.model.entities.Desafeto;
import com.enderLunatic.SysGuardeiNoCorazon.model.entities.Desavenca;

public class DesavencaRepository implements GenericRepository<Desavenca, Integer> {

	@Override
    public void create(Desavenca desavenca) {
		String sql = "INSERT INTO desavenca (tipo, violencia_fisica, local, descricao, data, hora, desafeto_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
        try {
        	
            PreparedStatement statement = ConnectionManager.getCurrentConnection().prepareStatement(sql);
            statement.setString(1, desavenca.getTipo());
            statement.setBoolean(2, desavenca.isViolenciaFisica());
            statement.setString(3, desavenca.getLocal());
            statement.setString(4, desavenca.getDescricao());
            statement.setString(5, desavenca.getData());
            statement.setString(6, desavenca.getHora());
            statement.setInt(7, desavenca.getDesafeto().getId());
            statement.execute();
       }catch (SQLException e) {
           e.printStackTrace();
       }
	}
	
	@Override
    public void update(Desavenca desavenca) {
		String sql = "UPDATE desavenca SET tipo = ?, violencia_fisica = ?, local = ?, descricao = ?, data = ?, hora = ?, desafeto_id = ? WHERE id = ?";
	
        try {
        	PreparedStatement statement = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        	
     
            statement.setString(1, desavenca.getTipo());
            statement.setBoolean(2, desavenca.isViolenciaFisica());
            statement.setString(3, desavenca.getLocal());
            statement.setString(4, desavenca.getDescricao());
            statement.setString(5, desavenca.getData());
            statement.setString(6, desavenca.getHora());
            statement.setInt(7, desavenca.getDesafeto().getId());
            statement.setInt(8, desavenca.getId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	@Override
    public Desavenca read(Integer id) {
		String sql = "SELECT * FROM desavenca as dv join desafeto as dt on (dv.desafeto_id=dt.id) WHERE dv.id = ?";
        
        try {
        	
        	PreparedStatement statement = ConnectionManager.getCurrentConnection().prepareStatement(sql);
            statement.setInt(1, id);
            Desavenca desavenca = null;
            ResultSet resultSet = statement.executeQuery();
           
            if (resultSet.next()) {
                desavenca = new Desavenca();
                desavenca.setId(resultSet.getInt("id"));
                desavenca.setTipo(resultSet.getString("tipo"));
                if(resultSet.getBoolean("violencia_fisica")){
                	desavenca.setViolenciaFisica(true);
                }else {
                	desavenca.setViolenciaFisica(false);
                }
                desavenca.setLocal(resultSet.getString("local"));
                desavenca.setDescricao(resultSet.getString("descricao"));
                desavenca.setData(resultSet.getString("data"));
                desavenca.setHora(resultSet.getString("hora"));
                
                Desafeto desafeto = new Desafeto();
                desafeto.setId(resultSet.getInt("desafeto_id"));
                desafeto.setNome(resultSet.getString("nome"));
                desafeto.setFaixaEtaria(resultSet.getString("faixa_etaria"));
                desafeto.setAltura(resultSet.getFloat("altura"));
                desafeto.setPeso(resultSet.getFloat("peso"));
                desafeto.setGrauConvivio(resultSet.getString("grau_convivio"));
                desafeto.setDescricao(resultSet.getString("descricao"));
                
                desavenca.setDesafeto(desafeto);
	        }
            return desavenca;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
	
	
	@Override
    public void delete(Integer id) {
		String sql = "DELETE FROM desavenca WHERE id = ?";
        try  {
        	PreparedStatement statement = ConnectionManager.getCurrentConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Desavenca> readAll() {
    	String sql= "SELECT * FROM desavenca as dv join desafeto as dt on (dv.desafeto_id=dt.id)";
    	
        List<Desavenca> desavencas = new ArrayList<>();
        try {
          
            PreparedStatement statement = ConnectionManager.getCurrentConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            Desavenca desavenca=null;
            while (resultSet.next()) {
            	desavenca = new Desavenca();
                desavenca.setId(resultSet.getInt("id"));
                desavenca.setTipo(resultSet.getString("tipo"));
                if(resultSet.getBoolean("violencia_fisica")){
                	desavenca.setViolenciaFisica(true);
                }else {
                	desavenca.setViolenciaFisica(false);
                }
                desavenca.setLocal(resultSet.getString("local"));
                desavenca.setDescricao(resultSet.getString("descricao"));
                desavenca.setData(resultSet.getString("data"));
                desavenca.setHora(resultSet.getString("hora"));
                
                Desafeto desafeto = new Desafeto();
                desafeto.setId(resultSet.getInt("desafeto_id"));
                desafeto.setNome(resultSet.getString("nome"));
                desafeto.setFaixaEtaria(resultSet.getString("faixa_etaria"));
                desafeto.setAltura(resultSet.getFloat("altura"));
                desafeto.setPeso(resultSet.getFloat("peso"));
                desafeto.setGrauConvivio(resultSet.getString("grau_convivio"));
                desafeto.setDescricao(resultSet.getString("descricao"));
                
                desavenca.setDesafeto(desafeto);
                
                desavencas.add(desavenca);
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return desavencas;
    }
    
}
