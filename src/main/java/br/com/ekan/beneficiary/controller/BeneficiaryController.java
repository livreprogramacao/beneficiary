package br.com.ekan.beneficiary.controller;

import br.com.ekan.beneficiary.entity.Beneficiary;
import br.com.ekan.beneficiary.entity.Document;
import br.com.ekan.beneficiary.entity.dto.BeneficiaryDto;
import br.com.ekan.beneficiary.entity.dto.DocumentDto;
import br.com.ekan.beneficiary.entity.repository.BeneficiaryRepository;
import br.com.ekan.beneficiary.entity.repository.DocumentRepository;
import br.com.ekan.beneficiary.mapper.BeneficiaryMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @GetMapping()
    public List<BeneficiaryDto> findAll() {
        log.info("Called method: findAll()");

        List<Beneficiary> beneficiaryList = beneficiaryRepository.findAll();
        return beneficiaryList.stream().map(beneficiaryMapper::beneficiaryToBeneficiaryDto).collect(Collectors.toList());
    }

    @GetMapping("/beneficiary/{id}/documents")
    public Set<DocumentDto> findDocumentsByBeneficiaryId(@PathVariable Long beneficaryId) {
        log.info(String.format("Called method: findDocumentsByBeneficiaryId(%d)", beneficaryId));

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

    @PostMapping
    public BeneficiaryDto saveBeneficiary(@RequestBody @NonNull @Valid BeneficiaryDto beneficiaryDto) {
        log.info("Called method: saveBeneficiary()");

        Beneficiary beneficiaryEntity = beneficiaryMapper.beneficiaryDtoToBeneficiary(beneficiaryDto);
        return beneficiaryMapper.beneficiaryToBeneficiaryDto(beneficiaryRepository.save(beneficiaryEntity));
    }

    @PutMapping
    public BeneficiaryDto updateBeneficiary(@RequestBody @NonNull @Valid BeneficiaryDto beneficiaryDto) {
        log.info("Called method: updateBeneficiary()");

        if (beneficiaryDto.getId() == null) {
            throw new IllegalArgumentException("Beneficiary ID is missing. Use the verb POST to create a new beneficiary");
        }

        Beneficiary beneficiaryEntity = beneficiaryRepository.findById(beneficiaryDto.getId()).orElseThrow(EntityNotFoundException::new);

        beneficiaryMapper.updateBeneficiaryFromBeneficiaryDto(beneficiaryDto, beneficiaryEntity);
        return beneficiaryMapper.beneficiaryToBeneficiaryDto(beneficiaryRepository.save(beneficiaryEntity));
    }

}
