package com.waa.waaproject.repository;

import com.waa.waaproject.domain.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoggerRepository extends JpaRepository<Logger,Long> {
}
