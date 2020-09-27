package test.cybercube.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
Represents the People Data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeopleDTO {
    private String firstName;
    private String lastName;
    private Double baseSeed;
}
