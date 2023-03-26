package ir.demisco.cfs.model.dto.request;

import javax.validation.constraints.NotNull;

public class FinancialActivityTypeRequest {
    private Long activityTypeId;
    private String code;
    private String description;
    private Boolean insertFlag;
    private Boolean updateFlag;
    private Boolean deleteFlag;
    private Boolean queryFlag;
    private Long financialSystemId;
    private Long financialSystemSubjectId;

    public Long getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(Long activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    @NotNull(message = "fin.financialActivityType.enterCode")

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NotNull(message = "fin.financialActivityType.enterDescription")

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "fin.financialActivityType.enterInsertFlag")
    public Boolean getInsertFlag() {
        return insertFlag;
    }

    public void setInsertFlag(Boolean insertFlag) {
        this.insertFlag = insertFlag;
    }

    @NotNull(message = "fin.financialActivityType.enterUpdateFlag")
    public Boolean getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Boolean updateFlag) {
        this.updateFlag = updateFlag;
    }

    @NotNull(message = "fin.financialActivityType.enterDeleteFlag")
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @NotNull(message = "fin.financialActivityType.enterQueryFlag")
    public Boolean getQueryFlag() {
        return queryFlag;
    }

    public void setQueryFlag(Boolean queryFlag) {
        this.queryFlag = queryFlag;
    }

    @NotNull(message = "fin.financialActivityType.enterFinancialSystemId")
    public Long getFinancialSystemId() {
        return financialSystemId;
    }

    public void setFinancialSystemId(Long financialSystemId) {
        this.financialSystemId = financialSystemId;
    }

    @NotNull(message = "fin.financialActivityType.enterFinancialSystemSubjectId")
    public Long getFinancialSystemSubjectId() {
        return financialSystemSubjectId;
    }

    public void setFinancialSystemSubjectId(Long financialSystemSubjectId) {
        this.financialSystemSubjectId = financialSystemSubjectId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private FinancialActivityTypeRequest financialActivityTypeRequest;

        private Builder() {
            financialActivityTypeRequest = new FinancialActivityTypeRequest();
        }


        public Builder activityTypeId(Long activityTypeId) {
            financialActivityTypeRequest.setActivityTypeId(activityTypeId);
            return this;
        }

        public Builder code(String code) {
            financialActivityTypeRequest.setCode(code);
            return this;
        }

        public Builder description(String description) {
            financialActivityTypeRequest.setDescription(description);
            return this;
        }

        public Builder insertFlag(Boolean insertFlag) {
            financialActivityTypeRequest.setInsertFlag(insertFlag);
            return this;
        }

        public Builder updateFlag(Boolean updateFlag) {
            financialActivityTypeRequest.setUpdateFlag(updateFlag);
            return this;
        }

        public Builder deleteFlag(Boolean deleteFlag) {
            financialActivityTypeRequest.setDeleteFlag(deleteFlag);
            return this;
        }

        public Builder queryFlag(Boolean queryFlag) {
            financialActivityTypeRequest.setQueryFlag(queryFlag);
            return this;
        }

        public Builder financialSystemId(Long financialSystemId) {
            financialActivityTypeRequest.setFinancialSystemId(financialSystemId);
            return this;
        }

        public Builder financialSystemSubjectId(Long financialSystemSubjectId) {
            financialActivityTypeRequest.setFinancialSystemSubjectId(financialSystemSubjectId);
            return this;
        }

        public FinancialActivityTypeRequest build() {
            return financialActivityTypeRequest;
        }
    }
}
