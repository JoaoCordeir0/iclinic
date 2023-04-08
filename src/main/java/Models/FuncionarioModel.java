package Models;

import Core.Database;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lucas Gabryel
 */
public class FuncionarioModel {
    
    String cod;
    String nome;
    String cargo;
    
    // Construtor
    public FuncionarioModel(String nome, String cargo, String codigo)
    {
        this.nome = nome;
        this.cargo = cargo;
        this.cod = codigo;
    }

    // Gets
    public String getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    // Sets
    public String getCargo() {
        return cargo;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }       
    
    // Função responsável por cadastrar o funcionario no banco de dados
    public boolean cadastraFuncionario() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Connection conn = Database.createConexao();
        
        String sql = "INSERT INTO funcionario(codFuncionario, nomeFuncionario, nomeCargo) VALUES (?,?,?)";
        
        // Prepara a Sitaxe basica de sql a ser executada
        PreparedStatement comandoSQL = conn.prepareStatement(sql);
        
        //Injeta os Valores que devem compor o SQL
        comandoSQL.setString(1, this.cod);
        comandoSQL.setString(2, this.nome);
        comandoSQL.setString(3, this.cargo);
        
        //Executa o comando do Mysql
        comandoSQL.executeUpdate();
        
        //Fecha os recursos
        comandoSQL.close();
        conn.close();
        
        return true;
    }
      
    // Função responsável por retornar a listagem de funcionario inseridos no banco de dados
    public static ResultSet getFuncionario () throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Connection conn = Database.createConexao();
        
        String sql = "SELECT idFuncionario, nomeFuncionario FROM funcionario;";
        
        ResultSet rs = Database.execSelect(conn, sql);
        
        return rs;
    }
    
    // Função responsável por validar se o código fornecido pelo usuário não existe no banco, ou seja, não está cadastrado
    public boolean validaCodigo() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        boolean achou = false;
        
        Connection conn = Database.createConexao();
        
        String sql = "SELECT codFuncionario, nomeCargo FROM funcionario;";
        
        ResultSet rs = Database.execSelect(conn, sql);
        
        while(rs.next())
        {
            if (rs.getString("codFuncionario").equals(getCod()) && rs.getString("nomeCargo").equals(getCargo()))
            {
                achou = true;
            }
        }
        
        return achou;
    }
    
}
