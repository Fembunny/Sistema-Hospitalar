
package dao;

import beans.Enfermeiro;
import conexaoJDBC.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnfermeiroDAO {
    private conexao conexao;
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    
    public EnfermeiroDAO() {
        this.conexao = new conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public static final List<Enfermeiro> enfermeiros = new ArrayList<>();
    
    public int Salvar(Enfermeiro enfermeiro){
        int status;
        try{
            st = conn.prepareStatement("INSERT INTO Enfermeiro (nome, cpf, data_nasc, endereco, telefone, cre) VALUES (?, ?, ?, ? , ?, ?)");
            st.setString(1, enfermeiro.getNome());
            st.setString(2, enfermeiro.getCpf());
            st.setString(3, enfermeiro.getData_nasc());
            st.setString(4, enfermeiro.getEndereco());
            st.setString(5, enfermeiro.getTelefone());
            st.setString(6, enfermeiro.getCre());
            
            
            status = st.executeUpdate();
            
            System.out.println("Enfermeiro cadastrado com sucesso");
            return status;
        }
        catch(SQLException ex){
            System.out.println("Erro ao conectar - " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
    public List<Enfermeiro> getEnfermeiro(String nome) { 
        String sql = "SELECT * FROM Enfermeiro WHERE nome LIKE ?";
        
        try {
            st = this.conn.prepareStatement(sql);

            st.setString(1,"%" + nome + "%");
            rs = st.executeQuery();    
            
            List<Enfermeiro> lista = new ArrayList<>();
            
            while (rs.next()) {
            Enfermeiro enfermeiro = new Enfermeiro();
          
            enfermeiro.setIdEnfermeiro(rs.getInt("id"));
            enfermeiro.setNome(rs.getString("nome"));
            enfermeiro.setCpf(rs.getString("cpf"));
            enfermeiro.setData_nasc(rs.getString("data_nasc"));
            enfermeiro.setEndereco(rs.getString("endereco"));
            enfermeiro.setTelefone(rs.getString("telefone"));
            enfermeiro.setCre(rs.getString("cre"));

            lista.add(enfermeiro);                   
            }                  
            
            return lista;   
        } catch (SQLException e) {
            return null;
        }
    }
    
    public Enfermeiro getEnfermeiro(Object id){
        String sql = "SELECT * FROM Enfermeiro WHERE id=?";
        
        try{
            PreparedStatement st = this.conn.prepareStatement(sql);
            st.setInt(1, (int) id);
            
            ResultSet rs = st.executeQuery();
            Enfermeiro enfermeiro = new Enfermeiro();
            
            rs.next();
            enfermeiro.setIdEnfermeiro(rs.getInt("id"));
            enfermeiro.setNome(rs.getString("nome"));
            enfermeiro.setCpf(rs.getString("cpf"));
            enfermeiro.setData_nasc(rs.getString("data_nasc"));
            enfermeiro.setEndereco(rs.getString("endereco"));
            enfermeiro.setTelefone(rs.getString("telefone"));
            enfermeiro.setCre(rs.getString("cre"));
            
            System.out.println("Consultando enfermeiro");
            return enfermeiro;
        }
        catch(SQLException ex){
            System.out.println("Erro ao buscar enfermeiro: " + ex.getMessage());
            return null;
        }
    }
    
    public int consultarID(int id){
        String sql = ("SELECT * FROM Enfermeiro WHERE id = ?");
        try{          
            st = this.conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            
            Enfermeiro enfermeiro = new Enfermeiro();
            while (rs.next()) {
                enfermeiro.setIdEnfermeiro(rs.getInt("id"));
                enfermeiro.setNome(rs.getString("nome"));
                enfermeiro.setCpf(rs.getString("cpf"));
                enfermeiro.setData_nasc(rs.getString("data_nasc"));
                enfermeiro.setEndereco(rs.getString("endereco"));
                enfermeiro.setTelefone(rs.getString("telefone"));
                enfermeiro.setCre(rs.getString("cre"));
            } 
            System.out.println("Consulta realizada com sucesso");
            return enfermeiro.getIdEnfermeiro();
        }
        catch(SQLException ex){
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return -1;
        }
    }
    
    public boolean Excluir(Object id){
        try {
            st = this.conn.prepareStatement("DELETE FROM Enfermeiro WHERE id = ?");
            st.setInt(1, (int) id);
            st.executeUpdate();
            
            System.out.println("Enfermeiro excluido");
            return true;
        } 
        catch (SQLException ex) {
            System.out.println("Erro ao excluir enfermeiro: " + ex.getMessage());
            return false;
        }
    }

    public int atualizar(Enfermeiro enfermeiro, int id){
        int status;
        try {
            st = conn.prepareStatement("UPDATE Enfermeiro SET nome = ?, cpf = ?, data_nasc = ?, endereco = ?, telefone = ?, cre = ? WHERE id = ?");
            st.setString(1,enfermeiro.getNome());
            st.setString(2,enfermeiro.getCpf());
            st.setString(3,enfermeiro.getData_nasc());
            st.setString(4,enfermeiro.getEndereco());
            st.setString(5,enfermeiro.getTelefone());
            st.setString(6,enfermeiro.getCre());
            st.setInt(7, id);
            
            status = st.executeUpdate();
            
            System.out.println("Enfermeiro atualizado com sucesso");
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
        }
    }
    
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
            
        }
    }

}
