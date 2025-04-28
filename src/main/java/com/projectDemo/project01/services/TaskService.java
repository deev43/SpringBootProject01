package com.projectDemo.project01.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectDemo.project01.entities.Task;
import com.projectDemo.project01.repository.TaskRepository;

@Service	// Bu anotasyon, Spring'e bu sınıfın bir servis oldugunu bildirir
public class TaskService {
	
	// Burada, TaskRepository'yi kullanacağız. Repository, veritabanı işlemleri için kullanılır. Bu yüzden TaskRepository'yi enjekte ediyoruz.
	private final TaskRepository taskRepository;

	// Constructor üzerinden taskRepository'yi enjekte ediyoruz. Spring, bu sınıfı başlatırken otomatik olarak bu bağımlılığı sağlayacaktır.
	@Autowired
	public TaskService(TaskRepository taskRepository) {		
		this.taskRepository = taskRepository;
	}
	
	// Bu metod, tüm görevleri almak için kullanılır. 'findAll()' metodu, veritabanındaki tüm verileri getirir.
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}
	
	 // Bu metod, belirli bir id'ye sahip görevi almak için kullanılır.
    // 'findById()' metodu, verilen id ile eşleşen veriyi döndürür, ancak bu veri olabilir veya olmayabilir (null). Exception firlatmasin diye Optional kullandik
	public Optional<Task> getTaskById(long id){
		return taskRepository.findById(id);	// id'ye gore gorevi getirir.
	}
	
	// Bu metod, yeni bir görev eklemek için kullanılır. Görevi veritabanına kaydederiz.
    public Task createTask(Task task) {
		return taskRepository.save(task);	// yeni bir gorevi veritabanina kaydeder.
		
	}
    
    
    // Bu metod, bir görevi güncellemek için kullanılır. Eğer görev var ise, veritabanındaki eski veriyi yeni veriyle güncelleriz.
     public Task updateTask(long id, Task taskDetails) {
    	// Öncelikle, id ile var olan görevi alıyoruz.
    	 Task existingTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    	 
    	// Veritabanındaki görevi, yeni verilen bilgilerle güncelliyoruz.
    	 existingTask.setTitle(taskDetails.getTitle());
    	 existingTask.setDescription(taskDetails.getDescription());
    	 existingTask.setStatus(taskDetails.getStatus());

    	// Güncellenen görevi kaydediyoruz ve geri döndürüyoruz.
    	 return taskRepository.save(existingTask);
		
    }
     
     // Bu metod, belirli bir id'ye sahip görevi silmek için kullanılır.
     public void deleteTask(long id) {
    	 // Eğer görev mevcutsa, onu sileriz. Eğer görev yoksa, exception fırlatırız.
        taskRepository.deleteById(id);
     }

	
	
	

}
