package br.com.ekan.beneficiary.entity;

import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BeneficiaryTest {

    private Beneficiary beneficiary;

    @BeforeEach
    void setUp() {
        // Create by specifying the class
        int maximum = Double.valueOf(Math.random()).intValue();
        Set<Document> documents = Instancio.ofSet(Document.class).size(maximum).create();
        beneficiary = Instancio.create(Beneficiary.class);
        beneficiary.setDocuments(documents);
    }

    @AfterEach
    void tearDown() {
        beneficiary.setDocuments(null);
    }

    @Test
    void getId() {
        assertNotNull(beneficiary.getId(), "Got some value for id?");
    }

    @Test
    void setId() {
        long expected = Long.MIN_VALUE;
        beneficiary.setId(expected);
        assertEquals(expected, beneficiary.getId());
    }

    @Test
    void getName() {
        assertNotNull(beneficiary.getName(), "Check if name has some value.");
    }

    @Test
    void setName() {
        String expected = String.format("Just some random [%1$,.2f] name for test.", Math.random());
        beneficiary.setName(expected);
        assertEquals(expected, beneficiary.getName());
    }

    @Test
    void getPhone() {
        assertNotNull(beneficiary.getPhone(), "Check if phone has some value.");
    }

    @Test
    void setPhone() {
        String expected = String.format("Just some random [%1$,.2f] phone for test.", Math.random());
        beneficiary.setPhone(expected);
        assertEquals(expected, beneficiary.getPhone());
    }

    @Test
    void getDateOfBirth() {
        assertNotNull(beneficiary.getDateOfBirth());
    }

    @Test
    void setDateOfBirth() {
        LocalDateTime expected = LocalDateTime.now();
        beneficiary.setDateOfBirth(expected);
        assertEquals(expected, beneficiary.getDateOfBirth());

    }

    @Test
    void getDocuments() {
        assertNotNull(beneficiary.getDocuments());
    }

    @Test
    void setDocuments() {
        Set<Document> expected = Instancio.ofSet(Document.class).create();
        beneficiary.setDocuments(expected);
        assertEquals(expected, beneficiary.getDocuments());
    }

}