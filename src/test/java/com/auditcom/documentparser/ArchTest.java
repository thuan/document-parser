package com.auditcom.documentparser;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.auditcom.documentparser");

        noClasses()
            .that()
            .resideInAnyPackage("com.auditcom.documentparser.service..")
            .or()
            .resideInAnyPackage("com.auditcom.documentparser.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.auditcom.documentparser.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
