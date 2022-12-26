package com.culturestamp.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.culturestamp.back.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long> {

}
