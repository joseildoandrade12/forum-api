package com.joseildoandrade12.forum.repository;

import com.joseildoandrade12.forum.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByCategoria_Id(Long categoriaId);
}
