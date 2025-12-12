package com.angadi.springoauthclient.models.responses;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DocumentsFormResponse {

    @Nullable
    private String docName;

    @Nullable
    private String docLocation;

    @Nullable
    private String docType;

    @Nullable
    private String docYear;

    @Nullable
    private String docValidity;
}
