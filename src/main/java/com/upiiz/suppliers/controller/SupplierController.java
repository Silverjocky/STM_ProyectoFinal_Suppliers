package com.upiiz.suppliers.controller;

import com.upiiz.suppliers.entities.Supplier;
import com.upiiz.suppliers.responses.CustomResponse;
import com.upiiz.suppliers.services.SupplierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://wcbdf-adl-examen-2.onrender.vercel.app"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/v1/suppliers")
@Tag(
        name = "Suppliers"
)
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    //@PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<CustomResponse<List<Supplier>>> getSuppliers() {
        List<Supplier> expens = new ArrayList<>();
        Link allSuppliersLink = linkTo(SupplierController.class).withSelfRel();
        List<Link> links = List.of(allSuppliersLink);
        try {
            expens = supplierService.getAllSuppliers();
            if (!expens.isEmpty()) {
                CustomResponse<List<Supplier>> response = new CustomResponse<>(1, "Proovedors encontrados", expens, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(0, "Proovedors no encontrados", expens, links));
            }
        } catch (Exception e) {
            CustomResponse<List<Supplier>> response = new CustomResponse<>(500, "Error interno de servidor", expens, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<CustomResponse<Supplier>> getSupplierById(@PathVariable Long id) {
        Optional<Supplier> supplier = null;
        CustomResponse<Supplier> response = null;
        Link allSuppliersLink = linkTo(SupplierController.class).withSelfRel();
        List<Link> links = List.of(allSuppliersLink);
        try {
            supplier = supplierService.getSupplierById(id);
            if (supplier.isPresent()) {
                response = new CustomResponse<>(1, "Proovedor encontrado", supplier.get(), links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response = new CustomResponse<>(0, "Proovedor no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response = new CustomResponse<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<CustomResponse<Supplier>> crearSupplier(@RequestBody Supplier supplier) {
        Link allSuppliersLink = linkTo(SupplierController.class).withSelfRel();
        List<Link> links = List.of(allSuppliersLink);
        try {
            Supplier supplier1 = supplierService.createSupplier(supplier);
            if (supplier1 != null) {
                CustomResponse<Supplier> response = new CustomResponse<>(1, "Proovedor creado", supplier1, links);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse<>(0, "Proovedor no encontrado", supplier1, links));
            }
        } catch (Exception e) {
            CustomResponse<Supplier> response = new CustomResponse<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<CustomResponse<Supplier>> updateSupplier(@RequestBody Supplier supplier, @PathVariable Long id) {
        Link allSuppliersLink = linkTo(SupplierController.class).withSelfRel();
        List<Link> links = List.of(allSuppliersLink);
        try {
            supplier.setSupplierId(id);
            if (!supplierService.getSupplierById(id).equals("")) {
                Supplier supplierEntity = supplierService.updateSupplier(supplier);
                CustomResponse<Supplier> response = new CustomResponse<>(1, "Proovedor actualizado", supplierEntity, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                CustomResponse<Supplier> response = new CustomResponse<>(0, "Proovedor no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            CustomResponse<Supplier> response = new CustomResponse<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<CustomResponse<Supplier>> deleteSupplierById(@PathVariable Long id) {
        Optional<Supplier> supplierEntity = null;
        CustomResponse<Supplier> response = null;
        Link allSuppliersLink = linkTo(SupplierController.class).withSelfRel();
        List<Link> links = List.of(allSuppliersLink);

        try {
            supplierEntity = supplierService.getSupplierById(id);
            if (supplierEntity.isPresent()) {
                supplierService.deleteSupplier(id);
                response = new CustomResponse<>(1, "Proovedor eliminado", null, links);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response = new CustomResponse<>(0, "Proovedor no encontrado", null, links);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response = new CustomResponse<>(500, "Error interno de servidor", null, links);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}