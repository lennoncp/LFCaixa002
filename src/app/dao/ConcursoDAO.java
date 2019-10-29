package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.config.DBConfig;
import app.model.Aposta;

public class ConcursoDAO {

	private Connection con;
	
	private PreparedStatement ps;
	private ResultSet rs;
	
	public List<List<Integer>> getAllConcursos(){
		List<List<Integer>> concursos = new ArrayList<List<Integer>>();
		
		con = DBConfig.getConnection();
		
		String sql = " SELECT concurso, bola1, bola2, bola3, bola4, bola5, bola6, bola7, bola8, bola9, bola10, bola11, bola12, bola13, bola14, bola15 FROM concursos ";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				List<Integer> dezenas = new ArrayList<Integer>();
				dezenas.add(rs.getInt("bola1")); 
				dezenas.add(rs.getInt("bola2")); 
				dezenas.add(rs.getInt("bola3")); 
				dezenas.add(rs.getInt("bola4")); 
				dezenas.add(rs.getInt("bola5"));
				dezenas.add(rs.getInt("bola6"));
				dezenas.add(rs.getInt("bola7"));
				dezenas.add(rs.getInt("bola8"));
				dezenas.add(rs.getInt("bola9"));
				dezenas.add(rs.getInt("bola10"));
				dezenas.add(rs.getInt("bola11"));
				dezenas.add(rs.getInt("bola12"));
				dezenas.add(rs.getInt("bola13"));
				dezenas.add(rs.getInt("bola14"));
				dezenas.add(rs.getInt("bola15"));
				
				concursos.add((int) rs.getLong("concurso"), dezenas);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return concursos;
	}
	
	public List<Aposta> getAllConcursosSimples(){
		List<Aposta> concursos = new ArrayList<Aposta>();
		
		con = DBConfig.getConnection();
		
		String sql = " SELECT concurso, bola1, bola2, bola3, bola4, bola5, bola6, bola7, bola8, bola9, bola10, bola11, bola12, bola13, bola14, bola15 FROM concursos ";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Aposta aposta = new Aposta();
				List<Integer> dezenas = new ArrayList<Integer>();
				dezenas.add(rs.getInt("bola1")); 
				dezenas.add(rs.getInt("bola2")); 
				dezenas.add(rs.getInt("bola3")); 
				dezenas.add(rs.getInt("bola4")); 
				dezenas.add(rs.getInt("bola5"));
				dezenas.add(rs.getInt("bola6"));
				dezenas.add(rs.getInt("bola7"));
				dezenas.add(rs.getInt("bola8"));
				dezenas.add(rs.getInt("bola9"));
				dezenas.add(rs.getInt("bola10"));
				dezenas.add(rs.getInt("bola11"));
				dezenas.add(rs.getInt("bola12"));
				dezenas.add(rs.getInt("bola13"));
				dezenas.add(rs.getInt("bola14"));
				dezenas.add(rs.getInt("bola15"));
				
				//concursos.add((int) rs.getLong("concurso"), dezenas);
				aposta.setDezenas(dezenas);
				concursos.add(aposta);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return concursos;
	}
	
	public List<Integer> getRangePorcentagens(int range){
		List<Integer> porcentagens = new ArrayList<Integer>();
		List<Aposta>  concursos = new ArrayList<Aposta>();
		Aposta ap = new Aposta();
		
		con = DBConfig.getConnection();
		
		String sql = "SELECT bola1, bola2, bola3, bola4, bola5, \r\n" + 
				          "	 bola6, bola7, bola8, bola9, bola10,\r\n" + 
				          "	 bola11, bola12, bola13, bola14, bola15\r\n" + 
					"FROM concursos\r\n" + 
					"ORDER BY concurso desc\r\n" + 
					"LIMIT " + range +";";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Aposta aposta = new Aposta();
				List<Integer> dezenas = new ArrayList<Integer>();
				for(int i = 1; i <= 15; i++) {
					dezenas.add(rs.getInt("bola"+i));
				}
				
				aposta.setDezenas(dezenas);
				concursos.add(aposta);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer[] contagem =  ap.contagem(concursos);
		
		int i = 1;
		for(int x : contagem) {
			System.out.println(i++ +" ( " + x + " * 100) / " + range + " ");
			porcentagens.add((x * 100) / range);
		}
		
		DBConfig.closeConnection();
		
		return porcentagens;
	}
	
	
	public List<Integer> getApostasPorcentagens(List<Aposta> apostas){
		List<Integer> porcentagens = new ArrayList<Integer>();
		List<Aposta>  concursos = new ArrayList<Aposta>();
		Aposta ap = new Aposta();
		
		
		
		Integer[] contagem =  ap.contagem(concursos);
		
		int i = 1;
		for(int x : contagem) {
			System.out.println(i++ +" ( " + x + " * 100) / " + apostas.size() + " ");
			porcentagens.add((x * 100) / apostas.size());
		}	
		
		return porcentagens;
	}
	
	public Long salvarAposta(Aposta aposta) {
		Long id = 0L;
		con = DBConfig.getConnection();
		
		String sql = " INSERT INTO apostas(BOLA1,BOLA2,BOLA3,BOLA4,BOLA5,BOLA6,BOLA7,BOLA8,BOLA9,BOLA10,BOLA11,BOLA12,BOLA13,BOLA14,BOLA15) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	
		try {
			ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			for(int i = 0; i < aposta.getDezenas().size(); i++)
				ps.setInt(i+1, aposta.getDezenas().get(i));
			
			ps.execute();
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
				id = rs.getLong(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBConfig.closeConnection();
		return id;
	}
	
}
