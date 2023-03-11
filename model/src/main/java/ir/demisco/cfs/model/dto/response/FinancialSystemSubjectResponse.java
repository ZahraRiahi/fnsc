package ir.demisco.cfs.model.dto.response;

public class FinancialSystemSubjectResponse {
    private Long financialSystemSubjectId;
    private String code;
    private String description;

    public Long getFinancialSystemSubjectId() {
        return financialSystemSubjectId;
    }

    public void setFinancialSystemSubjectId(Long financialSystemSubjectId) {
        this.financialSystemSubjectId = financialSystemSubjectId;
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

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private FinancialSystemSubjectResponse financialSystemSubjectResponse;

        private Builder() {
            financialSystemSubjectResponse = new FinancialSystemSubjectResponse();
        }


        public Builder financialSystemSubjectId(Long financialSystemSubjectId) {
            financialSystemSubjectResponse.setFinancialSystemSubjectId(financialSystemSubjectId);
            return this;
        }

        public Builder code(String code) {
            financialSystemSubjectResponse.setCode(code);
            return this;
        }

        public Builder description(String description) {
            financialSystemSubjectResponse.setDescription(description);
            return this;
        }

        public FinancialSystemSubjectResponse build() {
            return financialSystemSubjectResponse;
        }
    }
}
