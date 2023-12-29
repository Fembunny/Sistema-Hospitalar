
package dao;

import beans.Medicamento;
import beans.Paciente;
import conexaoJDBC.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MedicamentoDAO {
    private conexao conexao;
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    
    public MedicamentoDAO() {
        this.conexao = new conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public static final List<Medicamento> remedios = new ArrayList<>();
    
    public int Salvar(Medicamento medicamento){
        int status;
        try{
            st = conn.prepareStatement("INSERT INTO Remedio (classe, nome, quantidade, valor) VALUES (?, ?, ?, ?)");
            st.setString(1, medicamento.getClasse());
            st.setString(2, medicamento.getNome());
            st.setInt(3, medicamento.getQuantidade());
            st.setDouble(4, medicamento.getValor());
            
            
            status = st.executeUpdate();
            
            System.out.println("Medicamento cadastrado com sucesso");
            return status;
        } catch(SQLException ex){
            System.out.println("Erro ao conectar - " + ex.getMessage());
            return ex.getErrorCode();
        } finally{
            desconectar();
        }
    }
    
    public List<Medicamento> getMedicamento(String nome) { 
        String sql = "SELECT * FROM Remedio WHERE nome LIKE ?";
        
        try {
            st = this.conn.prepareStatement(sql);

            st.setString(1,"%" + nome + "%");
            rs = st.executeQuery();    
            
            List<Medicamento> lista = new ArrayList<>();
            
            while (rs.next()) {
            Medicamento medicamento = new Medicamento();
            
            medicamento.setIdRemedio(rs.getInt("id"));
            medicamento.setClasse(rs.getString("classe"));
            medicamento.setNome(rs.getString("nome"));
            medicamento.setQuantidade(rs.getInt("quantidade"));
            medicamento.setValor(rs.getDouble("valor")); 

            lista.add(medicamento);                   
            }                  
            
            return lista;   
        } catch (SQLException e) {
            return null;
        }
    }
    
    public Medicamento getMedicamento(Object id){
        String sql = "SELECT * FROM Remedio WHERE id=?";
        
        try{
            PreparedStatement st = this.conn.prepareStatement(sql);
            st.setInt(1, (int) id);
            
            ResultSet rs = st.executeQuery();
            Medicamento medicamento = new Medicamento();
            
            rs.next();
            medicamento.setIdRemedio(rs.getInt("id"));
            medicamento.setClasse(rs.getString("classe"));
            medicamento.setNome(rs.getString("nome"));
            medicamento.setQuantidade(rs.getInt("quantidade"));
            medicamento.setValor(rs.getDouble("valor"));
            
            System.out.println("Consultando medicamento");
            return medicamento;
        }
        catch(SQLException ex){
            System.out.println("Erro ao buscar medicamento: " + ex.getMessage());
            return null;
        } finally{
            desconectar();
        }
    }
    
    public int consultarID(int id){
        String sql = ("SELECT * FROM Remedio WHERE id = ?");
        try{          
            st = this.conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            
            Medicamento medicamento = new Medicamento();
            while (rs.next()) {
                medicamento.setIdRemedio(rs.getInt("id"));
                medicamento.setClasse(rs.getString("classe"));
                medicamento.setNome(rs.getString("nome"));
                medicamento.setQuantidade(rs.getInt("quantidade"));
                medicamento.setValor(rs.getDouble("valor"));                
            } 
            System.out.println("Consulta realizada com sucesso");
            return medicamento.getIdRemedio();
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
            st = this.conn.prepareStatement("DELETE FROM Remedio WHERE id = ?");
            st.setInt(1, (int) id);
            st.executeUpdate();
            
            System.out.println("Medicamento excluido");
            return true;
        } 
        catch (SQLException ex) {
            System.out.println("Erro ao medicamento : " + ex.getMessage());
            return false;
        } finally{
            desconectar();
        }
    }
    
    public int atualizar(Medicamento medicamento, int id){
        int status;
        try {
            st = conn.prepareStatement("UPDATE Remedio SET classe = ?, nome = ?, quantidade = ?, valor = ? where id = ?");
            
            st.setString(1,medicamento.getClasse());
            st.setString(2,medicamento.getNome());
            st.setInt(3,medicamento.getQuantidade());
            st.setDouble(4,medicamento.getValor());
            st.setInt(5, id);
            
            status = st.executeUpdate();
            
            System.out.println("Medicamento atualizado com sucesso");
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
        } finally{
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
