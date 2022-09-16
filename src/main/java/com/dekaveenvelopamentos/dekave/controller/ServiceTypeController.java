package com.dekaveenvelopamentos.dekave.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dekaveenvelopamentos.dekave.domain.entity.ServiceTypes;
import com.dekaveenvelopamentos.dekave.dto.ActiveDTO;
import com.dekaveenvelopamentos.dekave.dto.ServiceTypesDTO;
import com.dekaveenvelopamentos.dekave.service.ServiceTypeService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class ServiceTypeController {

    private static final String serviceTypesTag = "Service Types";

    @Autowired
    private ServiceTypeService service;

    @Operation(summary = "Get by id.", tags = serviceTypesTag)
    @GetMapping("/servicetypes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceTypes getServiceTypesById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @Operation(summary = "Get all per page and size.", tags = serviceTypesTag)
    @GetMapping("/servicetypes/{page}/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceTypes> getAllServiceTypes(@PathVariable Integer page, @PathVariable Integer size) {
        return service.getServiceTypes(page, size);
    }

    @Operation(summary = "Save new service type.", tags = serviceTypesTag)
    @PostMapping(value = "/servicetypes/save", consumes = { "multipart/form-data" })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveServiceType(@RequestPart("serviceType") @Valid ServiceTypesDTO serviceTypesDTO,
            @RequestPart("file") MultipartFile file) throws IOException {
        service.saveServiceType(serviceTypesDTO, file);
    }

    @Operation(summary = "Update by id.", tags = serviceTypesTag)
    @PutMapping("/servicetypes/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateServiceType(@PathVariable UUID id, @RequestPart("serviceType") ServiceTypesDTO serviceTypesDTO,
            @RequestPart("file") MultipartFile file) throws IOException {
        service.updateServiceType(id, serviceTypesDTO, file);
    }

    @Operation(summary = "Activate/Disable by id.", tags = serviceTypesTag)
    @PutMapping("/servicetypes/active/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void activeById(@PathVariable UUID id, @RequestBody @Valid ActiveDTO activeDTO) {
        service.activeById(id, activeDTO);
    }

    @Operation(summary = "Delete by id.", tags = serviceTypesTag)
    @DeleteMapping("/servicetypes/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServiceTypeById(@PathVariable UUID id) {
        service.deleteById(id);
    }
}
