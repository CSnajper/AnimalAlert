package spring.rest.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import spring.domain.Address;

@Data
@NoArgsConstructor
public class RegisterOrganisationDTO {

    private String name;

    private String phoneNumber;

    private Address Address;
}
