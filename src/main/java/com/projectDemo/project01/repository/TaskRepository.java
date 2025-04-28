package com.projectDemo.project01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projectDemo.project01.entities.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	/*
	 * Interface sayesinde .findAll(), .save(), .deleteById() 
	 * metodlari hazir olarak gelecek
	 */
	
	// Buraya ihtiyaca göre custom query method'ları ekleyebilirsiniz
	
}
