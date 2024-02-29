package br.com.ekan.beneficiary.controller;

import br.com.ekan.beneficiary.entity.dto.BeneficiaryDto;
import br.com.ekan.beneficiary.entity.dto.DocumentDto;
import br.com.ekan.beneficiary.entity.dto.SaveBeneficiaryDto;
import br.com.ekan.beneficiary.mapper.BeneficiaryMapper;
import br.com.ekan.beneficiary.mapper.BeneficiarySaveMapper;
import br.com.ekan.beneficiary.service.BeneficiaryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/api/v1")
@RestController
public class BeneficiaryController {

    private static final Logger log = LoggerFactory.getLogger(BeneficiaryController.class);

    private final BeneficiaryMapper beneficiaryMapper;
    private final BeneficiarySaveMapper beneficiarySaveMapper;
    private BeneficiaryService service;


    public BeneficiaryController(BeneficiarySaveMapper saveMapper, BeneficiaryMapper mapper, BeneficiaryService service) {
        log.info("\n--------------------");
        log.info("BeneficiaryController(...) method was call.");

        this.beneficiaryMapper = mapper;
        this.beneficiarySaveMapper = saveMapper;
        this.service = service;

    }

    @PostMapping
    public BeneficiaryDto saveResource(@RequestBody @NonNull @Valid SaveBeneficiaryDto saveBeneficiaryDto) {
        log.info("\n--------------------");
        log.info("saveBeneficiary() method was call.");

        log.info("\n--------------------Dto");
        log.info(saveBeneficiaryDto.toString());
        log.info("");
        return service.save(saveBeneficiaryDto);
    }

    @PutMapping
    public BeneficiaryDto updateResource(@RequestBody @NonNull @Valid BeneficiaryDto beneficiaryDto) {
        log.info("\n--------------------");
        log.info("updateBeneficiary() method was call.");

        if (null != beneficiaryDto.getDocuments()) {
            log.info("beneficiaryDto.getDocuments() --------------------");
            beneficiaryDto.getDocuments().forEach(dto -> {
                log.info("type=[" + dto.getType() + "],  description=[" + dto.getDescription() + "]");
            });
        }

        if (beneficiaryDto.getId() == null) {
            throw new IllegalArgumentException("Beneficiary ID is missing. Use the verb POST to create a new beneficiary");
        }

        log.info("\n--------------------Dto");
        log.info(beneficiaryDto.toString());
        log.info("");

        return service.update(beneficiaryDto);
    }

    @DeleteMapping("/{beneficiaryId}")
    public void deleteBeneficiaryById(@PathVariable Long beneficiaryId) {
        log.info("\n--------------------");
        log.info("deleteBeneficiaryById() method was call.");

        service.delete(beneficiaryId);

    }

    @GetMapping()
    public List<BeneficiaryDto> findAllResources() {
        log.info("\n--------------------");
        log.info("findAll() method was call.");

        return service.findAll();
    }

    @GetMapping("/{beneficiaryId}/documents")
    public Set<DocumentDto> findDocumentsByResourceId(@PathVariable Long beneficiaryId) {
        log.info("\n--------------------");
        log.info(String.format("findDocumentsByBeneficiaryId(%d) method was call.", beneficiaryId));

        return service.findDocumentsByBeneficiaryId(beneficiaryId);
    }

}
