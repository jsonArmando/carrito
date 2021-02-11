package com.carrito.compras.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table(name = "factura")
public class Factura implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Factura() {
	}
	
	public Factura(Long id, String nombre, String marca, Long precio, Long cantidad, String estado, Integer porcentaje,
			Long precioTotal, Date createAt) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.marca = marca;
		this.precio = precio;
		this.cantidad = cantidad;
		this.estado = estado;
		this.porcentaje = porcentaje;
		this.precioTotal = precioTotal;
		this.createAt = createAt;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String marca;
	private Long precio;
	private Long cantidad;
	private String estado;
	private Integer porcentaje;
	private Long precioTotal;
	@NotNull
	@Column(name = "create_at")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Long getPrecio() {
		return precio;
	}
	public void setPrecio(Long precio) {
		this.precio = precio;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}
	public Long getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(Long precioTotal) {
		this.precioTotal = precioTotal;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	@Override
	public String toString() {
		return "Factura [id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", precio=" + precio + ", cantidad="
				+ cantidad + ", estado=" + estado + ", porcentaje=" + porcentaje + ", precioTotal=" + precioTotal
				+ ", createAt=" + createAt + "]";
	}
	
	

}
