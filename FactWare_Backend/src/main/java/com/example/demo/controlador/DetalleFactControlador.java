package com.example.demo.controlador;

import com.example.demo.modelo.DetalleFactura;
import com.example.demo.service.IDetalleFacService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class DetalleFactControlador {

    @Autowired
    private IDetalleFacService sD;
    
    //listar todos los usuarios
    @GetMapping("/listD")
    public List<DetalleFactura> index() {
        return sD.findAll();
    }

    //Buscar un usuario
    @GetMapping("/buscD/{id}")
    public DetalleFactura findById(@PathVariable Long id) {
        return sD.findById(id);
    }

    //Guardar
    @PostMapping("/savD")
    @ResponseStatus(HttpStatus.CREATED)
    public DetalleFactura save(@RequestBody DetalleFactura detalleFactura) {
        return sD.save(detalleFactura);
    }

    //Modificar Usuario
    @PutMapping("/modiD/{id}")
    public DetalleFactura update(@RequestBody DetalleFactura detalle, @PathVariable Long id) {
        DetalleFactura DetalleFacturaN = sD.findById(id);
        DetalleFacturaN.setCantidad(detalle.getCantidad());
        DetalleFacturaN.setSubTotal(detalle.getSubTotal());
        DetalleFacturaN.setFactura(detalle.getFactura());
        DetalleFacturaN.setTotal(detalle.getTotal());
        DetalleFacturaN.setProducto(detalle.getProducto());
        return sD.save(DetalleFacturaN);
    }

    //Eliminar Usuario
    @DeleteMapping("/delD/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        sD.delete(id);
    }
}
