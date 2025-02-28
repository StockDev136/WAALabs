package com.waa.waaproject.repository;

import com.waa.waaproject.domain.ExceptionHandler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExceptionRespository extends JpaRepository<ExceptionHandler, Long> {
}
