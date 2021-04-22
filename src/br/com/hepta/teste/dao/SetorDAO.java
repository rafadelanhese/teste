package br.com.hepta.teste.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hepta.teste.conexao.ConnectionFactory;
import br.com.hepta.teste.entity.Setor;

public class SetorDAO {

    private Connection connection;

    public SetorDAO(){
        this.connection = ConnectionFactory.criarConexao();
    }

    public List<Setor> listarTodos(){
        String sql = "SELECT * FROM Setor ORDER BY nome";
        List<Setor> lista = new ArrayList<Setor>();

        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lista.add(new Setor(rs.getInt(1), rs.getString(2)));
            }     
            rs.close();
            ps.close();             
            return lista;
        }catch(SQLException e){ 
            if(connection != null){
                try{
                    connection.rollback();
                }catch(SQLException rb){
                    System.out.println("Erro no rollback: " + rb.getMessage());                    
                }             
            }
            return null;            
        }    
    }

    public void criar(Setor setor){
        String sql = "INSERT INTO Setor (nome) VALUES (?)";

        try{

            PreparedStatement ps = connection.prepareStatement(sql);

            //ps.setInt(1, setor.getId());
            ps.setString(1, setor.getNome());

            ps.execute();
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
    }
    
    public void atualizar(Setor setor){
        String sql = "UPDATE Setor SET nome=? WHERE id_setor=?";

        try{
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, setor.getNome());
            ps.setInt(2, setor.getId());

            int retorno = ps.executeUpdate();
            if(retorno > 0){
                System.out.printf("Novo registro alterado: %d: %s ",setor.getId(), setor.getNome());
            }else{
                System.out.println("Não foi possível alterar os registros!");
            }
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
    }    

    public Setor procurarPorId(int id){
        String sql = "SELECT * FROM Setor WHERE id_setor=?";
        Setor setor = null;        
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();

            if(rs != null){
                setor = new Setor(rs.getInt(1), rs.getString(2));
            }

            ps.close();
            rs.close();
            return setor;
        }catch(SQLException e){ 
            if(connection != null){
                try{
                    connection.rollback();
                }catch(SQLException rb){
                    System.out.println("Erro no rollback: " + rb.getMessage());                    
                }             
            }
            return null;            
        }    
    }

    public void deletar(int id){
        String sql = "DELETE FROM Setor WHERE id_setor=?";          
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            
            int retorno = ps.executeUpdate();
            if(retorno > 0){
                System.out.printf("Registro de ID %d foi deletado",id);
            }else{
                System.out.println("Não foi possível deletar!");
            }  
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
    }
}
