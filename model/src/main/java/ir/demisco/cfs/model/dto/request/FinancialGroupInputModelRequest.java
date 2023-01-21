package ir.demisco.cfs.model.dto.request;

import java.util.List;

public class FinancialGroupInputModelRequest {
    private List<Long> groupId;

    public List<Long> getGroupId() {
        return groupId;
    }

    public void setGroupId(List<Long> groupId) {
        this.groupId = groupId;
    }
}
