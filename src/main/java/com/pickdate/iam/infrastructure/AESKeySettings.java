package com.pickdate.iam.infrastructure;

public record AESKeySettings(
        String info,
        String salt,
        String master
) {
}
