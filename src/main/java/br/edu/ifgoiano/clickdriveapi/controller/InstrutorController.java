package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.dto.Response.InstrutorResponseDTO;
import br.edu.ifgoiano.clickdriveapi.model.InstrutorModel;
import br.edu.ifgoiano.clickdriveapi.service.InstrutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // O RequestBody correto vem daqui

import java.util.List;

@RestController
@RequestMapping("/instrutores") // Coloquei no plural para seguir o padrão
public class InstrutorController {

    private final InstrutorService instrutorService;

    public InstrutorController(InstrutorService instrutorService){
        this.instrutorService = instrutorService;
    }

    /*@PostMapping
    public ResponseEntity<InstrutorModel> criarInstrutor(@RequestBody InstrutorModel instrutor){
        return new ResponseEntity<>(instrutorService.salvar(instrutor), HttpStatus.CREATED);
    }*/

    @GetMapping
    public ResponseEntity<List<InstrutorResponseDTO>> listarTodos() {
        List<InstrutorResponseDTO> instrutores = instrutorService.listaTodos()
                .stream()
                .map(InstrutorResponseDTO::deEntity)
                .toList();
        return ResponseEntity.ok(instrutores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrutorModel> buscarInstrutorPorId(@PathVariable Long id) {
        return instrutorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstrutorModel> atualizarInstrutor(@PathVariable Long id, @RequestBody InstrutorModel instrutorAtualizado) {
        // Usando o método update que criamos no padrão Service
        return ResponseEntity.ok(instrutorService.update(id, instrutorAtualizado));
    }

    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstrutor(@PathVariable Long id){
        instrutorService.deletar(id);
        return ResponseEntity.noContent().build();
    }*/
}