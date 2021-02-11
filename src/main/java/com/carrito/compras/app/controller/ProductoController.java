package com.carrito.compras.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carrito.compras.app.dao.IProductoDao;
import com.carrito.compras.app.entity.Factura;
import com.carrito.compras.app.entity.Productos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ProductoController {
	@Autowired
	private IProductoDao iProductoDao;
	
	private static final Logger log = LoggerFactory.getLogger(ProductoController.class);
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Prueba Práctica - Backend Developer");
		List<Productos> listProd = new ArrayList<>();
		for(Productos prod: iProductoDao.findAll()) {
			if(prod.getCantidad()!=null && prod.getMarca() !=null && !prod.getMarca().equals("") && prod.getPrecio()!=null && prod.getEstado()!=null && prod.getPorcentaje()!=null) {
				listProd.add(prod);
			}else {
				iProductoDao.deleteLong(prod.getId());
			}
		}
		model.addAttribute("productos", listProd);
		return "listar";
	}
	
	@RequestMapping(value = "/buscar/{marca}", method = RequestMethod.GET)
	public ResponseEntity<Object>  buscarMarca(@PathVariable(value = "marca") String marca, Map<String, Object> model) {
		List<Productos> listProd = new ArrayList<>();
		log.info("Productos found with findMarca():");
		if (marca!=null) {
			for(Productos productos:iProductoDao.findMarca(marca)) {
				listProd = iProductoDao.findMarca(marca);
			}
		} 
		return new ResponseEntity<Object>(listProd, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/look/{nombre}", method = RequestMethod.GET)
	public ResponseEntity<Object>  buscarNombre(@PathVariable(value = "nombre") String nombre, Map<String, Object> model) {
		List<Productos> listProd = new ArrayList<>();
		log.info("Productos found with findMarca():");
		if (nombre!=null) {
			for(Productos productos:iProductoDao.findName(nombre)) {
				listProd = iProductoDao.findName(nombre);
			}
		} 
		return new ResponseEntity<Object>(listProd, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/find/{priceMin}/{priceMax}")
	public ResponseEntity<Object> findRange(@PathVariable(value = "priceMin") Long priceMin,@PathVariable(value = "priceMax") Long priceMax){
		List<Productos> listProd = new ArrayList<>();
		log.info("Productos found with findMarca():");
		if(priceMin!=null && priceMax!=null) {
			listProd = iProductoDao.findRangePrices(priceMin, priceMax);
		}
		return new ResponseEntity<Object>(listProd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/guardarFactura/{id}/{cantidad}", method = RequestMethod.GET)
	public ResponseEntity<Object>  guardarFactura(@PathVariable(value = "id") Long id, @PathVariable(value = "cantidad") Long cantidad, Map<String, Object> model) {
		Factura factura = new Factura();
		Productos prod = iProductoDao.findOne(id);
		if(prod!=null) {
				factura.setNombre(prod.getNombre());
				factura.setMarca(prod.getMarca());
				factura.setCantidad(cantidad);
				factura.setEstado(prod.getEstado());
				factura.setPorcentaje(prod.getPorcentaje());
				factura.setPrecio(prod.getPrecio());
				factura.setPrecioTotal(prod.getPrecio()-(prod.getPorcentaje().longValue()*cantidad));
				factura.setCreateAt(new Date());
				iProductoDao.save(factura);
		}else {
			log.info("No se encontro el producto o realice el cargue de los productos");

		}
		return new ResponseEntity<Object>(factura, HttpStatus.OK);
	}	
}
