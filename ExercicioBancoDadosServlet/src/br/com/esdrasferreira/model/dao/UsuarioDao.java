package br.com.esdrasferreira.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.esdrasferreira.model.entity.Usuario;
import br.com.esdrasferreira.factory.jdbc.*;

public class UsuarioDao {
	
	
	private Connection conexao;
	
	public UsuarioDao() throws Exception {
		this.conexao = FabricaConexao.getConexao();
	}
	
	public String getDados(int usuarioId)throws Exception{
		
		
		
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		UsuarioProduto user = null;
		
		
try {
			
			conn = this.conexao;
			ps = conn.prepareStatement("SELECT servlet.usuarios.id_usuario, servlet.usuarios.nome, servlet.produtos.produto\n" + 
					" from servlet.usuarios, servlet.produtos\n" + 
					" where servlet.usuarios.id_usuario='"+usuarioId+"' and servlet.produtos.id_produto='"+usuarioId+"'");
						
			rs = ps.executeQuery();
			
			
			
			while (rs.next()) {
				
				user = new UsuarioProduto();
				user.setIdusuario(rs.getInt("id_usuario"));
				user.setNome(rs.getString("nome"));
				user.setProduto(rs.getString("produto"));
				
				
				
			}
			
			
			
			return user.toString();
			
			
			
			
		}catch(SQLException sqle) {
			throw new Exception(sqle);
		}finally {
			FabricaConexao.fecharPreparedStatement(ps);
			FabricaConexao.fecharResultSet(rs);
			fecharConexao();

		}
		
		
	}
	
	
	public Usuario login(String usuario, String senha) throws Exception {
		
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		
		
		try {
			
			conn = this.conexao;
			ps = conn.prepareStatement("SELECT * FROM `usuarios` WHERE nome=? AND senha =?");
			ps.setString(1, usuario);
			ps.setString(2, senha);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				return new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3));
				
			}
			return null;
			
			
			
		}catch(SQLException sqle) {
			throw new Exception(sqle);
		}finally {
			FabricaConexao.fecharPreparedStatement(ps);
			FabricaConexao.fecharResultSet(rs);
			fecharConexao();

		}
		
		
		
	}
	
public Usuario dados(String usuario, String senha) throws Exception {
		
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		
		
		try {
			
			conn = this.conexao;
			ps = conn.prepareStatement("SELECT * FROM servlet.produtos where servlet.produtos.id_produto =?");
			ps.setString(1, usuario);
			ps.setString(2, senha);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				return new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3));
				
			}
			return null;
			
			
			
		}catch(SQLException sqle) {
			throw new Exception(sqle);
		}finally {
			FabricaConexao.fecharPreparedStatement(ps);
			FabricaConexao.fecharResultSet(rs);
			fecharConexao();

		}
		
		
		
	}
	

	//devemos chamar o fechamento da conexao apenas quando não for usar mais
	public void fecharConexao() throws Exception {
		FabricaConexao.fecharConexao(conexao);
	}


}
