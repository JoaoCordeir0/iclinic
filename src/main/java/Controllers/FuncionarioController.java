package Controllers;
import Models.FuncionarioModel;
import java.awt.Component;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
/**
 *
 * @author Lucas Gabryel
 */
public class FuncionarioController {

    private static Component rootPane;
     
    public static void CadastrarFuncionario(String nome, String cargo, String codigo) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        FuncionarioModel funcionarioModel = new FuncionarioModel(nome, cargo, codigo);
        
        if (!funcionarioModel.validaCodigo())
        {
            funcionarioModel.cadastraFuncionario();
            
            JOptionPane.showMessageDialog(rootPane,"Funcionario inserido com sucesso!");
        }
        else 
        {
            JOptionPane.showMessageDialog(rootPane, "Este código já está cadastrado!");
        }
    }
    
    public static void listarFuncionarios(JComboBox comboBox) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        // Resultset usado para listar as informações na tabela
        ResultSet rsValue = FuncionarioModel.getFuncionario();                
               
        while(rsValue.next())
        {
            comboBox.addItem(rsValue.getInt("idFuncionario")+ "- " +rsValue.getString("nomeFuncionario"));
        }
    }
    
}
