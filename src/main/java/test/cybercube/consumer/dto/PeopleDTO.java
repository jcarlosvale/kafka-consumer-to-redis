package test.cybercube.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/*
Represents the People Data
 */
@Data
@RedisHash("PeopleDTO")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeopleDTO implements Serializable{
    private String id;
    private String firstName;
    private String lastName;
    private Double baseSeed;
}
