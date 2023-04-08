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
        TurnosModel turnoModel = new TurnosModel(0, SystemController.formatDateDB(diaTurno), tipoTurno, idFuncionario);
        
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
    public static void listaTurnos(JTable Itable, String option) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException
    {
        // Resultset usado para listar as informações na tabela
        ResultSet rsValue = TurnosModel.getTurnos(option);                
        // Resultset usado para contar a quantidade de resultados obtidos
        ResultSet rsCount = TurnosModel.getTurnos(option);                        
        
        String horaI = "";
        String horaF = "";
        String codigo = "";
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
            
            switch(rsValue.getString("nomeCargo"))
            {
                case "Médico":
                    codigo = "CRM: ";
                    break;
                case "Residente":
                    codigo = "CRM: ";
                    break;
                case "Enfermeiro":
                    codigo = "COREN: ";
                    break;
            }
            
            Itable.setValueAt(rsValue.getString("idTurno"), c, 0);  
            Itable.setValueAt(codigo + rsValue.getString("codFuncionario"), c, 1);  
            Itable.setValueAt(rsValue.getString("nomeFuncionario"), c, 2);  
            Itable.setValueAt(rsValue.getString("nomeCargo"), c, 3);  
            Itable.setValueAt(SystemController.formatDateUser(rsValue.getString("diaTurno")), c, 4);            
            Itable.setValueAt(horaI + " até " + horaF, c, 5);      
            Itable.setValueAt(rsValue.getString("tipoTurno"), c, 6);                                                    
            
            c += 1; 
        }        
    }        
    
    // Função responsável por exibir mensagem de aviso e chamar o model que exclui o turno
    public static void excluiTurno(JTable Itable) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException
    {          
        if (Itable.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(rootPane, "Selecione uma linha antes de excluir");
        }
        else
        {
            // Coleta as informações da linha da tabela de acordo com o que o usuário selecionou                                 
            int idTurno = Integer.parseInt(Itable.getValueAt(Itable.getSelectedRow(), 0).toString());
            String diaTurno = Itable.getValueAt(Itable.getSelectedRow(), 4).toString();
            String tipoTurno = Itable.getValueAt(Itable.getSelectedRow(), 6).toString();
            
            int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Warning", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION)
            {
                // Instanceia o model para proseguir com a exclusão do usuário no banco de dados                
                (new TurnosModel(idTurno, diaTurno, tipoTurno, 0)).excluiTurno();
                
                JOptionPane.showMessageDialog(rootPane, "Turno apagado com sucesso!");
                               
                TurnosController.listaTurnos(Itable, "todos");
            }
        }        
    }
    
    // Função responsável por adicionar as informações de um determinado turno que será editado no array
    public static String[] getInformacoesTurno(int idTurno) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
    {     
        String[] array = new String[10];
        
        ResultSet rs = TurnosModel.getInformacoesTurno(idTurno);
        
        while(rs.next())
        {
            array[0] = rs.getString("diaTurno");           
            array[1] = rs.getString("nomeFuncionario");
            array[2] = rs.getString("tipoTurno");
        }
        
        return array;
    }
}
