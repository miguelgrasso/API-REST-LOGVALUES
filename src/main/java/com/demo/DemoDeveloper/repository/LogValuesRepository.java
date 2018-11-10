package com.demo.DemoDeveloper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.DemoDeveloper.model.LogValues;

@Repository
public interface LogValuesRepository extends JpaRepository<LogValues, Long> {

}
