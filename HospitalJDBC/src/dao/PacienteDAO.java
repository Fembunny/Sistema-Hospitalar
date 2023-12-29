package dao;

import beans.Paciente;
import conexaoJDBC.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PacienteDAO {
    private conexao conexao;
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    
    public PacienteDAO() {
        this.conexao = new conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public static final List<Paciente> pacientes = new ArrayList<>();
    
    public int Salvar(Paciente paciente){
        int status;
        try{
            st = conn.prepareStatement("INSERT INTO Paciente (nome, cpf, data_nasc, endereco, telefone) VALUES (?, ?, ?, ? , ?)");
            st.setString(1, paciente.getNome());
            st.setString(2, paciente.getCpf());
            st.setString(3, paciente.getData_nasc());
            st.setString(4, paciente.getEndereco());
            st.setString(5, paciente.getTelefone());
            
            
            status = st.executeUpdate();
            
            System.out.println("Paciente cadastrado com sucesso");
            return status;
        }
        catch(SQLException ex){
            System.out.println("Erro ao conectar - " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
   
    
    public Paciente getPacienteNome(String nome){
        String sql = "SELECT * FROM Paciente WHERE nome LIKE ?";
        
        try{
            
            st = this.conn.prepareStatement(sql);

            st.setString(1, "%" + nome + "%");
            rs = st.executeQuery();

            Paciente paciente = new Paciente();
            
            rs.next();
            paciente.setIdPaciente(rs.getInt("id"));
            paciente.setNome(rs.getString("nome"));
            paciente.setCpf(rs.getString("cpf"));
            paciente.setData_nasc(rs.getString("data_nasc"));
            paciente.setEndereco(rs.getString("endereco"));
            paciente.setTelefone(rs.getString("telefone"));
            
            System.out.println("Consultando paciente");
            return paciente;
        }
        catch(SQLException ex){
            System.out.println("Erro ao buscar paciente: " + ex.getMessage());
            return null;
        }
    }
    
    public Paciente consultar(int id){
        String sql = ("SELECT * FROM Paciente WHERE id = ?");
        try{          
            st = this.conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            
            Paciente paciente = new Paciente();
            while (rs.next()) {
                paciente.setIdPaciente(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setData_nasc(rs.getString("data_nasc"));
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setTelefone(rs.getString("telefone"));               
            } 
            System.out.println("Consulta realizada com sucesso");
            return paciente;
        }
        catch(SQLException ex){
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        } finally{
            desconectar();
        }
    }
    
    public List<Paciente> getPaciente(String nome) { 
        String sql = "SELECT * FROM Paciente WHERE nome LIKE ?";
        
        try {
            st = this.conn.prepareStatement(sql);

            st.setString(1,"%" + nome + "%");
            rs = st.executeQuery();    
            
            List<Paciente> lista = new ArrayList<>();
            
            while (rs.next()) {
            Paciente paciente = new Paciente();
          
            paciente.setIdPaciente(rs.getInt("id"));
            paciente.setNome(rs.getString("nome"));
            paciente.setCpf(rs.getString("cpf"));
            paciente.setData_nasc(rs.getString("data_nasc"));
            paciente.setEndereco(rs.getString("endereco"));
            paciente.setTelefone(rs.getString("telefone"));

            lista.add(paciente);                   
            }                  
            
            return lista;   
        } catch (SQLException e) {
            return null;
        }
    }
    
    public Paciente getPaciente(Object id){
        String sql = "SELECT * FROM Paciente WHERE id=?";
        
        try{
            st = this.conn.prepareStatement(sql);
            st.setInt(1, (int) id);
            
            rs = st.executeQuery();
            Paciente paciente = new Paciente();
            
            rs.next();
            paciente.setIdPaciente(rs.getInt("id"));
            paciente.setNome(rs.getString("nome"));
            paciente.setCpf(rs.getString("cpf"));
            paciente.setData_nasc(rs.getString("data_nasc"));
            paciente.setEndereco(rs.getString("endereco"));
            paciente.setTelefone(rs.getString("telefone"));
            
            System.out.println("Consultando paciente");
            return paciente;
        }
        catch(SQLException ex){
            System.out.println("Erro ao buscar paciente: " + ex.getMessage());
            return null;
        }
    }
    
    public int consultarID(String nome){
        String sql = ("SELECT * FROM Paciente WHERE nome LIKE ?");
        try{          
            st = this.conn.prepareStatement(sql);

            st.setString(1, "%" + nome + "%");
            rs = st.executeQuery();

            Paciente paciente = new Paciente();
            
            rs.next();
            paciente.setIdPaciente(rs.getInt("id"));
            paciente.setNome(rs.getString("nome"));
            paciente.setCpf(rs.getString("cpf"));
            paciente.setData_nasc(rs.getString("data_nasc"));
            paciente.setEndereco(rs.getString("endereco"));
            paciente.setTelefone(rs.getString("telefone"));
            
            System.out.println("Consultando paciente");

            return paciente.getIdPaciente();
        }
        catch(SQLException ex){
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return -1;
        }
    }
    
    public boolean Excluir(Object id){
        try {
            st = this.conn.prepareStatement("DELETE FROM Paciente WHERE id = ?");
            st.setInt(1, (int) id);
            st.executeUpdate();
            
            System.out.println("Paciente excluido");
            return true;
        } 
        catch (SQLException ex) {
            System.out.println("Erro ao excluir paciente: " + ex.getMessage());
            return false;
        }
    }
    
    public int atualizar(Paciente paciente, int id){
        int status;
        try {
            st = conn.prepareStatement("UPDATE Paciente SET nome = ?, cpf = ?, data_nasc = ?, endereco = ?, telefone = ? where id = ?");
            st.setString(1,paciente.getNome());
            st.setString(2,paciente.getCpf());
            st.setString(3,paciente.getData_nasc());
            st.setString(4,paciente.getEndereco());
            st.setString(5,paciente.getTelefone());
            st.setInt(6, id);
            
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
