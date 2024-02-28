package br.com.ekan.beneficiary.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = Document.TABLE_NAME)
public class Document implements Serializable {
    public static final String TABLE_NAME = "document";
    public static final String COLUMN_ID_NAME = "id";
    @Serial
    private static final long serialVersionUID = -6948016372363634385L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_seq")
    @SequenceGenerator(name = "document_seq")
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private Long id;

    @Column(name = "tipoDocumento")
    private String type;

    @Column(name = "descricao")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "beneficiary_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Beneficiary beneficiary = new Beneficiary();

    @Column(name = "dataInclusao")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "dataAtualizacao")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected Document() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Document{");
        sb.append("id=").append(id);
        sb.append(", type='").append(type).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", beneficiary=").append(beneficiary);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document document)) return false;
        return Objects.equals(getId(), document.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Long getId() {
        return id;
    }

    public Document setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }
}