package Controllers;
import Models.TurnosModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cordeiro
 */
public class TurnosController {
    
    // Função responsável por chamar o model que irá fazer á inserção no banco de dados
    public static void cadastraTurno(String diaTurno, String tipoTurno, int idFuncionario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException
    {                        
        TurnosModel insere = new TurnosModel(SystemController.formatDateDB(diaTurno), tipoTurno, idFuncionario);
        
        insere.insertTurno();
    }
    
    // Função responsável por adicionar os turnos na tabela da view ListaTurnos
    public static void listaTurnos(JTable Itable) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException
    {
        // Resultset usado para listar as informações na tabela
        ResultSet rsValue = TurnosModel.getTurnos();                
        // Resultset usado para contar a quantidade de resultados obtidos
        ResultSet rsCount = TurnosModel.getTurnos();                        
        
        int size = 9;
        int c = 0;        
        
        while(rsCount.next())
        {
            size += 1;
        }
                
        DefaultTableModel tbl = (DefaultTableModel) Itable.getModel();
        
        tbl.setRowCount(9);
        Itable.setModel(tbl);               
        
        // Percorre e adiciona os valores na tabela
        while(rsValue.next())
        {           
            Itable.setValueAt(rsValue.getString("codFuncionario"), c, 0);  
            Itable.setValueAt(rsValue.getString("nomeFuncionario"), c, 1);  
            Itable.setValueAt(SystemController.formatDateUser(rsValue.getString("diaTurno")), c, 2);
            Itable.setValueAt("07:00", c, 3);
            Itable.setValueAt("19:00", c, 4);      
            Itable.setValueAt(rsValue.getString("tipoTurno"), c, 5);                                                
            
            c += 1; 
        }        
    }
}
