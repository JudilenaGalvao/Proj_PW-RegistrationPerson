package com.example.ProjetoPw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;
public class Vagas_pessoasDao {
    Conexao c;


    private String CADASTRO = "INSERT INTO Vagas_pessoas(nome, idade, telefone, email, arqv_curriculo) VALUES (?, ?, ?, ?, ?)";
    private String REL = "SELECT * FROM Vagas_pessoas ORDER BY id";
    private String BUSCARPESSOAID = "SELECT * FROM Vagas_pessoas WHERE id = ?";
    private String DELETARPESSOAID = "DELETE FROM Vagas_pessoas WHERE id = ?";
    private String ATUALIZARPESSOA = "UPDATE Vagas_pessoas SET nome = ?, idade = ?, telefone = ?, email = ?, arqv_curriculo = ? WHERE id = ?";


    public void CadastrarPessoa(Vagas_pessoas p) {
        Connection conection = null;
        PreparedStatement instrucao = null;

        try {
            conection = Conexao.getConnection();
            instrucao = conection.prepareStatement(CADASTRO);
            instrucao.setString(1, p.getNome());
            instrucao.setInt(2, p.getIdade());
            instrucao.setString(3, p.getTelefone());
            instrucao.setString(4, p.getEmail());
            instrucao.setString(5, p.getArqv_curriculo());
            instrucao.executeUpdate();
            conection.close();

        }catch(Exception e) {
            System.out.println("Erro no Cadastro");
        }
    }


    public ArrayList<Vagas_pessoas> listarTodasPessoas(){
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement instrucao = null;
        Vagas_pessoas vagas_pessoas;
        ArrayList<Vagas_pessoas> lista = new ArrayList<Vagas_pessoas>();
        try {
            connection = Conexao.getConnection();
            instrucao = connection.prepareStatement(REL); // "new" do Statement - cria uma instrucao
            rs = instrucao.executeQuery();
            while(rs.next()) { //andando no resultset
                vagas_pessoas = new Vagas_pessoas(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getString("telefone"), rs.getString("email"), rs.getString("arqv_curriculo"));
                lista.add(vagas_pessoas);
            }
            connection.close();


        }catch(Exception e) {
            System.out.println("Erro no relatorio");
        }
        return lista;

    }



    public Vagas_pessoas buscarpessoaid(int id) {
        Vagas_pessoas vp = null;
        Connection connection = null;
        PreparedStatement instrucao = null;
        ResultSet rs = null;

        try {
            connection = Conexao.getConnection();
            instrucao = connection.prepareStatement(BUSCARPESSOAID);
            instrucao.setInt(1, id);
            rs = instrucao.executeQuery();
            if(rs.next()) {
                vp = new Vagas_pessoas(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getString("telefone"), rs.getString("email"), rs.getString("arqv_curriculo"));
            }connection.close();

        }catch(Exception e) {
            System.out.println("Erro no Buscar");
        }
        return vp;
    }



    public void deletarpessoa(Vagas_pessoas p) {
        Connection connection = null;
        PreparedStatement instrucao = null;
        try {
            connection = Conexao.getConnection();
            instrucao = connection.prepareStatement(DELETARPESSOAID);
            instrucao.setInt(1, p.getId());
            instrucao.executeUpdate();
            connection.close();

        }catch(Exception e) {
            System.out.println("Erro no deletar");
        }
    }



    public void atualizarpessoa(Vagas_pessoas p) {
        Connection connection = null;
        PreparedStatement instrucao = null;
        try {
            connection = Conexao.getConnection();
            instrucao = connection.prepareStatement(ATUALIZARPESSOA);
            instrucao.setString(1, p.getNome());
            instrucao.setInt(2, p.getIdade());
            instrucao.setString(3, p.getTelefone());
            instrucao.setString(4, p.getEmail());
            instrucao.setString(5, p.getArqv_curriculo());
            instrucao.setInt(6, p.getId());
            instrucao.executeUpdate();
            connection.close();
        }catch(Exception e) {
            System.out.println("Erro ao atualizar");
        }
    }

}
