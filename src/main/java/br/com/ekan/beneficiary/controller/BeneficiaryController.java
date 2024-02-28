package br.com.ekan.beneficiary.controller;

import br.com.ekan.beneficiary.entity.Document;
import br.com.ekan.beneficiary.entity.dto.BeneficiaryDto;
import br.com.ekan.beneficiary.entity.dto.DocumentDto;
import br.com.ekan.beneficiary.entity.repository.BeneficiaryRepository;
import br.com.ekan.beneficiary.entity.repository.DocumentRepository;
import br.com.ekan.beneficiary.mapper.BeneficiaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.Set;

@RequestMapping("/api/v1")
@RestController
public class BeneficiaryController {

    private static final Logger log = LoggerFactory.getLogger(BeneficiaryController.class);

    private final BeneficiaryMapper beneficiaryMapper;
    private final BeneficiaryRepository beneficiaryRepository;
    private final DocumentRepository documentRepository;

    public BeneficiaryController(BeneficiaryMapper mapper, BeneficiaryRepository repository,
                                 DocumentRepository documentRepository) {
        this.beneficiaryMapper = mapper;
        this.beneficiaryRepository = repository;
        this.documentRepository = documentRepository;
    }

    @GetMapping("/beneficiary/{id}/documents")
    public Set<DocumentDto> findDocumentsByBenefiaryId(@PathVariable Long beneficaryId) {
        Set<Document> documentSet = documentRepository.findByBeneficiary_Id(beneficaryId);
        log.info(String.format("Found %d documents for this Beneficiary_Id=%d", documentSet.stream().count(), beneficaryId));

        Set<DocumentDto> result = new LinkedHashSet<>(3);
        documentSet.stream().forEach(document -> {
            DocumentDto documentDto = new DocumentDto(
                    document.getId()
                    , document.getType()
                    , document.getDescription()
                    , beneficiaryMapper.beneficiaryToBeneficiaryDto(document.getBeneficiary()));
            result.add(documentDto);
        });
        return result;
    }
}
