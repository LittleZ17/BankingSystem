package midtermProject.BankingSystem.controller.dto;

import jakarta.persistence.Enumerated;
import midtermProject.BankingSystem.enums.Status;

public class AccountStatusDTO {
    @Enumerated
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
