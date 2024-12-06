package jokiOrderApp.service;

import org.springframework.stereotype.Component;

@Component
public class VoucherService implements VoucherServiceInterface{
    private static final String VALID_VOUCHER_CODE = "MTJ";

    @Override
    public boolean applyVoucher(String voucherCode) {
        return VALID_VOUCHER_CODE.equalsIgnoreCase(voucherCode);
    }
}
