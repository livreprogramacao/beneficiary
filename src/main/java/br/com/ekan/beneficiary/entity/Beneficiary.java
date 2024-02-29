package br.com.ekan.beneficiary.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = Beneficiary.TABLE_NAME, indexes = {
        @Index(name = "idx_beneficiary_id", columnList = "id")
})
public class Beneficiary implements Serializable {
    public static final String TABLE_NAME = "beneficiary";
    public static final String COLUMN_ID_NAME = "id";
    @Serial
    private static final long serialVersionUID = 5219187653860387370L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beneficiary_seq")
    @SequenceGenerator(name = "beneficiary_seq")
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "nome")
    private String name;

    @NotNull
    @Column(name = "telefone")
    private String phone;

    @NotNull
    @Column(name = "dataNascimento")
    @CreationTimestamp
    private LocalDateTime dateOfBirth;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    private Set<Document> documents = new LinkedHashSet<>();

    @Column(name = "dataInclusao")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "dataAtualizacao")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Beneficiary() {
    }

    @Override
    public String toString() {
        String sb = "Beneficiary{" + "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", size documents is =" + documents.stream().count() +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
        return sb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Beneficiary that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Long getId() {
        return id;
    }

    public Beneficiary setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
}