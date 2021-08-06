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
        @CheckOnlyOneIsNotNull(fieldNames = {"field1", "field2"}),
        @CheckOnlyOneIsNotNull(fieldNames = {"field3","field4"})
})
public class CustomRequest {

    private String field1;

    private String field2;

    private String field3;

    private String field4;
}
