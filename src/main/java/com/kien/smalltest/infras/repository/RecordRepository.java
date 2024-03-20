package com.kien.smalltest.infras.repository;

import com.kien.smalltest.domain.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author kienvt
 */
public interface RecordRepository extends JpaRepository<Record, Long> {

}
