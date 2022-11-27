package ir.demisco.cfs.model.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public class FinancialAlternativeUsersListRequest {
    private List<Long> financialAlternativeId;
    private LocalDateTime disableDate;

    public List<Long> getFinancialAlternativeId() {
        return financialAlternativeId;
    }

    public void setFinancialAlternativeId(List<Long> financialAlternativeId) {
        this.financialAlternativeId = financialAlternativeId;
    }

    public LocalDateTime getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(LocalDateTime disableDate) {
        this.disableDate = disableDate;
    }

}
