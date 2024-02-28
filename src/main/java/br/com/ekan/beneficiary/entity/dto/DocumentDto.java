package br.com.ekan.beneficiary.entity.dto;

import java.io.Serializable;
import java.util.Objects;

public class DocumentDto implements Serializable {

    private final Long id;

    private String type;

    private String description;

    private BeneficiaryDto beneficiary;

    public DocumentDto(Long id, String type, String description, BeneficiaryDto beneficiary) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.beneficiary = beneficiary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentDto that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DocumentDto{");
        sb.append("id=").append(id);
        sb.append(", type='").append(type).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", beneficiary ID =").append(beneficiary.getId());
        sb.append('}');
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public BeneficiaryDto getBeneficiary() {
        return beneficiary;
    }
}
