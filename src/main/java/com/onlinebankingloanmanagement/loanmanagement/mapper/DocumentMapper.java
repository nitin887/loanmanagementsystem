package com.onlinebankingloanmanagement.loanmanagement.mapper;

import com.onlinebankingloanmanagement.loanmanagement.dto.DocumentResponse;
import com.onlinebankingloanmanagement.loanmanagement.model.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Document and its DTO DocumentResponse.
 */
@Mapper(componentModel = "spring")
public interface DocumentMapper {

    @Mapping(target = "downloadUrl", ignore = true) // This will be set in the service layer
    DocumentResponse toDto(Document document);

}