package com.carrito.compras.app.dao;

import java.util.List;

import com.carrito.compras.app.entity.Factura;
import com.carrito.compras.app.entity.Productos;

public interface IProductoDao {
	public List<Productos> findAll();
	public List<Productos> findMarca(String marca);
	public List<Productos> findRangePrices(Long priceMin,Long priceMax);
	public List<Productos> findName(String nombre);
	public Productos findOne(Long id);
	public void deleteLong(Long id);
	public void save(Factura factura);
	
}
