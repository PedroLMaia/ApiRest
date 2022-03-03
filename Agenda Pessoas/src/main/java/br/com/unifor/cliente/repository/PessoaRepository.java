package br.com.unifor.cliente.repository;

import br.com.unifor.cliente.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByNome(String nome);

    List<Pessoa> findByDia(Integer dia);

    List<Pessoa> findByMes(Integer mes);

    long deleteByNome(String nome);

    List<Pessoa> removeByNome(String nome);

    List<Pessoa> findByNomeStartingWith(String letra);

    List<Pessoa> findByOrderByNomeAsc();

    List<Pessoa> findByOrderByMesAsc();
}

