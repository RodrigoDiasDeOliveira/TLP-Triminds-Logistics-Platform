package com.triminds.tlp.warehouse.repository;

import com.triminds.tlp.warehouse.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    List<Warehouse> findByCompanyId(Long companyId);
}