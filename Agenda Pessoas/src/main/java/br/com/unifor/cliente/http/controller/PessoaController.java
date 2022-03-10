package br.com.unifor.cliente.http.controller;

import br.com.unifor.cliente.entity.Pessoa;
import br.com.unifor.cliente.service.PessoaService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class PessoaController {

    @Autowired //OKAY
    private PessoaService clienteService;


    @Autowired //okay
    private ModelMapper  modelMapper;

    @ApiOperation(value="Cria um novo cliente")
    @PostMapping //Criar um novo cliente OKAY!
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa salvar(@RequestBody Pessoa cliente) {
        return clienteService.salvar(cliente);
    }

    @ApiOperation(value="Lista todos os clientes")
    @GetMapping("/todos") //Lista todos os clientes OKAY!
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> listaClientes() {
        return clienteService.listaCliente();
    }

    @ApiOperation(value="Lista todos os clientes ordenando por nome")
    @GetMapping("/ordenar/nome") //Lista todos os clientes OKAY!
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> listaClienteOrdenadoPornome() {
        return clienteService.listaClienteOrdenadoPornome();
    }

    @ApiOperation(value="Lista todos os clientes ordenando por mês")
    @GetMapping("/ordenar/mes") //Lista todos os clientes OKAY!
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> listaClienteOrdenadoPormes() {
        return clienteService.listaClienteOrdenadoPormes();
    }

    @ApiOperation(value="Busca clientes por nome")
    @GetMapping //Busca clientes por nome OKAY!
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa>  buscarClientePorNome(@RequestParam("nome") String nome) {
        return clienteService.buscarPorNome(nome);
    }

    @ApiOperation(value="Busca clientes por dia")
    @GetMapping("/dia/{dia}") //Busca clientes por nome OKAY!
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa>  buscarClientePorDia(@PathVariable("dia") Integer dia) {
        return clienteService.buscarPorDia(dia);
    }

    @ApiOperation(value="Busca clientes por mês")
    @GetMapping("/mes/{mes}") //Busca clientes por nome OKAY!
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa>  buscarClientePorMes(@PathVariable("mes") Integer mes) {
        return clienteService.buscarPorMes(mes);
    }

    @ApiOperation(value="Remove cliente por nome")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void removerPorNome(@RequestParam("nome") String nome) {
        clienteService.removerPorNome(nome);
    }

    @ApiOperation(value="Busca clientes por ID")
    @GetMapping("/{id}")  //Busca clientes por ID OKAY!
    @ResponseStatus(HttpStatus.OK)
    public Optional<Pessoa> buscarClientePorId(@PathVariable("id") Long id) {
        return clienteService.buscarPorId(id);
    }

    @ApiOperation(value="Remove cliente por ID")
    @DeleteMapping("/{id}") //Remover cliente por ID OKAY!
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerCliente(@PathVariable("id") Long id) {
        clienteService.buscarPorId(id)
                .map(cliente -> {
                    clienteService.removerPorId(cliente.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    @ApiOperation(value="Altera cliente por ID")
    @PutMapping("/{id}") //Altera cliente por ID OKAY!
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@PathVariable("id") Long id, @RequestBody Pessoa cliente){
        clienteService.buscarPorId(id)
                .map(clienteBase -> {
                    modelMapper.map(cliente, clienteBase);
                    clienteService.salvar(clienteBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    @ApiOperation(value="Altera cliente por nome")
    @PutMapping("/nome/{nome}") //Altera cliente por nome OKAY!
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarClientePorNome(@PathVariable("nome") String nome, @RequestBody Pessoa cliente) {
        clienteService.buscarPorNome(nome).stream()
                .findFirst()
                .map(clienteBase -> {
                    modelMapper.map(cliente, clienteBase);
                    clienteService.salvar(clienteBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    @ApiOperation(value="Busca cliente por letra inicial")
    @GetMapping("/letra/{letra}") //Altera cliente por nome OKAY!
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> buscarClientePorLetraInicial(@PathVariable("letra") String letra) {
        return clienteService.buscarPorLetraInicial(letra);
    }

}
