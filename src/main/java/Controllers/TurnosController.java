package Controllers;
import Models.TurnosModel;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cordeiro
 */
public class TurnosController {

    private static Component rootPane;
    
    // Função responsável por chamar o model que irá fazer á inserção no banco de dados
    public static void cadastraTurno(String diaTurno, String tipoTurno, int idFuncionario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException
    {        
        TurnosModel turnoModel = new TurnosModel(SystemController.formatDateDB(diaTurno), tipoTurno, idFuncionario);
        
        if (!turnoModel.validaTurno())
        {
            turnoModel.insertTurno();
            
            JOptionPane.showMessageDialog(rootPane,"Turno inserido com sucesso!");
        }
        else 
        {
            JOptionPane.showMessageDialog(rootPane, "Este funcionario não pode agendar um novo turno!");
        }                       
    }
    
    // Função responsável por adicionar os turnos na tabela da view ListaTurnos
    public static void listaTurnos(JTable Itable) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException
    {
        // Resultset usado para listar as informações na tabela
        ResultSet rsValue = TurnosModel.getTurnos("todos");                
        // Resultset usado para contar a quantidade de resultados obtidos
        ResultSet rsCount = TurnosModel.getTurnos("todos");                        
        
        String horaI = "";
        String horaF = "";
        int size = 0;
        int c = 0;        
        
        while(rsCount.next())
        {
            size += 1;
        }
                
        DefaultTableModel tbl = (DefaultTableModel) Itable.getModel();
        
        tbl.setRowCount(size);
        Itable.setModel(tbl);               
        
        // Percorre e adiciona os valores na tabela
        while(rsValue.next())
        {           
            switch(rsValue.getString("tipoTurno"))
            {
                case "Noturno":
                    horaI = "19:00";
                    horaF = "07:00";
                    break;
                case "Diurno":
                    horaI = "07:00";
                    horaF = "19:00";
                    break;
            }
            
            Itable.setValueAt(rsValue.getString("codFuncionario"), c, 0);  
            Itable.setValueAt(rsValue.getString("nomeFuncionario"), c, 1);  
            Itable.setValueAt(SystemController.formatDateUser(rsValue.getString("diaTurno")), c, 2);
            Itable.setValueAt(horaI, c, 3);
            Itable.setValueAt(horaF, c, 4);      
            Itable.setValueAt(rsValue.getString("tipoTurno"), c, 5);                                                
            
            c += 1; 
        }        
    }
    
    // Função responsável por adicionar os turnos do dia atual na tabela da dashboard
    public static void listaTurnosHoje(JTable Itable) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException
    {
        // Resultset usado para listar as informações na tabela
        ResultSet rsValue = TurnosModel.getTurnos("diaAtual");                
        // Resultset usado para contar a quantidade de resultados obtidos
        ResultSet rsCount = TurnosModel.getTurnos("diaAtual");                        
        
        String horaI = "";
        String horaF = "";
        int size = 0;
        int c = 0;        
        
        while(rsCount.next())
        {
            size += 1;
        }
                
        DefaultTableModel tbl = (DefaultTableModel) Itable.getModel();
        
        tbl.setRowCount(size);
        Itable.setModel(tbl);               
        
        // Percorre e adiciona os valores na tabela
        while(rsValue.next())
        {           
            switch(rsValue.getString("tipoTurno"))
            {
                case "Noturno":
                    horaI = "19:00";
                    horaF = "07:00";
                    break;
                case "Diurno":
                    horaI = "07:00";
                    horaF = "19:00";
                    break;
            }
            
            Itable.setValueAt(rsValue.getString("codFuncionario"), c, 0);  
            Itable.setValueAt(rsValue.getString("nomeFuncionario"), c, 1);  
            Itable.setValueAt(SystemController.formatDateUser(rsValue.getString("diaTurno")), c, 2);
            Itable.setValueAt(horaI, c, 3);
            Itable.setValueAt(horaF, c, 4);      
            Itable.setValueAt(rsValue.getString("tipoTurno"), c, 5);                                                
            
            c += 1; 
        }        
    }
}
