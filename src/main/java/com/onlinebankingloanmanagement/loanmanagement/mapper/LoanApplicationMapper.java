package com.onlinebankingloanmanagement.loanmanagement.mapper;

import com.onlinebankingloanmanagement.loanmanagement.dto.LoanApplicationRequest;
import com.onlinebankingloanmanagement.loanmanagement.dto.LoanApplicationResponse;
import com.onlinebankingloanmanagement.loanmanagement.model.LoanApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity LoanApplication and its DTOs.
 */
@Mapper(componentModel = "spring", uses = {DocumentMapper.class})
public interface LoanApplicationMapper {

    @Mapping(source = "id", target = "userId")
    LoanApplicationResponse toDto(LoanApplication loanApplication);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "applicationDate", ignore = true)
    @Mapping(target = "decisionDate", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "loan", ignore = true)
    LoanApplication toEntity(LoanApplicationRequest loanApplicationRequest);

}