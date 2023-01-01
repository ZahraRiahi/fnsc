package ir.demisco.cfs.model.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public class FinancialUserAlternativeInputModelRequest {
    private List<Long> userAlternativeIdList;
    private LocalDateTime disableDate;

    public List<Long> getUserAlternativeIdList() {
        return userAlternativeIdList;
    }

    public void setUserAlternativeIdList(List<Long> userAlternativeIdList) {
        this.userAlternativeIdList = userAlternativeIdList;
    }

    public LocalDateTime getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(LocalDateTime disableDate) {
        this.disableDate = disableDate;
    }
}
