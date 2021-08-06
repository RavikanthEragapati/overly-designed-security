package com.overlydesigned.security.model.request;

import com.overlydesigned.security.validation.custom.CheckOnlyOneIsNotNull;
import com.overlydesigned.security.validation.custom.CheckOnlyOneIsNotNullArray;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@CheckOnlyOneIsNotNullArray({
        @CheckOnlyOneIsNotNull(fieldNames = {"abc", "xyz"}),
        @CheckOnlyOneIsNotNull(fieldNames = {"def","uvw"})
})
public class CustomRequest {

    private String abc;

    private String xyz;

    private String def;

    private String uvw;
}
