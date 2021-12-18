package modelo;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
import java.util.ArrayList;


public class Contato {
	private String nome;
	private LocalDate nascimento;
	private String endereco;
	private ArrayList<Telefone> telefones = new ArrayList<Telefone>();

	
	public Contato(String nome, LocalDate nascimento, String endereco) {
		this.nome = nome;
		this.nascimento = nascimento;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	
	public String getNascimentoStr() {
		return nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public ArrayList<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(ArrayList<Telefone> telefones) {
		this.telefones = telefones;
	}

	public void adicionar(Telefone p){
		telefones.add(p);
	}
	public void remover(Telefone p){
		telefones.remove(p);
	}
	public Telefone localizarTelefone(String numero){
		for(Telefone p : telefones){
			if(p.getNumero().equals(numero))
				return p;
		}
		return null;
	}
	
	public int getIdade() throws Exception {
		//calcular a idade usando a data de nascimento e data do computador
		LocalDate dataNasc;
		LocalDate hoje = LocalDate.now();
		String nascimento = getNascimentoStr();
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dataNasc = LocalDate.parse(nascimento, parser);

		Period p = Period.between(dataNasc, hoje);
		return p.getYears();
	}


	@Override
	public String toString() {
		String texto = "nome=" + nome + ", nascimento="+getNascimentoStr() + ", endereco="+endereco;
		texto += ", telefones:";
		if (telefones.isEmpty())
			texto += " vazia";
		else 	
			for(Telefone p: telefones) 
				texto += " " + p.getNumero() ;

		return texto ;
	}


}
	


