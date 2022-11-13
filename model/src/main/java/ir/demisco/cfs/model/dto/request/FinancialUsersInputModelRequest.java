package ir.demisco.cfs.model.dto.request;

import java.util.List;

public class FinancialUsersInputModelRequest {
    private List<Long> applicationUserId;

    public List<Long> getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(List<Long> applicationUserId) {
        this.applicationUserId = applicationUserId;
    }
}
