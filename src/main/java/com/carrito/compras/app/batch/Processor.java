package com.carrito.compras.app.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.carrito.compras.app.entity.Productos;


@Component
public class Processor implements ItemProcessor<Productos, Productos>{
	
	private static final Map<String, String> DEPT_ESTADO =
            new HashMap<>();

    public Processor() {
    	DEPT_ESTADO.put("Nuevo", "Nuevo");
    	DEPT_ESTADO.put("Usado", "Usado");
    }
    
	@Override
	public Productos process(Productos item) throws Exception {
		String estCode = item.getEstado();
		String est = DEPT_ESTADO.get(estCode);
		item.setEstado(est);
		item.setCreateAt(new Date());
		System.out.println(String.format("Converted from [%s] to [%s]", estCode, est));
		return item;
	}
}
