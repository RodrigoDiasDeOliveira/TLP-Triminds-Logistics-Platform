package com.triminds.tlp.prediction.repository;

import com.triminds.tlp.prediction.entity.Prediction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredictionRepository extends JpaRepository<Prediction, Long> {

    List<Prediction> findByCompanyIdOrderByCreatedAtDesc(
            String companyId,
            Pageable pageable
    );
}
