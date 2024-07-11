package com.qris_payment.api.third_party.bni.model.inquiry;

import com.qris_payment.api.common.Amount;
import com.qris_payment.api.third_party.bni.model.BniAdditionalInfoResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BniInquiryBody {
    private String originalReferenceNo;
    private Amount originalPartnerReferenceNo;
    private String originalExternalId;
    private String serviceCode;
    private String merchantId;
    private BniAdditionalInfoResponse additionalInfo;
}