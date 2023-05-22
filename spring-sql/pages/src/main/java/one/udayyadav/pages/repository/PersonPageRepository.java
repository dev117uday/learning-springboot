package one.udayyadav.pages.repository;

import one.udayyadav.pages.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonPageRepository extends PagingAndSortingRepository<Person, Integer> {
}
