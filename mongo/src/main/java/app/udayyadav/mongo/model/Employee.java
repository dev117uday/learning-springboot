package app.udayyadav.mongo.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(
        collection = "testCol"
)
public class Employee {

    private String id;
    private String name;
    private Integer age;
    private String department;

}
