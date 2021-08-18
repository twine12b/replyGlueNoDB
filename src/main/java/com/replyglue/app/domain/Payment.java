package com.replyglue.app.domain;

import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter @Setter
@NoArgsConstructor  @ToString
@AllArgsConstructor
public class Payment {

    @NotNull
    private long payment_card;
    @NotNull
    private int amount;

}
