package app.udayyadav.mongo.repository;

import app.udayyadav.mongo.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

//    @Query(value = "{'name': ?0}", fields = "{'age':0}")
//    List<Employee> findByName(String name);

    @Query(value = "{'name': {'$regex': '?0' } }")
    List<Employee> findByName(String name);

    @Query("{'name': ?0, 'department': ?1}")
    List<Employee> findByNameAndDepartment(String name, String department);

    @Query("{'age' : {'$gte':?0, '$lte': ?1 }}")
    List<Employee> findBetweenAge(Integer minAge, Integer maxAge);

    List<Employee> findByNameOrderByDepartment(String name);

}
