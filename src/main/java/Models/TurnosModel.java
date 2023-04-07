package Models;
import Core.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Cordeiro
 */
public class TurnosModel {
    
    String nome;
    Date data;
    String tipoTurno;
    
    public TurnosModel(String nome, Date data, String tipoTurno)
    {
        this.nome = nome;
        this.data = data;
        this.tipoTurno = tipoTurno;
    }       
    
    public boolean insertTurno() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        Connection conn = Database.createConexao();
        
        String sql = "INSERT INTO turnos (diaTurno, tipoTurno, codFuncionario) VALUES (?,?,?)";                                
        
        // Preparar a sintaxe básica do sql a ser executado
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        // Injeta os valores que devem compor o SQL
        comandoSQL.setString(1, this.data.toInstant());
        comandoSQL.setString(2, this.tipoTurno);
        comandoSQL.setInt(3, 1);

        // Executar o comando no mysql
        comandoSQL.executeUpdate();

        // Fecha os recursos
        comandoSQL.close();
        conn.close();
       
        return true;
    }
}
