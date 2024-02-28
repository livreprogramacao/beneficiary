package br.com.ekan.beneficiary.entity.repository;

import br.com.ekan.beneficiary.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Set;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Set<Document> findByBeneficiary_Id(@NonNull Long id);

}