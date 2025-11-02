package com.onlinebankingloanmanagement.loanmanagement.mapper;

import com.onlinebankingloanmanagement.loanmanagement.dto.LoanResponse;
import com.onlinebankingloanmanagement.loanmanagement.model.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Loan and its DTO LoanResponse.
 */
@Mapper(componentModel = "spring", uses = {PaymentMapper.class})
public interface LoanMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "loanApplication.id", target = "loanApplicationId")
    LoanResponse toDto(Loan loan);

}