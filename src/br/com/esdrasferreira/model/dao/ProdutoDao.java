package br.com.esdrasferreira.model.dao;

import java.sql.*;
import java.util.*;

import br.com.esdrasferreira.model.entity.Produto;
import br.com.esdrasferreira.factory.jdbc.*;

public class ProdutoDao {

	private Connection conexao;

	public ProdutoDao() throws Exception {
		this.conexao = FabricaConexao.getConexao();
	}

	public void addProduto(String produto, int id) throws Exception {

		PreparedStatement ps = null;
		Connection conexao = null;
//UPDATE `produtos` SET `produto` = 'ps4' WHERE `produtos`.`id_produto` = 1
		try {
			conexao = this.conexao;
			ps = conexao.prepareStatement(
					"INSERT INTO `produtos` (`id_produto`, `produto`, `usuariofk`) VALUES (NULL, ?, ?)");
			ps.setString(1, produto);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception();
		} finally {
			FabricaConexao.fecharPreparedStatement(ps);
			fecharConexao();
		}

	}

	public void atualizar(Produto produto) throws Exception {

		PreparedStatement ps = null;
		Connection conexao = null;

		try {
			conexao = this.conexao;
			ps = conexao.prepareStatement("UPDATE `produtos` SET `produto` =? WHERE `produtos`.`id_produto` = ?");
			ps.setString(1, produto.getProduto());
			ps.setInt(2, produto.getId());

			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception();
		} finally {
			FabricaConexao.fecharPreparedStatement(ps);
			fecharConexao();
		}

	}

	public Produto pesquisaPorID(int id) throws Exception {

		Connection conexao = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Produto produto = null;

		try {

			conexao = this.conexao;
			ps = conexao.prepareStatement("SELECT * FROM `produtos` WHERE id_produto = '" + id + "' ");
			rs = ps.executeQuery();

			while (rs.next()) {

				produto = new Produto(rs.getInt(1), rs.getString(2));

			}

			return produto;

		} catch (SQLException sqle) {

			throw new Exception("Erro ao visualizar os dados " + sqle);

		} finally {
			FabricaConexao.fecharStmtRs(ps, rs);
			fecharConexao();
		}

	}

	public List<Produto> pesquisaPorNomeProduto(String nome, int id) throws Exception {

		Connection conexao = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Produto produto = null;

		try {

			conexao = this.conexao;
			ps = conexao.prepareStatement(
					"select servlet.produtos.id_produto, servlet.produtos.produto\n" + "from servlet.produtos\n"
							+ "where servlet.produtos.usuariofk = ?\n" + "and servlet.produtos.produto like ?");
			ps.setInt(1, id);
			ps.setString(2, '%' + nome + '%');
			rs = ps.executeQuery();
			List<Produto> produtos = new ArrayList<Produto>();

			while (rs.next()) {

				produto = new Produto(rs.getInt(1), rs.getString(2));
				produtos.add(produto);

			}

			return produtos;

		} catch (SQLException sqle) {

			throw new Exception("Erro ao visualizar os dados " + sqle);

		} finally {
			FabricaConexao.fecharStmtRs(ps, rs);
			fecharConexao();
		}

	}

	public List<Produto> todos(int id) throws Exception {

		Connection conexao = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		Produto produto = null;

		try {

			conexao = this.conexao;
			st = conexao.prepareStatement(
					"SELECT produtos.id_produto, produto FROM `produtos` WHERE `usuariofk` = '" + id + "' ");
			rs = st.executeQuery();

			List<Produto> produtos = new ArrayList<Produto>();

			while (rs.next()) {

				produto = new Produto(rs.getInt(1), rs.getString(2));

				produtos.add(produto);

			}

			return produtos;

		} catch (SQLException sqle) {

			throw new Exception("Erro ao visualizar os dados " + sqle);

		} finally {
			FabricaConexao.fecharStmtRs(st, rs);
			fecharConexao();
		}

	}

	// devemos chamar o fechamento da conexao apenas quando não for usar mais
	public void fecharConexao() throws Exception {
		FabricaConexao.fecharConexao(conexao);
	}

	public void excluir(Integer id) throws Exception {

		PreparedStatement ps = null;
		Connection conexao = null;
//"DELETE FROM `produtos` WHERE `produtos`.`id_produto` = 1"?
		try {
			conexao = this.conexao;
			ps = conexao.prepareStatement("DELETE FROM `produtos` WHERE `produtos`.`id_produto` = '" + id + "'");

			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception();
		} finally {
			FabricaConexao.fecharPreparedStatement(ps);
			fecharConexao();
		}

	}

}
