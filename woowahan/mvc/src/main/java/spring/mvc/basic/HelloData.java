package spring.mvc.basic;

import lombok.Data;

@Data // @Getter, @Setter, @ToString, @RequiredArgsConstructor, @EqualsAndHashCode, @Value
public class HelloData {
    private String username;
    private int age;
}
