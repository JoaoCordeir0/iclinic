package Models;
import Core.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cordeiro
 */
public class TurnosModel {
        
    String diaTurno;
    String tipoTurno;
    int idFuncionario;
    
    public TurnosModel(String diaTurno, String tipoTurno, int idFuncionario)
    {
        this.diaTurno = diaTurno;
        this.tipoTurno = tipoTurno;
        this.idFuncionario = idFuncionario;
    }       
    
    public String getDiaTurno() {
        return diaTurno;
    }

    public String getTipoTurno() {
        return tipoTurno;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setDiaTurno(String diaTurno) {
        this.diaTurno = diaTurno;
    }

    public void setTipoTurno(String tipoTurno) {
        this.tipoTurno = tipoTurno;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
    
    // Função responsável por inserir no banco de dados o novo turno
    public boolean insertTurno() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        Connection conn = Database.createConexao();
        
        String sql = "INSERT INTO turnos (diaTurno, tipoTurno, idFuncionario) VALUES (?,?,?)";                                
        
        // Preparar a sintaxe básica do sql a ser executado
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        // Injeta os valores que devem compor o SQL
        comandoSQL.setString(1, getDiaTurno());
        comandoSQL.setString(2, getTipoTurno());
        comandoSQL.setInt(3, getIdFuncionario());

        // Executar o comando no mysql
        comandoSQL.executeUpdate();

        // Fecha os recursos
        comandoSQL.close();
        conn.close();
       
        return true;
    }          
    
    // Função responsável por retornar a listagem de turnos inseridos no banco de dados
    public static ResultSet getTurnos() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Connection conn = Database.createConexao();
        
        String sql = "SELECT DISTINCT(t.idTurno), f.idFuncionario, f.codFuncionario, f.nomeFuncionario, f.nomeCargo, t.tipoTurno, t.diaTurno FROM turnos as t INNER JOIN funcionario as f ON t.idFuncionario = f.idFuncionario";
        
        ResultSet rs = Database.execSelect(conn, sql);
            
        return rs;
    }    
}
