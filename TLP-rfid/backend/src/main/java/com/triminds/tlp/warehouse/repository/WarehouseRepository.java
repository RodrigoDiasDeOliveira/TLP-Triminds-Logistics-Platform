package com.triminds.tlp.warehouse.repository;

import com.triminds.tlp.warehouse.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {

    List<Warehouse> findByCompanyId(UUID companyId);
}