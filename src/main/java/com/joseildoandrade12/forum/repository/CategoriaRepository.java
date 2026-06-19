package com.joseildoandrade12.forum.repository;

import com.joseildoandrade12.forum.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
