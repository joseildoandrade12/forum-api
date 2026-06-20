package com.joseildoandrade12.forum.repository;

import com.joseildoandrade12.forum.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    List<Resposta> findByTopico_Id(Long topicoId);
}
