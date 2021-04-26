package br.com.hepta.teste.dao;

import br.com.hepta.teste.conexao.ConnectionFactory;
import br.com.hepta.teste.entity.Funcionario;
import br.com.hepta.teste.entity.Setor;
import br.com.hepta.teste.interfacedao.IFuncionarioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO implements IFuncionarioDAO {

    private Connection connection;
    private final int LINHAS_AFETADAS = 0;

    public FuncionarioDAO(){
        this.connection = ConnectionFactory.criarConexao();
    }

    @Override
    public void fecharConexao() {
        try{
            this.connection.close();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    @Override
    public List<Funcionario> listarTodos() {
        String sql = "SELECT * FROM Funcionario ORDER BY nome";
        List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
        SetorDAO setorDAO = new SetorDAO();

        try{
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                listaFuncionario.add(new Funcionario(rs.getInt(1),
                                                    rs.getString(4),
                                                    setorDAO.procurarPorId(rs.getInt(6)),
                                                    rs.getDouble(5),
                                                    rs.getString(2),
                                                    rs.getInt(3)));
            }
            setorDAO.fecharConexao();
            rs.close();
            ps.close();
        }catch(SQLException e){
            if(connection != null){
                try{
                    connection.rollback();
                }catch(SQLException rb){
                    System.out.println("Erro no rollback: " + rb.getMessage());
                }
            }
        }
        return listaFuncionario;
    }

    @Override
    public boolean criar(Funcionario funcionario) {
        boolean funcionarioCriado = false;
        String sql = "INSERT INTO Funcionario (ds_email, nu_idade, nome, nu_salario, fk_setor) VALUES (?,?,?,?,?)";

        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, funcionario.getEmail());
            ps.setInt(2, funcionario.getIdade());
            ps.setString(3, funcionario.getNome());
            ps.setDouble(4, funcionario.getSalario());
            ps.setInt(5, funcionario.getSetor().getId());

            if(ps.executeUpdate() > LINHAS_AFETADAS)
                funcionarioCriado = !funcionarioCriado;

            ps.close();
        }catch(SQLException e){
            if(connection != null){
                try{
                    connection.rollback();
                }catch(SQLException rb){
                    System.out.println("Erro no rollback: " + rb.getMessage());
                }
            }
        }

        return funcionarioCriado;
    }

    @Override
    public boolean atualizar(Funcionario funcionario) {
        boolean funcionarioAtualizado = false;
        String sql = "UPDATE Funcionario SET ds_email=?, nu_idade=?, nome=?, nu_salario=?, fk_setor=? WHERE id_funcionario=?";

        try{
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, funcionario.getEmail());
            ps.setInt(2, funcionario.getIdade());
            ps.setString(3, funcionario.getNome());
            ps.setDouble(4, funcionario.getSalario());
            ps.setInt(5, funcionario.getSetor().getId());
            ps.setInt(6, funcionario.getId());

            if(ps.executeUpdate() > LINHAS_AFETADAS)
                funcionarioAtualizado = !funcionarioAtualizado;

            ps.close();

        } catch(SQLException e) {
            if(connection != null){
                try{
                    connection.rollback();
                }catch(SQLException rb){
                    System.out.println("Erro no rollback: " + rb.getMessage());
                }
            }
        }

        return funcionarioAtualizado;
    }

    @Override
    public Funcionario procurarPorId(int id) {
        String sql = "SELECT * FROM Funcionario WHERE id_funcionario=?";
        Funcionario funcionario = null;

        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs != null && rs.next()){
                SetorDAO setorDAO = new SetorDAO();
                funcionario = new Funcionario(rs.getInt(1),
                                    rs.getString(4),
                                    setorDAO.procurarPorId(rs.getInt(6)),
                                    rs.getDouble(5),
                                    rs.getString(2),
                                    rs.getInt(3));
                setorDAO.fecharConexao();
            }

            ps.close();
            rs.close();
        }catch(SQLException e){
            e.printStackTrace();
            if(connection != null){
                try{
                    connection.setAutoCommit(false);
                    connection.rollback();
                }catch(SQLException rb){
                    System.out.println("Erro no rollback: " + rb.getMessage());
                }
            }
        }
        return funcionario;
    }

    @Override
    public Funcionario procurarPorNome(String nome) {
        String sql = "SELECT * FROM Funcionario WHERE nome=?";
        Funcionario funcionario = null;
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nome);

            ResultSet rs = ps.executeQuery();

            if(rs != null && rs.next()){
                SetorDAO setorDAO = new SetorDAO();
                Setor setor = setorDAO.procurarPorId(rs.getInt(6));
                funcionario = new Funcionario(rs.getInt(1),
                                            rs.getString(4),
                                            setorDAO.procurarPorId(rs.getInt(6)),
                                            rs.getDouble(5),
                                            rs.getString(2),
                                            rs.getInt(3));
                setorDAO.fecharConexao();
            }

            ps.close();
            rs.close();
        }catch(SQLException e){
            if(connection != null){
                try{
                    connection.setAutoCommit(false);
                    connection.rollback();
                }catch(SQLException rb){
                    System.out.println("Erro no rollback: " + rb.getMessage());
                }
            }
        }
        return funcionario;
    }

    @Override
    public boolean deletar(int id) {
        boolean funcionarioDeletado = false;
        String sql = "DELETE FROM Funcionario WHERE id_funcionario=?";

        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            if(ps.executeUpdate() > LINHAS_AFETADAS)
                funcionarioDeletado = !funcionarioDeletado;

            ps.close();
        }catch(SQLException e){
            if(connection != null){
                try{
                    connection.rollback();
                }catch(SQLException rb){
                    System.out.println("Erro no rollback: " + rb.getMessage());
                }
            }
        }

        return funcionarioDeletado;
    }
}
