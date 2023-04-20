package com.example.ProjetoPw;

public class Vagas_pessoas {

        private Integer id;
        private String nome;
        private Integer idade;
        private String telefone;
        private String email;
        private String arqv_curriculo;

        public Vagas_pessoas(Integer id, String nome, Integer idade, String telefone, String email, String arqv_curriculo) {
            this.id = id;
            this.nome = nome;
            this.idade = idade;
            this.telefone = telefone;
            this.email = email;
            this.arqv_curriculo = arqv_curriculo;
        }

        public Vagas_pessoas(){



        }



    public Integer getId() {
        return id;
    }

    public Vagas_pessoas(Integer id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArqv_curriculo() {
        return arqv_curriculo;
    }

    public void setArqv_curriculo(String arqv_curriculo) {
        this.arqv_curriculo = arqv_curriculo;
    }

}
