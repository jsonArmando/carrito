package com.carrito.compras.app.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrito.compras.app.entity.Productos;


public interface ProductosRepository extends JpaRepository<Productos, Integer>{

}
