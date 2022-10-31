package com.nstsolution.java_utils.communs.annomalie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnomalieRepository extends JpaRepository<AnnomalieEntity , Long> {
}
