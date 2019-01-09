
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	@Query("select f from Finder f where f.id = ?1")
	public Finder finderById(Integer id);

	@Query("select h1 from HandyWorker h join h.finder h1 where h.id =?1")
	public Finder finderByHwId(Integer id);
}
