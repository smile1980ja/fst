package fst.demo;

import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
public class Member {
    @Id
    private final String id;
    private final String name;
}
