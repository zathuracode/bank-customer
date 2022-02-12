package com.vobi.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vobi.bank.domain.DocumentType;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {

}
