package service;

public class VoucherService {
    private static final String VALID_VOUCHER_CODE = "MTJ";

    public boolean applyVoucher(String voucherCode) {
        return VALID_VOUCHER_CODE.equalsIgnoreCase(voucherCode);
    }
}
