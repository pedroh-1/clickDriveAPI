package br.edu.ifgoiano.clickdriveapi.controller;

import br.edu.ifgoiano.clickdriveapi.dto.RegistroUsuarioDTO;
import br.edu.ifgoiano.clickdriveapi.model.UsuarioModel;
import br.edu.ifgoiano.clickdriveapi.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    //Injetando o service no controller
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;

    }
    //Endpoint para cadastrar um novo usuario

    @PostMapping
    public ResponseEntity<UsuarioModel> criarUsuario(@RequestBody RegistroUsuarioDTO dto){
        UsuarioModel novoUsuario = usuarioService.salvar(dto);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);

    }
    // Endpoint para listar todos os usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios(){
        List<UsuarioModel> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);

    }
    // Endpoint para buscar usuarios por id
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> buscarUsuario(@PathVariable Long id){
        Optional<UsuarioModel> usuario = usuarioService.buscarPorId(id);

        // Se encontrar o usuário, retorna 200 OK. Se não, retorna 404 Not Found.
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());//


    }
    @DeleteMapping("/{id}") // Adicione o /{id} aqui!
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public UsuarioModel atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioModel usuarioModel){
        return usuarioService.update(id, usuarioModel);
    }


}
