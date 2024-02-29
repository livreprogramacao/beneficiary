package br.com.ekan.beneficiary.entity.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class SaveBeneficiaryDto implements Serializable {

//    private final Long id;

    private final String name;
    private final Set<DocumentDto> documents;
    private final String phone;
    private final LocalDateTime dateOfBirth;

    public SaveBeneficiaryDto(String name, String phone, LocalDateTime dateOfBirth, Set<DocumentDto> set) {
        this.name = name;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.documents = set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaveBeneficiaryDto that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        String sb = "SaveBeneficiaryDto{" + "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", documents has " + documents.size() + " items" +
                '}';
        return sb;
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

    public Set<DocumentDto> getDocuments() {
        return documents;
    }
}
