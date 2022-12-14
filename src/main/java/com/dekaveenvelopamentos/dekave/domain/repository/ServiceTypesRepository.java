package com.dekaveenvelopamentos.dekave.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dekaveenvelopamentos.dekave.domain.entity.ServiceTypes;

@Repository
public interface ServiceTypesRepository extends JpaRepository<ServiceTypes, UUID> {

    ServiceTypes findByServiceTypeOrder(Long serviceTypeOrder);
}
