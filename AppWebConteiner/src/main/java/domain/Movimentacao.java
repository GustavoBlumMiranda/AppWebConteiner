package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import commons.util.EntityClass;

@Entity
public class Movimentacao extends EntityClass<Movimentacao> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	
	@Column(name="tipo")
	private String tipo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dataHoraInicio")
	private Date dataHoraInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dataHoraFim")
	private Date dataHoraFim;
	
	@ManyToOne
	@JoinColumn(name = "idConteiner")
	Conteiner conteiner;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public Conteiner getConteiner() {
		return conteiner;
	}

	public void setConteiner(Conteiner conteiner) {
		this.conteiner = conteiner;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
