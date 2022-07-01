package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import commons.util.EntityClass;

@Entity
public class Conteiner extends EntityClass<Conteiner> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	
	@Column(name="numero")
	private String numero;
	
	@Column(name="status")
	private String status;
	
	@Column(name="categoria")
	private String categoria;
	
	@Column(name="cliente")
	private String cliente;
	
	@Column(name="tipo")
	private String tipo;
	
	
	
	
	
	public String getNumero() {
		return numero;
	}





	public void setNumero(String numero) {
		this.numero = numero;
	}





	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}





	public String getCategoria() {
		return categoria;
	}





	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}





	public String getCliente() {
		return cliente;
	}





	public void setCliente(String cliente) {
		this.cliente = cliente;
	}





	public String getTipo() {
		return tipo;
	}





	public void setTipo(String tipo) {
		this.tipo = tipo;
	}





	public void setId(Long id) {
		this.id = id;
	}





	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
