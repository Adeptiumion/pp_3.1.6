package pp_3_1_6.model;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class User {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;

    public Map<String, Object> getMap() {
        Map<String, Object> answer = new HashMap<>();
        answer.put("id", this.id);
        answer.put("name", this.name);
        answer.put("lastName", this.lastName);
        answer.put("age", this.age);
        return answer;
    }

    public User setId(Long id){
        this.id = id;
        return this;
    }

    public User setName(String name){
        this.name = name;
        return this;
    }

    public User setLastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public User setAge(Byte age) {
        this.age = age;
        return this;
    }



}
