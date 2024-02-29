package br.com.ekan.beneficiary.service;

import br.com.ekan.beneficiary.entity.Beneficiary;
import br.com.ekan.beneficiary.entity.Document;
import br.com.ekan.beneficiary.entity.dto.BeneficiaryDto;
import br.com.ekan.beneficiary.entity.dto.DocumentDto;
import br.com.ekan.beneficiary.entity.dto.SaveBeneficiaryDto;
import br.com.ekan.beneficiary.entity.repository.BeneficiaryRepository;
import br.com.ekan.beneficiary.entity.repository.DocumentRepository;
import br.com.ekan.beneficiary.mapper.BeneficiaryMapper;
import br.com.ekan.beneficiary.mapper.SaveBeneficiaryMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/api/v1")
@RestController
public class BeneficiaryService {

    private static final Logger log = LoggerFactory.getLogger(BeneficiaryService.class);

    private final BeneficiaryMapper beneficiaryMapper;
    private final BeneficiaryRepository beneficiaryRepository;
    private final DocumentRepository documentRepository;
    private final SaveBeneficiaryMapper saveBeneficiaryMapper;

    public BeneficiaryService(SaveBeneficiaryMapper saveMapper, BeneficiaryMapper mapper, BeneficiaryRepository repository, DocumentRepository documentRepository) {
        log.info("--------------------");
        log.info("BeneficiaryService(...) method was call.\n");

        this.beneficiaryMapper = mapper;
        this.saveBeneficiaryMapper = saveMapper;
        this.beneficiaryRepository = repository;
        this.documentRepository = documentRepository;

    }

    public BeneficiaryDto saveEntityBeneficiary(SaveBeneficiaryDto saveBeneficiaryDto) {
        Beneficiary beneficiaryEntity = beneficiaryRepository.save(saveBeneficiaryMapper.saveBeneficiaryDtoToBeneficiary(saveBeneficiaryDto));

        log.info("\nEntity --------------------");
        log.info(beneficiaryEntity.toString());
        log.info("");

        Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryEntity.getId()).orElseThrow(EntityNotFoundException::new);
        if (null != saveBeneficiaryDto.getDocuments() && !saveBeneficiaryDto.getDocuments().isEmpty()) {
            log.info("saveBeneficiaryDto.getDocuments() --------------------");
            saveBeneficiaryDto.getDocuments().forEach(dto -> {
                log.info("type=[" + dto.getType() + "],");
                log.info("description=[" + dto.getDescription() + "]");
                final Document document;
                document = new Document(null, dto.getType(), dto.getDescription(), beneficiary);
                documentRepository.save(document);
            });
        }

        return beneficiaryMapper.beneficiaryToBeneficiaryDto(beneficiaryEntity);
    }

    public BeneficiaryDto updateBeneficiaryEntity(BeneficiaryDto beneficiaryDto) {
        Beneficiary beneficiaryEntity = beneficiaryRepository.findById(beneficiaryDto.getId()).orElseThrow(EntityNotFoundException::new);
        beneficiaryMapper.updateBeneficiaryFromBeneficiaryDto(beneficiaryDto, beneficiaryEntity);

        Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryEntity.getId()).orElseThrow(EntityNotFoundException::new);
        if (null != beneficiaryDto.getDocuments() && !beneficiaryDto.getDocuments().isEmpty()) {
            log.info("saveBeneficiaryDto.getDocuments() --------------------");
            beneficiaryDto.getDocuments().forEach(dto -> {
                log.info("type=[" + dto.getType() + "],");
                log.info("description=[" + dto.getDescription() + "]");
                final Document document = documentRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
                document.setType(dto.getType());
                document.setDescription(dto.getDescription());
                documentRepository.save(document);
            });
        }

        return beneficiaryMapper.beneficiaryToBeneficiaryDto(beneficiaryRepository.save(beneficiaryEntity));
    }


    public void deleteBeneficiaryEntity(Long beneficiaryId) {
        Optional<Beneficiary> beneficiaryEntity = beneficiaryRepository.findById(beneficiaryId);
        beneficiaryEntity.ifPresent(beneficiaryRepository::delete);
    }

    public List<BeneficiaryDto> findAll() {
        List<Beneficiary> beneficiaryList = beneficiaryRepository.findAll();
        return beneficiaryList.stream().map(beneficiaryMapper::beneficiaryToBeneficiaryDto).collect(Collectors.toList());
    }

    public Set<DocumentDto> findDocumentEntityByBeneficiaryId(Long beneficiaryId) {
        Set<Document> documentSet = documentRepository.findByBeneficiary_Id(beneficiaryId);
        log.info("--------------------");
        log.info(String.format("Found %d documents for this Beneficiary_Id=%d\n", documentSet.stream().count(), beneficiaryId));

        Set<DocumentDto> result = new LinkedHashSet<>(3);
        documentSet.stream().forEach(document -> {
            DocumentDto documentDto = new DocumentDto(document.getId(), document.getType(), document.getDescription(), beneficiaryMapper.beneficiaryToBeneficiaryDto(document.getBeneficiary()));
            result.add(documentDto);
        });
        return result;
    }

}
