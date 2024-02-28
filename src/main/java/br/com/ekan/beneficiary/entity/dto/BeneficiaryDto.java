package br.com.ekan.beneficiary.entity.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class BeneficiaryDto implements Serializable {

    private final Long id;

    private final String name;

    private String phone;

    private LocalDateTime dateOfBirth;

    private final Set<DocumentDto> documentDtos;

    public BeneficiaryDto(Long id, String name, String phone, LocalDateTime dateOfBirth, Set<DocumentDto> documentDtos) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.documentDtos = documentDtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BeneficiaryDto that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BeneficiaryDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", documentDtos has ").append(documentDtos.size()).append(" items");
        sb.append('}');
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public Set<DocumentDto> getDocumentDtos() {
        return documentDtos;
    }
}
