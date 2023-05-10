package midtermProject.BankingSystem.controller.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CheckingAccountDTO {

    @NotNull
    @Size(max = 8, message = "The secret key must have a maximum of 8 characters")
    private String secretKey;
    @NotNull
    private Integer primaryOwnerId;


    public String getSecretKey() {
        return secretKey;
    }

    public Integer getPrimaryOwnerId() {
        return primaryOwnerId;
    }

}
