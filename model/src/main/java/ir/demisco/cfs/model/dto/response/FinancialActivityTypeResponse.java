package ir.demisco.cfs.model.dto.response;

public class FinancialActivityTypeResponse {
    private Long financialActivityTypeId;
    private String code;
    private String description;
    private Boolean insertFlag;
    private Boolean updateFlag;
    private Boolean deleteFlag;
    private Boolean queryFlag;
    private Long financialSystemId;
    private Long financialSystemSubjectId;
    private String financialSystemDes;
    private String financialSystemSubjectDes;

    public Long getFinancialActivityTypeId() {
        return financialActivityTypeId;
    }

    public void setFinancialActivityTypeId(Long financialActivityTypeId) {
        this.financialActivityTypeId = financialActivityTypeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getInsertFlag() {
        return insertFlag;
    }

    public void setInsertFlag(Boolean insertFlag) {
        this.insertFlag = insertFlag;
    }

    public Boolean getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Boolean updateFlag) {
        this.updateFlag = updateFlag;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Boolean getQueryFlag() {
        return queryFlag;
    }

    public void setQueryFlag(Boolean queryFlag) {
        this.queryFlag = queryFlag;
    }

    public Long getFinancialSystemId() {
        return financialSystemId;
    }

    public void setFinancialSystemId(Long financialSystemId) {
        this.financialSystemId = financialSystemId;
    }

    public Long getFinancialSystemSubjectId() {
        return financialSystemSubjectId;
    }

    public void setFinancialSystemSubjectId(Long financialSystemSubjectId) {
        this.financialSystemSubjectId = financialSystemSubjectId;
    }

    public String getFinancialSystemDes() {
        return financialSystemDes;
    }

    public void setFinancialSystemDes(String financialSystemDes) {
        this.financialSystemDes = financialSystemDes;
    }

    public String getFinancialSystemSubjectDes() {
        return financialSystemSubjectDes;
    }

    public void setFinancialSystemSubjectDes(String financialSystemSubjectDes) {
        this.financialSystemSubjectDes = financialSystemSubjectDes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private FinancialActivityTypeResponse financialActivityTypeResponse;

        private Builder() {
            financialActivityTypeResponse = new FinancialActivityTypeResponse();
        }


        public Builder financialActivityTypeId(Long financialActivityTypeId) {
            financialActivityTypeResponse.setFinancialActivityTypeId(financialActivityTypeId);
            return this;
        }

        public Builder code(String code) {
            financialActivityTypeResponse.setCode(code);
            return this;
        }

        public Builder description(String description) {
            financialActivityTypeResponse.setDescription(description);
            return this;
        }

        public Builder insertFlag(Boolean insertFlag) {
            financialActivityTypeResponse.setInsertFlag(insertFlag);
            return this;
        }

        public Builder updateFlag(Boolean updateFlag) {
            financialActivityTypeResponse.setUpdateFlag(updateFlag);
            return this;
        }

        public Builder deleteFlag(Boolean deleteFlag) {
            financialActivityTypeResponse.setDeleteFlag(deleteFlag);
            return this;
        }

        public Builder queryFlag(Boolean queryFlag) {
            financialActivityTypeResponse.setQueryFlag(queryFlag);
            return this;
        }

        public Builder financialSystemId(Long financialSystemId) {
            financialActivityTypeResponse.setFinancialSystemId(financialSystemId);
            return this;
        }

        public Builder financialSystemSubjectId(Long financialSystemSubjectId) {
            financialActivityTypeResponse.setFinancialSystemSubjectId(financialSystemSubjectId);
            return this;
        }

        public Builder financialSystemDes(String financialSystemDes) {
            financialActivityTypeResponse.setFinancialSystemDes(financialSystemDes);
            return this;
        }

        public Builder financialSystemSubjectDes(String financialSystemSubjectDes) {
            financialActivityTypeResponse.setFinancialSystemSubjectDes(financialSystemSubjectDes);
            return this;
        }

        public FinancialActivityTypeResponse build() {
            return financialActivityTypeResponse;
        }
    }
}
