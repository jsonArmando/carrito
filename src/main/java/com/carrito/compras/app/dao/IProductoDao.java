package com.carrito.compras.app.dao;

import java.util.List;

import com.carrito.compras.app.entity.Productos;

public interface IProductoDao {
	public List<Productos> findAll();
	public List<Productos> findMarca(String marca);
	public List<Productos> findRangePrices(Integer priceMin,Integer priceMax);
	public List<Productos> findName(String nombre);
}
