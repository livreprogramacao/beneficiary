package br.com.ekan.beneficiary.controller;

import br.com.ekan.beneficiary.entity.Beneficiary;
import br.com.ekan.beneficiary.entity.Document;
import br.com.ekan.beneficiary.entity.dto.BeneficiaryDto;
import br.com.ekan.beneficiary.entity.dto.DocumentDto;
import br.com.ekan.beneficiary.entity.dto.SaveBeneficiaryDto;
import br.com.ekan.beneficiary.entity.repository.BeneficiaryRepository;
import br.com.ekan.beneficiary.entity.repository.DocumentRepository;
import br.com.ekan.beneficiary.mapper.BeneficiaryMapper;
import br.com.ekan.beneficiary.mapper.SaveBeneficiaryMapper;
import br.com.ekan.beneficiary.service.BeneficiaryService;
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
public class BeneficiaryController {

    private static final Logger log = LoggerFactory.getLogger(BeneficiaryController.class);

    private final BeneficiaryMapper beneficiaryMapper;
    private final SaveBeneficiaryMapper saveBeneficiaryMapper;
    private BeneficiaryService service;


    public BeneficiaryController(SaveBeneficiaryMapper saveMapper, BeneficiaryMapper mapper, BeneficiaryService service) {
        log.info("--------------------");
        log.info("BeneficiaryController(...) method was call.\n");

        this.beneficiaryMapper = mapper;
        this.saveBeneficiaryMapper = saveMapper;
        this.service = service;

    }

    @PostMapping
    public BeneficiaryDto saveResource(@RequestBody @NonNull @Valid SaveBeneficiaryDto saveBeneficiaryDto) {
        log.info("--------------------");
        log.info("saveBeneficiary() method was call.\n");

        log.info("\nDto --------------------");
        log.info(saveBeneficiaryDto.toString());
        log.info("");
        return service.saveEntityBeneficiary(saveBeneficiaryDto);
    }

    @PutMapping
    public BeneficiaryDto updateResource(@RequestBody @NonNull @Valid BeneficiaryDto beneficiaryDto) {
        log.info("--------------------");
        log.info("updateBeneficiary() method was call.\n");

        if (null != beneficiaryDto.getDocuments()) {
            log.info("\nbeneficiaryDto.getDocuments() --------------------\n");
            beneficiaryDto.getDocuments().forEach(dto -> {
                log.info("type=[" + dto.getType() + "], \n description=[" + dto.getDescription() + "]");
            });
        }

        if (beneficiaryDto.getId() == null) {
            throw new IllegalArgumentException("Beneficiary ID is missing. Use the verb POST to create a new beneficiary");
        }

        log.info("Dto --------------------");
        log.info(beneficiaryDto.toString());
        log.info("");

        return service.updateBeneficiaryEntity(beneficiaryDto);
    }

    @DeleteMapping("/{beneficiaryId}")
    public void deleteBeneficiaryById(@PathVariable Long beneficiaryId) {
        log.info("--------------------");
        log.info("deleteBeneficiaryById() method was call.\n");

        service.deleteBeneficiaryEntity(beneficiaryId);

    }

    @GetMapping()
    public List<BeneficiaryDto> findAllResources() {
        log.info("--------------------");
        log.info("findAll() method was call.\n");

        return service.findAll();
    }

    @GetMapping("/{beneficiaryId}/documents")
    public Set<DocumentDto> findDocumentsByResourceId(@PathVariable Long beneficiaryId) {
        log.info("--------------------");
        log.info(String.format("findDocumentsByBeneficiaryId(%d) method was call.\n", beneficiaryId));

        return service.findDocumentEntityByBeneficiaryId(beneficiaryId);
    }

}
