package com.evaluacion.productosapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaluacion.productosapi.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    

    //busqueda por categoria
    List<Producto> findByCategoriaIgnoreCase(String categoria);
}
