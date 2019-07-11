package br.com.esdrasferreira.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.esdrasferreira.factory.jdbc.FabricaConexao;
import br.com.esdrasferreira.model.entity.UsuarioProduto;

public class UsuarioProdutoDao {

	private Connection conexao;

	public UsuarioProdutoDao() throws Exception {
		this.conexao = FabricaConexao.getConexao();
	}

	public UsuarioProduto getDados(int usuarioId, int prodId) throws Exception {

		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		UsuarioProduto user = null;

		try {

			conn = this.conexao;
			ps = conn.prepareStatement(
					"SELECT servlet.usuarios.id_usuario, servlet.usuarios.nome,servlet.produtos.id_produto, servlet.produtos.produto\n"
							+ " from servlet.usuarios, servlet.produtos\n" + " where servlet.usuarios.id_usuario='"
							+ usuarioId + "' and servlet.produtos.id_produto='" + prodId + "'");

			rs = ps.executeQuery();

			while (rs.next()) {

				user = new UsuarioProduto();
				user.setIdusuario(rs.getInt("id_usuario"));
				user.setNome(rs.getString("nome"));
				user.setIdproduto(rs.getInt("id_produto"));
				user.setProduto(rs.getString("produto"));

			}

			return user;

		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
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
