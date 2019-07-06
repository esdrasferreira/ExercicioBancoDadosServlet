package br.com.esdrasferreira.factory.jdbc;

import java.sql.*;

public class FabricaConexao {
	

	
	public static Connection getConexao() throws Exception {
		String url = "jdbc:mysql://192.175.108.234/servlet?useTimezone=true&serverTimezone=UTC";	 //porta tcp 3306	
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			return DriverManager.getConnection( url , "esdras01", "j4cA0~uh!x-f");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("Erro na conexão");
		}
	}
	
	public static void fecharConexao(Connection conexao) throws Exception{
		fecharTudo(conexao, null, null, null);
		
		
	}
	
	public static void fecharStatement(Statement stmt) throws Exception{
		fecharTudo(null, stmt, null, null);
		
	}
	
	public static void fecharResultSet(ResultSet rs) throws Exception{
		fecharTudo(null, null, rs, null);
		
	}
	
	public static void fecharPreparedStatement(PreparedStatement ps) throws Exception{
		fecharTudo(null, null, null, ps);
		
	}
	
	public static void fecharStmtRs(Statement stmt, ResultSet rs) throws Exception{
		fecharTudo(null, stmt, rs, null);
		
	}
	
	
	private static void fecharTudo(Connection conn, Statement stmt, ResultSet rs, PreparedStatement ps) throws Exception {
		
		try {
			if( conn != null ) conn.close();
			if( rs != null ) rs.close();
			if( ps != null ) ps.close();
			if( stmt != null ) stmt.close();
	
			
		}catch (Exception e){
			throw new Exception(e.getMessage()) ;
		}
		
	}
	
	
	

}
