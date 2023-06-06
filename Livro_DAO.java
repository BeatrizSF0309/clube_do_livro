import java.sql.*;

public class Livro_DAO {
    private Connection conexao;

    public Livro_DAO() { 
        conexao = new FabricadeConexoes().solicitaConexao("localhost", "clubedolivro", "root", "123456"); //tem que colocar a senha do banco
    }

    public void insere(Livro a) {

        String sql = "insert into livro " +
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
