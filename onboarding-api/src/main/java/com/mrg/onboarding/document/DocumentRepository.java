package com.mrg.onboarding.document;

import com.mrg.onboarding.document.model.Document;
import com.mrg.onboarding.user.AppUser;
import io.micrometer.common.KeyValues;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends PagingAndSortingRepository<Document, Long>, CrudRepository<Document, Long> {

    @Query("select d from Document d")
    List<Document> findAll();
    Optional<Document> findByUuid(UUID uuid);

    List<Document> findByCreatedBy(AppUser createdByUser);
}
