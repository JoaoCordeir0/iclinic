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
        
    int idTurno;
    String diaTurno;
    String tipoTurno;
    int idFuncionario;
    
    public TurnosModel(int idTurno, String diaTurno, String tipoTurno, int idFuncionario)
    {
        this.idTurno = idTurno;
        this.diaTurno = diaTurno;
        this.tipoTurno = tipoTurno;
        this.idFuncionario = idFuncionario;
    }       

    // Gests
    public int getIdTurno() {
        return idTurno;
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

    // Sets
    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
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
    public static ResultSet getTurnos(String option) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        String sql = "";
        Connection conn = Database.createConexao();
        
        switch(option.toString())
        {
            case "todos":
                sql = "SELECT DISTINCT(t.idTurno), f.idFuncionario, f.codFuncionario, f.nomeFuncionario, f.nomeCargo, t.tipoTurno, t.diaTurno FROM turnos as t INNER JOIN funcionario as f ON t.idFuncionario = f.idFuncionario";
                break;
                
            case "diaAtual":
                sql = "SELECT DISTINCT(t.idTurno), f.idFuncionario, f.codFuncionario, f.nomeFuncionario, f.nomeCargo, t.tipoTurno, t.diaTurno FROM turnos as t INNER JOIN funcionario as f ON t.idFuncionario = f.idFuncionario WHERE t.diaTurno = curdate()";
                break;
        }
                
        ResultSet rs = Database.execSelect(conn, sql);
            
        return rs;
    }    
    
    // Função responsável por validar se o funcionario pode agendar um novo turno utilizando a regra 12/36
    public boolean validaTurno() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {       
        boolean achou = false;
        int index = 0;
        
        Connection conn = Database.createConexao();
        
        String sql = "SELECT diaTurno FROM turnos WHERE idFuncionario = " + getIdFuncionario() + " and DATEDIFF('" + getDiaTurno() + "', diaTurno) < 2";
        
        ResultSet rs = Database.execSelect(conn, sql);
        
        while(rs.next())
        {
            index += 1;
        }
        
        if (index > 0)
        {
            achou = true;
        }
        
        return achou;
    }
    
    // Função responsável por excluir um turno
    public boolean excluiTurno() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Connection conn = Database.createConexao();
        
        String sql = "DELETE FROM turnos WHERE idTurno = ?";                                
        
        // Preparar a sintaxe básica do sql a ser executado
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        // Injeta os valores que devem compor o SQL
        comandoSQL.setInt(1, getIdTurno());

        // Executar o comando no mysql
        comandoSQL.executeUpdate();

        // Fecha os recursos
        comandoSQL.close();
        conn.close();
       
        return true;
    }
    
    public static ResultSet getInformacoesTurno(int idTurno) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Connection conn = Database.createConexao();
        
        String sql = "SELECT diaTurno, nomeFuncionario, tipoTurno, f.idFuncionario FROM turnos as t INNER JOIN funcionario as f ON t.idFuncionario = f.idFuncionario WHERE idTurno = " + idTurno;
        
        ResultSet rs = Database.execSelect(conn, sql);
                       
        return rs;
    }
    
    public boolean alterarTurno() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Connection conn = Database.createConexao();
        
        String sql = "UPDATE turnos SET diaTurno = ?, tipoTurno = ? WHERE idTurno = ?;";                                
        
        // Preparar a sintaxe básica do sql a ser executado
        PreparedStatement comandoSQL = conn.prepareStatement(sql);

        // Injeta os valores que devem compor o SQL
        comandoSQL.setString(1, getDiaTurno());
        comandoSQL.setString(2, getTipoTurno());
        comandoSQL.setInt(3, getIdTurno());

        // Executar o comando no mysql
        comandoSQL.executeUpdate();

        // Fecha os recursos
        comandoSQL.close();
        conn.close();
       
        return true;
    }
}
