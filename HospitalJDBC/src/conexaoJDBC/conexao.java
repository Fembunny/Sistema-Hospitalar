package conexaoJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class conexao {
    public Connection getConexao() { 
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            System.out.println( "Driver JDBC carregado" );
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalPI","root","fembunny9847");
            System.out.println( "Conexao com o banco de dados estabelecida." );
            
            return conn;
        } 
        catch (ClassNotFoundException | SQLException sqle ) {
            System.out.println( "Erro na conexao ao Bando de Dados : " + sqle.getMessage() );
            return null;
        }
    } 
   
}
