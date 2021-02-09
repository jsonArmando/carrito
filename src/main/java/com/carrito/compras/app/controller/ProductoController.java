package com.carrito.compras.app.controller;

import java.util.ArrayList;
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
		model.addAttribute("productos", iProductoDao.findAll());
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
	
	
}
