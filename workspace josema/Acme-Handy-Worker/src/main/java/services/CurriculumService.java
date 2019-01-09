
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CurriculumRepository;
import domain.Curriculum;

@Service
@Transactional
public class CurriculumService {

	//Repository
	@Autowired
	public CurriculumRepository	curriculumRepository;


	public Collection<Curriculum> findAll() {
		return this.curriculumRepository.findAll();
	}
}
