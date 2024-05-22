package test.framework.utilities;

import test.framework.api.models.ContactDto;

import java.util.Map;

public class DtoUtils {

    public static ContactDto createContactDto(Map<String, String> contactData) {
        var dto = new ContactDto();
        dto.setFirstName(contactData.get("First Name"));
        dto.setLastName(contactData.get("Last Name"));
        dto.setBirthdate(contactData.get("Date of Birth"));
        dto.setEmail(contactData.get("Email"));
        dto.setPhone(contactData.get("Phone"));
        dto.setStreet1(contactData.get("Street Address 1"));
        dto.setStreet2(contactData.get("Street Address 2"));
        dto.setCity(contactData.get("City"));
        dto.setStateProvince(contactData.get("State or Province"));
        dto.setPostalCode(contactData.get("Postal Code"));
        dto.setCountry(contactData.get("Country"));
        return dto;
    }
}
