import java.sql.*;
public class FabricadeConexoes {
    
    public Connection solicitaConexao(String host, String banco, String leitor) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("\nOcorreu um Problema! \nDriver não encontrado!!!\n+e.toString()");
            System.exit(1);
        }

        try {
            return DriverManager.getConnection("jdbc:mysql://" + host + "/" + banco, leitor);
        } catch (SQLException e) {
            System.out.println("\nOcorreu um Problema! \nBanco de Dados não encontrado!!!\n+e.toString()");
            System.exit(2);
            return null;
        }
    }
}
