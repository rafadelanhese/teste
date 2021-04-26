package br.com.hepta.teste.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hepta.teste.conexao.ConnectionFactory;
import br.com.hepta.teste.entity.Setor;
import br.com.hepta.teste.interfacedao.ISetorDAO;

public class SetorDAO implements ISetorDAO{

    private Connection connection;
    private final int LINHAS_AFETADAS = 0;

    public SetorDAO(){
        this.connection = ConnectionFactory.criarConexao();
    }

    @Override
    public void fecharConexao(){
        try{
            this.connection.close();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }    
    }

    @Override
    public List<Setor> listarTodos(){
        String sql = "SELECT * FROM Setor ORDER BY nome";
        List<Setor> listaSetor = new ArrayList<Setor>();

        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                listaSetor.add(new Setor(rs.getInt(1), rs.getString(2)));
            }     
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
        return listaSetor;
    }

    @Override
    public boolean criar(Setor setor){
        boolean setorCriado = false;
        String sql = "INSERT INTO Setor (nome) VALUES (?)";

        try{
            PreparedStatement ps = connection.prepareStatement(sql);        
            ps.setString(1, setor.getNome());

            if(ps.executeUpdate() > LINHAS_AFETADAS)
                setorCriado = !setorCriado;

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

        return setorCriado;
    }
    
    @Override
    public boolean atualizar(Setor setor){
        boolean setorAtualizado = false;
        String sql = "UPDATE Setor SET nome=? WHERE id_setor=?";

        try{
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, setor.getNome());
            ps.setInt(2, setor.getId());            

            if(ps.executeUpdate() > LINHAS_AFETADAS)
                setorAtualizado = !setorAtualizado;
        
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

        return setorAtualizado;
    }    

    @Override
    public Setor procurarPorId(int id){
        String sql = "SELECT * FROM Setor WHERE id_setor=?";
        Setor setor = null;        
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs != null && rs.next()){            
                setor = new Setor(rs.getInt(1), rs.getString(2));                
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
        return setor;
    }

    @Override
    public Setor procurarPorNome(String nome){
        String sql = "SELECT * FROM Setor WHERE nome=?";
        Setor setor = null;        
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nome);
            
            ResultSet rs = ps.executeQuery();

            if(rs != null && rs.next()){
                setor = new Setor(rs.getInt(1), rs.getString(2));
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
        return setor;
    }

    @Override
    public boolean deletar(int id){
        boolean setorDeletado = false;
        String sql = "DELETE FROM Setor WHERE id_setor=?"; 
        
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
                        
            if(ps.executeUpdate() > LINHAS_AFETADAS)
            setorDeletado = !setorDeletado;
            
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
        
        return setorDeletado;
    }
}
