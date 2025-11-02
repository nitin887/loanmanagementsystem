package com.onlinebankingloanmanagement.loanmanagement.mapper;

import com.onlinebankingloanmanagement.loanmanagement.dto.PaymentResponse;
import com.onlinebankingloanmanagement.loanmanagement.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Payment and its DTO PaymentResponse.
 */
@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(source = "loan.id", target = "loanId")
    PaymentResponse toDto(Payment payment);
}