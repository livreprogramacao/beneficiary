package br.com.ekan.beneficiary.mapper;

import br.com.ekan.beneficiary.entity.Beneficiary;
import br.com.ekan.beneficiary.entity.dto.SaveBeneficiaryDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BeneficiarySaveMapper {

    Beneficiary saveBeneficiaryDtoToBeneficiary(SaveBeneficiaryDto saveBeneficiaryDto);

    SaveBeneficiaryDto beneficiaryToSaveBeneficiaryDto(Beneficiary beneficiary);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBeneficiaryFromSaveBeneficiaryDto(SaveBeneficiaryDto saveBeneficiaryDto, @MappingTarget Beneficiary beneficiary);

    @AfterMapping
    default void linkDocuments(@MappingTarget Beneficiary beneficiary) {
        if (null != beneficiary.getDocuments()) {
            beneficiary.getDocuments().forEach(document -> document.setBeneficiary(beneficiary));
        }
    }
}
