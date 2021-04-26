package br.com.hepta.teste.entity;

public class Funcionario {

    private Integer id;

    private String nome;

    private Setor setor;

    private Double salario;

    private String email;

    private Integer idade;

    public Funcionario() {}

    public Funcionario(Integer id, String nome, Setor setor, Double salario, String email, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.setor = setor;
        this.salario = salario;
        this.email = email;
        this.idade = idade;
    }

    public Funcionario(String nome, Setor setor, Double salario, String email, Integer idade) {
        this.nome = nome;
        this.setor = setor;
        this.salario = salario;
        this.email = email;
        this.idade = idade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
