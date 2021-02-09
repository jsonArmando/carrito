package com.carrito.compras.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.carrito.compras.app.entity.Productos;

@Repository
public class ProductosDaoImpl implements IProductoDao{

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Productos> findAll() {
		return em.createQuery("from Productos").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Productos> findMarca(String marca) {
		List<Productos> resultList = em.createQuery("SELECT u FROM Productos u WHERE u.marca = :marca").setParameter("marca", marca).getResultList();
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Productos> findRangePrices(Integer priceMin, Integer priceMax) {
		Query query = em.createQuery("SELECT u FROM Productos u WHERE u.precio BETWEEN u.precioMin AND u.precioMax");
		query.setParameter("precioMin", priceMin);
		query.setParameter("precioMax", priceMax);
		List<Productos> resultList = query.getResultList();
		resultList.forEach(System.out::println);
		em.close();
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Productos> findName(String nombre) {
		List<Productos> resultList = em.createQuery("SELECT u FROM Productos u WHERE u.nombre LIKE '%nombre%'").setParameter("nombre", nombre).getResultList();
		return resultList;
	}

}
