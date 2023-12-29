
package dao;

import beans.Medico;
import conexaoJDBC.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MedicoDAO {
    private conexao conexao;
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    
    public MedicoDAO() {
        this.conexao = new conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public int Salvar(Medico medico){
        int status;
        try{
            st = conn.prepareStatement("INSERT INTO Medico (nome, cpf, data_nasc, endereco, telefone, crm, especialidade) VALUES (?, ?, ?, ? , ?, ?, ?)");
            st.setString(1, medico.getNome());
            st.setString(2, medico.getCpf());
            st.setString(3, medico.getData_nasc());
            st.setString(4, medico.getEndereco());
            st.setString(5, medico.getTelefone());
            st.setString(6, medico.getCrm());
            st.setString(7, medico.getEspecialidade());
            
            
            status = st.executeUpdate();
            
            System.out.println("Medico cadastrado com sucesso");
            return status;
        }
        catch(SQLException ex){
            System.out.println("Erro ao conectar - " + ex.getMessage());
            return ex.getErrorCode();
        } finally {
            desconectar();
        }
    }
    
    public Medico consultar(int id){
        String sql = ("SELECT * FROM Medico WHERE id = ?");
        try{          
            st = this.conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            
            Medico medico = new Medico();
            while (rs.next()) {
                medico.setIdMedico(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCpf(rs.getString("cpf"));
                medico.setData_nasc(rs.getString("data_nasc"));
                medico.setEndereco(rs.getString("endereco"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setCrm(rs.getString("crm"));
                medico.setEspecialidade(rs.getString("especialidade"));                
            } 
            System.out.println("Consulta realizada com sucesso");
            return medico;
        }
        catch(SQLException ex){
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        } finally{
            desconectar();
        }
    }
    
    public List<Medico> getMedico(String nome) { 
        String sql = "SELECT * FROM Medico WHERE nome LIKE ?";
        
        try {
            st = this.conn.prepareStatement(sql);

            st.setString(1,"%" + nome + "%");
            rs = st.executeQuery();    
            
            List<Medico> lista = new ArrayList<>();
            
            while (rs.next()) {
            Medico medico = new Medico();
          
            medico.setIdMedico(rs.getInt("id"));
            medico.setNome(rs.getString("nome"));
            medico.setCpf(rs.getString("cpf"));
            medico.setData_nasc(rs.getString("data_nasc"));
            medico.setEndereco(rs.getString("endereco"));
            medico.setTelefone(rs.getString("telefone"));
            medico.setCrm(rs.getString("crm"));
            medico.setEspecialidade(rs.getString("especialidade"));

            lista.add(medico);                   
            }                  
            
            return lista;   
        } catch (SQLException e) {
            return null;
        } finally {
            desconectar();
        }
    }
    
    public List<Medico> getEspecialidade(String nome) { 
        String sql = "SELECT * FROM Medico WHERE especialidade LIKE ?";
        
        try {
            st = this.conn.prepareStatement(sql);

            st.setString(1,"%" + nome + "%");
            rs = st.executeQuery();    
            
            List<Medico> lista = new ArrayList<>();
            
            while (rs.next()) {
            Medico medico = new Medico();
          
            medico.setIdMedico(rs.getInt("id"));
            medico.setNome(rs.getString("nome"));
            medico.setCpf(rs.getString("cpf"));
            medico.setData_nasc(rs.getString("data_nasc"));
            medico.setEndereco(rs.getString("endereco"));
            medico.setTelefone(rs.getString("telefone"));
            medico.setCrm(rs.getString("crm"));
            medico.setEspecialidade(rs.getString("especialidade"));

            lista.add(medico);                   
            }                  
            
            return lista;   
        } catch (SQLException e) {
            return null;
        } finally {
            desconectar();
        }
    }
    
    public String getEspecialidadeNome(int id) { 
        String sql = "SELECT especialidade FROM Medico WHERE id = ?";
        
        try {
            st = this.conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();    
            
            Medico medico = new Medico();
            while (rs.next()) {
            medico.setIdMedico(rs.getInt("id"));
            medico.setNome(rs.getString("nome"));
            medico.setCpf(rs.getString("cpf"));
            medico.setData_nasc(rs.getString("data_nasc"));
            medico.setEndereco(rs.getString("endereco"));
            medico.setTelefone(rs.getString("telefone"));
            medico.setCrm(rs.getString("crm"));
            medico.setEspecialidade(rs.getString("especialidade"));                 
            }                  
            
            System.out.println("Consulta realizada com sucesso");
            return medico.getEspecialidade(); 
            
        } catch (SQLException e) {
            return null;
        } finally {
            desconectar();
        }
    }
    
    public Medico getMedicoNome(String nome){
        String sql = "SELECT * FROM Medico WHERE nome LIKE ?";
        
        try{
            
            st = this.conn.prepareStatement(sql);

            st.setString(1, "%" + nome + "%");
            rs = st.executeQuery();

            Medico medico = new Medico();
            
            rs.next();
            medico.setIdMedico(rs.getInt("id"));
            medico.setNome(rs.getString("nome"));
            medico.setCpf(rs.getString("cpf"));
            medico.setData_nasc(rs.getString("data_nasc"));
            medico.setEndereco(rs.getString("endereco"));
            medico.setTelefone(rs.getString("telefone"));
            medico.setCrm(rs.getString("crm"));
            medico.setEspecialidade(rs.getString("especialidade")); 
            
            System.out.println("Consultando medico");
            return medico;
        }
        catch(SQLException | NullPointerException ex){
            System.out.println("Erro ao buscar medico: " + ex.getMessage());
            return null;
        }
    }
    
    public Medico getMedico(Object id){
        String sql = "SELECT * FROM Medico WHERE id=?";
        
        try{
            PreparedStatement st = this.conn.prepareStatement(sql);
            st.setInt(1, (int) id);
            
            ResultSet rs = st.executeQuery();
            Medico medico = new Medico();
            
            rs.next();
            medico.setIdMedico(rs.getInt("id"));
            medico.setNome(rs.getString("nome"));
            medico.setCpf(rs.getString("cpf"));
            medico.setData_nasc(rs.getString("data_nasc"));
            medico.setEndereco(rs.getString("endereco"));
            medico.setTelefone(rs.getString("telefone"));
            medico.setCrm(rs.getString("crm"));
            medico.setEspecialidade(rs.getString("especialidade"));
            
            System.out.println("Consultando medico");
            return medico;
        }
        catch(SQLException ex){
            System.out.println("Erro ao buscar medico: " + ex.getMessage());
            return null;
        } finally {
            desconectar();
        }
    }
    
    public int consultarID(String nome){
        String sql = ("SELECT id FROM Medico WHERE nome LIKE ?");
        try{          
            st = this.conn.prepareStatement(sql);
            st.setString(1,"%" + nome + "%");
            rs = st.executeQuery();
            
            Medico medico = new Medico();
            while (rs.next()) {
                medico.setIdMedico(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCpf(rs.getString("cpf"));
                medico.setData_nasc(rs.getString("data_nasc"));
                medico.setEndereco(rs.getString("endereco"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setCrm(rs.getString("crm"));
                medico.setEspecialidade(rs.getString("especialidade"));
            } 
            System.out.println("Consulta realizada com sucesso");
            return medico.getIdMedico();
        }
        catch(SQLException ex){
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return -1;
        } finally {
            desconectar();
        }
    }
    
    public int consultarID(int id){
        String sql = ("SELECT * FROM Medico WHERE id = ?");
        try{          
            st = this.conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            
            Medico medico = new Medico();
            while (rs.next()) {
                medico.setIdMedico(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCpf(rs.getString("cpf"));
                medico.setData_nasc(rs.getString("data_nasc"));
                medico.setEndereco(rs.getString("endereco"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setCrm(rs.getString("crm"));
                medico.setEspecialidade(rs.getString("especialidade"));                
            } 
            System.out.println("Consulta realizada com sucesso");
            return medico.getIdMedico();
        }
        catch(SQLException ex){
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return -1;
        } finally{
            desconectar();
        }
    }
    
    public boolean Excluir(Object id){
        try {
            st = this.conn.prepareStatement("DELETE FROM Medico WHERE id = ?");
            st.setInt(1, (int) id);
            st.executeUpdate();
            
            System.out.println("Medico excluido");
            return true;
        } 
        catch (SQLException ex) {
            System.out.println("Erro ao excluir medico: " + ex.getMessage());
            return false;
        } finally {
            desconectar();
        }
    }

    public int atualizar(Medico medico, int id){
        int status;
        try {
            st = conn.prepareStatement("UPDATE medico SET nome = ?, cpf = ?, data_nasc = ?, endereco = ?, telefone = ?, crm = ?, especialidade = ? WHERE id = ?");
            st.setString(1,medico.getNome());
            st.setString(2,medico.getCpf());
            st.setString(3,medico.getData_nasc());
            st.setString(4,medico.getEndereco());
            st.setString(5,medico.getTelefone());
            st.setString(6,medico.getCrm());
            st.setString(7,medico.getEspecialidade());
            st.setInt(8, id);
            
            status = st.executeUpdate();
            
            System.out.println("Paciente atualizado com sucesso");
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
