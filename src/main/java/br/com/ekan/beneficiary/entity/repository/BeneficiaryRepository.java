package br.com.ekan.beneficiary.entity.repository;

import br.com.ekan.beneficiary.entity.Beneficiary;
import br.com.ekan.beneficiary.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

}