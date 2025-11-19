package com.evaluacion.productosapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.evaluacion.productosapi.entity.Producto;
import com.evaluacion.productosapi.service.ProductoService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/productos")
public class ProductoController {

    // Inyección de dependencias
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // CRUD
    
    // Listar / Obtener
    @GetMapping
    public List<Producto> listar() {
        return productoService.listarTodos();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Long id) {
        return productoService.obtenerPorId(id);
    }


    // Crear / Actualizar / Eliminar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@Valid @RequestBody Producto producto) {
        return productoService.crear(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id,
                               @Valid @RequestBody Producto producto) {
        return productoService.actualizar(id, producto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }

    // Extra: vender / restar stock
    @PatchMapping("/{id}/vender")
    public Producto vender(@PathVariable Long id,
                           @RequestParam int cantidad) {
        return productoService.vender(id, cantidad);
    }

    
    // Busqueda por categoria
    @GetMapping("/categoria/{categoria}")
    public List<Producto> listarPorCategoria(@PathVariable String categoria) {
        validarCategoria(categoria);
        return productoService.listarPorCategoria(categoria);
    }


    // VALIDACIÓN de categoría (Tecnología|Tecnologia, Accesorios u Oficina)
    private void validarCategoria(String categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("La categoría es obligatoria");
        }
        String cat = categoria.trim();
        if (!(cat.equalsIgnoreCase("Tecnologia")
                || cat.equalsIgnoreCase("Tecnología")
                || cat.equalsIgnoreCase("Accesorios")
                || cat.equalsIgnoreCase("Oficina"))) {
            throw new IllegalArgumentException(
                    "Categoría no válida. Debe ser Tecnología, Accesorios u Oficina"
            );
        }
    }


}
