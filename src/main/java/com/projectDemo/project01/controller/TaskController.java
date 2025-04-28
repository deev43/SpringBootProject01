package com.projectDemo.project01.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectDemo.project01.entities.Task;
import com.projectDemo.project01.services.TaskService;

@RestController // Bu sınıfın bir REST Controller olduğunu belirtir. Yani HTTP isteklerini karşılar.
@RequestMapping("/tasks") // Bu sınıftaki tüm istekler "/tasks" URL'inden başlayacak demektir.
public class TaskController {

	private final TaskService taskService;

	// TaskService'i controller içinde kullanabilmek için constructor injection yapıyoruz.
    // Bu sayede TaskService'deki methodlara erişip iş katmanındaki işlemleri çağırabiliriz.
    @Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
 // Belirli bir ID'ye sahip görevi getirmek için GET /tasks/{id} isteğini tanımlıyoruz
     @GetMapping
    public List<Task> getAllTasks(){
    	return taskService.getAllTasks(); // ID’ye göre görevi getiriyoruz.
    }
     @GetMapping("/{id}")
     public Optional<Task> getTaskById(@PathVariable Long id) {
         return taskService.getTaskById(id);
     }
    
    // Yeni bir görev (task) oluşturmak için POST isteği
    // Kullanıcıdan JSON şeklinde veri alır.
    @PostMapping
    public Task createTask(@RequestBody Task task) {
    	return taskService.createTask(task); // Gelen veriyi servise göndererek kaydediyoruz.
    }
    
    // Mevcut bir görevi güncellemek için PUT isteği
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);  // ID’ye göre görevi güncelliyoruz.
    }
    
    // Bir görevi silmek için DELETE isteği
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);  // ID’ye göre görevi siliyoruz.
    }
    
    
	
	
	
}
