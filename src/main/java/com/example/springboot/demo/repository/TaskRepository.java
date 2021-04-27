package com.example.springboot.demo.repository;

import com.example.springboot.demo.model.Tasks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Tasks, Integer> {
}
