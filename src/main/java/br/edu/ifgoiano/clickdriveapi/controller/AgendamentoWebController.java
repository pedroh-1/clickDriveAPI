//package br.edu.ifgoiano.clickdriveapi.controller;
//
//import br.edu.ifgoiano.clickdriveapi.dto.AulaRequestDTO;
//import br.edu.ifgoiano.clickdriveapi.dto.DisponibRequestDTO;
//import br.edu.ifgoiano.clickdriveapi.service.AulaService;
//import br.edu.ifgoiano.clickdriveapi.service.DisponibService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/web/agendamento")
//public class AgendamentoWebController {
//
//    @Autowired
//    private AulaService aulaService;
//
//    @Autowired
//    private DisponibService disponibService;
//
//    model.addAttribute("aulaRequest", new AulaRequestDTO(null, null, null, null, null, null, null));
//
//    @GetMapping
//    public String carregarPainel(Model model) {
//        model.addAttribute("aulas", aulaService.findAll());
//        model.addAttribute("disponibilidades", disponibService.findAll());
//        return "gestao-agendamento";
//    }
//
//    @PostMapping("/aula")
//    public String salvarAula(@ModelAttribute AulaRequestDTO dto) {
//        aulaService.save(dto);
//        return "redirect:/web/agendamento";
//    }
//
//    @PostMapping("/disponibilidade")
//    public String salvarDisponibilidade(@ModelAttribute DisponibRequestDTO dto) {
//        disponibService.save(dto);
//        return "redirect:/web/agendamento";
//    }
//}