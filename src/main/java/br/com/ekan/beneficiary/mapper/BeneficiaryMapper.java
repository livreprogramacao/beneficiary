package br.com.ekan.beneficiary.mapper;

import br.com.ekan.beneficiary.entity.Beneficiary;
import br.com.ekan.beneficiary.entity.dto.BeneficiaryDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BeneficiaryMapper {

    Beneficiary beneficiaryDtoToBeneficiary(BeneficiaryDto beneficiaryDto);

    BeneficiaryDto beneficiaryToBeneficiaryDto(Beneficiary beneficiary);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBeneficiaryFromBeneficiaryDto(BeneficiaryDto beneficiaryDto, @MappingTarget Beneficiary beneficiary);

    @AfterMapping
    default void linkDocuments(@MappingTarget Beneficiary beneficiary) {
        if (null != beneficiary.getDocuments()) {
            beneficiary.getDocuments().forEach(document -> document.setBeneficiary(beneficiary));
        }
    }
}
