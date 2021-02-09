package com.carrito.compras.app.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carrito.compras.app.entity.Productos;
import com.carrito.compras.app.entity.repository.ProductosRepository;

@Component
public class DBWriter implements ItemWriter<Productos> {
	
	private ProductosRepository productosRepository;
	
	@Autowired
    public DBWriter (ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

	@Override
	public void write(List<? extends Productos> items) throws Exception {
		 System.out.println("Data Saved for Users: " + items);
		 productosRepository.saveAll(items);
		
	}

}
