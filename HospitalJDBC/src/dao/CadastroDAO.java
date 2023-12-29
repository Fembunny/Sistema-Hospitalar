
package dao;

import beans.Consulta;
import beans.Medico;
import beans.Paciente;
import conexaoJDBC.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CadastroDAO {
    
    private conexao conexao;
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    
    public CadastroDAO() {
        this.conexao = new conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public static final List<Consulta> consultas = new ArrayList<>();
    
    public int Salvar(Consulta consulta){
        int status;
        try{
            st = conn.prepareStatement("INSERT INTO Consulta (idMedico, medicoEspecialidade, idPaciente, data_hora, valor) VALUES (?, ?, ?, ?, ?)");
            
            st.setInt(1, consulta.getMedico().getIdMedico());
            st.setString(2, consulta.getMedico().getEspecialidade());
            st.setInt(3, consulta.getPaciente().getIdPaciente());
            st.setString(4, consulta.getData());
            st.setDouble(5, consulta.getValor());
            
            
            status = st.executeUpdate();
            
            System.out.println("Consulta  marcada com sucesso");
            return status;
        }
        catch(SQLException ex){
            System.out.println("Erro ao conectar - " + ex.getMessage());
            return ex.getErrorCode();
        } finally{
            desconectar();
        }
    }
    
    public List<Consulta> getConsulta() { 
        String sql = "SELECT * FROM Consulta";
        
        try {
            st = this.conn.prepareStatement(sql); 
            rs = st.executeQuery();
            
            List<Consulta> lista = new ArrayList<>();
            
            while (rs.next()) {
            Consulta consulta = new Consulta(); 
            MedicoDAO medicoDAO = new MedicoDAO();
            PacienteDAO pacienteDAO = new PacienteDAO();
            
            consulta.setMedico(medicoDAO.consultar(rs.getInt("idMedico")));
            consulta.setPaciente(pacienteDAO.consultar(rs.getInt("idPaciente")));
          
            consulta.setIdConsulta(rs.getInt("id"));
            consulta.getMedico().setIdMedico(rs.getInt("idMedico"));
            consulta.getMedico().setEspecialidade(rs.getString("medicoEspecialidade"));
            consulta.getPaciente().setIdPaciente(rs.getInt("idPaciente"));
            consulta.setData(rs.getString("data_hora"));
            consulta.setValor(rs.getDouble("valor"));

            lista.add(consulta);                   
            }                  
            
            System.out.println("Consultando");
            return lista;   
        }catch(SQLException | NullPointerException ex){
            System.out.println("Erro ao buscar dados: " + ex.getMessage());
            return null;
        } finally {
            desconectar();
        }
    }
    
    public List<Consulta> getConsulta(int idPaciente) { 
        String sql = "SELECT * FROM Consulta WHERE idPaciente = ?";
        
        try {
            st = this.conn.prepareStatement(sql); 
            
            st.setInt(1, idPaciente);
            rs = st.executeQuery();
            
            List<Consulta> lista = new ArrayList<>();
            
            while (rs.next()) {
            Consulta consulta = new Consulta();
            
            MedicoDAO medicoDAO = new MedicoDAO();
            Medico medico = new Medico();
            
            PacienteDAO pacienteDAO = new PacienteDAO();
            Paciente paciente = new Paciente();
            
            //medico.setIdMedico(medicoDAO.consultarID(rs.getInt("idMedico")));
            
            consulta.setMedico(medicoDAO.consultar(rs.getInt("idMedico")));
            consulta.setPaciente(pacienteDAO.consultar(rs.getInt("idPaciente")));
          
            consulta.setIdConsulta(rs.getInt("id"));
            consulta.getMedico().setIdMedico(rs.getInt("idMedico"));
            consulta.getMedico().setEspecialidade(rs.getString("medicoEspecialidade"));
            consulta.getPaciente().setIdPaciente(rs.getInt("idPaciente"));
            consulta.setData(rs.getString("data_hora"));
            consulta.setValor(rs.getDouble("valor"));

            lista.add(consulta);                   
            }                  
            
            System.out.println("Consultando");
            return lista;   
        }catch(SQLException | NullPointerException ex){
            System.out.println("Erro ao buscar dados: " + ex.getMessage());
            return null;
        } finally {
            desconectar();
        }
    }
    
    public Consulta getConsulta(Object id){
        String sql = "SELECT * FROM Consulta WHERE id=?";
        
        try{
            PreparedStatement st = this.conn.prepareStatement(sql);
            st.setInt(1, (int) id);
            
            ResultSet rs = st.executeQuery();
            Consulta consulta = new Consulta();
            
            rs.next();
            consulta.setIdConsulta(rs.getInt("id"));
            consulta.getMedico().setIdMedico(rs.getInt("idMedico"));
            consulta.getPaciente().setIdPaciente(rs.getInt("idPaciente"));
            consulta.setData(rs.getString("data_hora"));
            consulta.setValor(rs.getDouble("valor"));
            
            System.out.println("Consultando");
            return consulta;
        }
        catch(SQLException ex){
            System.out.println("Erro ao buscar paciente: " + ex.getMessage());
            return null;
        } finally {
            desconectar();
        }
    }
    
    public int consultarID(int id){
        String sql = ("SELECT * FROM Consulta WHERE id = ?");
        try{          
            st = this.conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            
            Consulta consulta = new Consulta();
            while (rs.next()) {
                consulta.setIdConsulta(rs.getInt("id"));
                consulta.getMedico().setIdMedico(rs.getInt("idMedico"));
                consulta.getPaciente().setIdPaciente(rs.getInt("idPaciente"));
                consulta.setData(rs.getString("data_hora"));
                consulta.setValor(rs.getDouble("valor"));                
            } 
            System.out.println("Consulta realizada com sucesso");
            return consulta.getIdConsulta();
        }
        catch(SQLException ex){
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return -1;
        } finally {
            desconectar();
        }
    }
    
    public boolean Excluir(Object id){
        try {
            st = this.conn.prepareStatement("DELETE FROM Consulta WHERE id = ?");
            st.setInt(1, (int) id);
            st.executeUpdate();
            
            System.out.println("Consulta excluida");
            return true;
        } 
        catch (SQLException ex) {
            System.out.println("Erro ao excluir consulta: " + ex.getMessage());
            return false;
        } finally {
            desconectar();
        }
    }
    
    public int atualizar(Consulta consulta, int id){
        int status;
        try {
            st = conn.prepareStatement("UPDATE Consulta SET idMedico = ?, idPaciente = ?, data_hora = ?, valor = ? where id = ?");
            st.setInt(1,consulta.getMedico().getIdMedico());
            st.setInt(2, consulta.getPaciente().getIdPaciente());
            st.setString(3 , consulta.getData());
            st.setDouble(4, consulta.getValor());        
            st.setInt(5, id);
            
            status = st.executeUpdate();
            
            System.out.println("Consulta atualizada com sucesso");
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
        } finally {
            desconectar();
        }
    }
    
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
            
        }
    }
}
