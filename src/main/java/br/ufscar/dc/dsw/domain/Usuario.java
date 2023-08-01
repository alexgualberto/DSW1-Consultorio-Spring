package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import br.ufscar.dc.dsw.validation.UniqueEmail;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
public class Usuario extends AbstractEntity<Long>{
	
	@UniqueEmail (message = "{Unique.usuario.email}")
	@NotBlank
    @Column(nullable = false, length = 64, unique = true)
    private String email;
    
	@NotBlank
    @Column(nullable = false, length = 64)
    private String password;
       
    @NotBlank
    @Column(nullable = false, length = 64)
    private String name;
    
    @NotBlank
    @Column(nullable = false, length = 64)
    private String role;
    
    @Column(nullable = false)
    private boolean enabled;
		
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}