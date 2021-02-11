package com.carrito.compras.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.carrito.compras.app.entity.Factura;
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
	public List<Productos> findRangePrices(Long priceMin, Long priceMax) {
		Query query = em.createQuery("SELECT u FROM Productos u WHERE u.precio BETWEEN :priceMin AND :priceMax");
		query.setParameter("priceMin", priceMin);
		query.setParameter("priceMax", priceMax);
		List<Productos> resultList = query.getResultList();
		resultList.forEach(System.out::println);
		em.close();
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Productos> findName(String nombre) {
		System.out.println("-- Productos name LIKE D% --");
		List<Productos> resultList = em.createQuery("SELECT u FROM Productos u WHERE u.nombre LIKE :nombre").setParameter("nombre", "%"+nombre+"%").getResultList();
		return resultList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Productos findOne(Long id) {
		return em.find(Productos.class, id);
	}

	@Override
	@Transactional
	public void deleteLong(Long id) {
		Productos productos = findOne(id);
		em.remove(productos);	
	}

	@Override
	@Transactional
	public void save(Factura factura) {
		if (factura.getId() != null && factura.getId() > 0) {
			em.merge(factura);
		}else {
			em.persist(factura);
		}
		
	}

}
