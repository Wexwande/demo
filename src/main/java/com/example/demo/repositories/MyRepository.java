package com.example.demo.repositories;

import com.example.demo.models.MyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyRepository extends JpaRepository<MyModel, Integer> {
List<MyModel> findAllByResponsible(String responsible);
    List<MyModel> findAllByName(String name);

}
