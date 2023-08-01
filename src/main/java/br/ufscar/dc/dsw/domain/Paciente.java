package br.ufscar.dc.dsw.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.validation.constraints.Size;

import br.ufscar.dc.dsw.validation.UniqueCPF;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
public class Paciente extends Usuario {
    
    @UniqueCPF (message = "{Unique.paciente.CPF}")
    @NotBlank
    @Size(min = 11, max = 11, message = "{Size.paciente.CPF}")
    @Column(nullable = true, length = 11, unique = true)
    private String CPF;

    @NotBlank
    @Size(min = 11, max = 11, message = "{Size.paciente.telefone}")
    @Column(nullable = true, length = 11, unique = true)
    private String telefone;
    
    @NotBlank
    @Column(nullable = true, length = 1, unique = false)
    private String sexo;

    @Column(nullable = true, length = 64, unique = false)
    private Date data_nascimento;

    private String stringData_nascimento;

    public String getCPF() {
		return CPF;
	}
	
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

    public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

    public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

    public Date getData_Nascimento(){
        return data_nascimento;
    }

    public void setData_Nascimento(Date data_nascimento){
        this.data_nascimento = data_nascimento;

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
        this.stringData_nascimento = dateFormat.format(this.data_nascimento);
    }

    public String getStringData_Nascimento(){ 
        return stringData_nascimento;
    }

    public void setStringData_Nascimento(String data_nascimento_str){
        this.stringData_nascimento = data_nascimento_str;

        LocalDate localDate = LocalDate.parse(data_nascimento_str);
        this.data_nascimento = Date.valueOf(localDate);
    }

}
