package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Consulta")
public class Consulta extends AbstractEntity<Long> {
  
	@NotBlank
    @Size(min = 11, max = 11, message = "{Size.consulta.CPF}")
    @Column(nullable = false, length = 60)
    private String CPFpaciente;
    
	@NotBlank
    @Size(min = 8, max = 8, message = "{Size.consulta.CRM}")
    @Column(nullable = false, length = 60)
    private String CRMmedico;
       
    @Column(nullable = false, length = 60)
    private Date data_consulta;
    
    @Column(nullable = false, length = 60)
    private Time hora;

	private String stringData_consulta;
	private String stringHora;
		
	public String getCPFpaciente() {
		return CPFpaciente;
	}
	
	public void setCPFpaciente(String CPFpaciente) {
		this.CPFpaciente = CPFpaciente;
	}
	
	public String getCRMmedico() {
		return CRMmedico;
	}
	
	public void setCRMmedico(String CRMmedico) {
		this.CRMmedico = CRMmedico;
	}
	
	public Date getdata_consulta() {
		return data_consulta;
	}
	
	public void setdata_consulta(Date data_consulta) {
		this.data_consulta = data_consulta;

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
        this.stringData_consulta = dateFormat.format(this.data_consulta);
	}	
	
	public Time gethora() {
		return hora;
	}

	public void sethora(Time hora) {
		this.hora = hora;

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		this.stringHora = sdf.format(this.hora);
	}

	public String getStringData_consulta(){ 
        return stringData_consulta;
    }

    public void setStringData_consulta(String data_consulta_str){

        this.stringData_consulta = data_consulta_str;

        LocalDate localDate = LocalDate.parse(data_consulta_str);
        this.data_consulta = Date.valueOf(localDate);
    }

	public String getStringHora() {
		return stringHora;
	}

	public void setStringHora(String hora_str) {
		this.stringHora = hora_str;

		this.hora = Time.valueOf(hora_str+":00");
	}
}