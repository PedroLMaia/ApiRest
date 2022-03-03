package br.com.unifor.cliente.service;


import br.com.unifor.cliente.entity.Pessoa;
import br.com.unifor.cliente.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvar(Pessoa cliente){                  //Salva ou atualiza cliente
        return pessoaRepository.save(cliente);
    }

    public List<Pessoa> listaCliente() {                    //Lista todos os clientes
        return pessoaRepository.findAll();
    }

    public List<Pessoa> listaClienteOrdenadoPornome() {                    //Lista todos os clientes
        return pessoaRepository.findByOrderByNomeAsc();
    }

    public List<Pessoa> listaClienteOrdenadoPormes() {                    //Lista todos os clientes
        return pessoaRepository.findByOrderByMesAsc();
    }

    public List<Pessoa> buscarPorNome(String nome){            //Busca cliente pelo seu nome.
        return pessoaRepository.findByNome(nome);
    }

    public Optional<Pessoa> buscarPorId(Long id){            //Busca cliente pelo seu id.
        return pessoaRepository.findById(id);
    }

    public void removerPorId(Long id){                       //Remove clientes pelo id.
        pessoaRepository.deleteById(id);
    }

    @Transactional
    public void removerPorNome(String nome) {
        pessoaRepository.deleteByNome(nome);
    }

    public List<Pessoa> buscarPorDia(Integer dia){            //Busca cliente pelo seu nome.
        return pessoaRepository.findByDia(dia);
    }

    public List<Pessoa> buscarPorMes(Integer mes){            //Busca cliente pelo seu nome.
        return pessoaRepository.findByMes(mes);
    }

    public List<Pessoa> buscarPorLetraInicial(String letra){            //Busca cliente pelo seu nome.
        return pessoaRepository.findByNomeStartingWith(letra);
    }

}
