package test.framework.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data @JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactDto {
        private String firstName;
        private String lastName;
        private String birthdate;
        private String email;
        private String phone;
        private String street1;
        private String street2;
        private String city;
        private String stateProvince;
        private String postalCode;
        private String country;
}
