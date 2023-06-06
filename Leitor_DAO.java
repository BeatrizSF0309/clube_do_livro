import java.sql.*;

public class Leitor_DAO<NomeLeitor> {

    private Connection conexao;

    public Leitor_DAO() { 
        conexao = new FabricadeConexoes().solicitaConexao("localhost", "clubedolivro", "root", "123456"); //tem que colocar a senha do banco
    }

    public void insere(NomeLeitor a) {

        String sql = "insert into leitor" +
        "(nome) " + 
        "values (?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(2,a.getNome());
            
            

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
}
