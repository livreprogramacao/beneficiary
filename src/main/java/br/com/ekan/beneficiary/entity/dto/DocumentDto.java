package br.com.ekan.beneficiary.entity.dto;

import java.io.Serializable;
import java.util.Objects;

public class DocumentDto implements Serializable {

    private final Long id;

    private final String type;

    private final String description;

    private final BeneficiaryDto beneficiary;

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
        String sb = "DocumentDto{" + "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", beneficiary ID =" + beneficiary.getId() +
                '}';
        return sb;
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
