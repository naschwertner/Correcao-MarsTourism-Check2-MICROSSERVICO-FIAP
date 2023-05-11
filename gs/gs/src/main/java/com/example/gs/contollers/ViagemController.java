package com.example.gs.contollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.gs.models.Viagem;
import com.example.gs.repositories.ViagemRepository;

import jakarta.validation.Valid;
  
  
  @Controller
@RequestMapping("viagem")
public class ViagemController {
	@Autowired
	private ViagemRepository viagemRepository;
	
	
	@GetMapping("")
	public ModelAndView listar() {
		List<Viagem> listViagem = viagemRepository.findAll();
		ModelAndView model = new ModelAndView("viagem/index");
		model.addObject("listarViagem",  listViagem);
		return model; 
	}
	
	
	@GetMapping("/criar")
	public ModelAndView get(Viagem model) {
		ModelAndView modelAndView = new ModelAndView("viagem/criar");
		return modelAndView; 
	}
	
	@PostMapping("/criar")
	public ResponseEntity<Viagem>  Create(@Valid @RequestBody Viagem viagem) {
		viagemRepository.save(viagem);
		return new ResponseEntity<Viagem>(viagem, HttpStatus.CREATED);
	}
	public ResponseEntity<Viagem> atualizar (@Pathvariable Lon id, @Valid @RequestBody Viagem viagem){
		Optional<Viagem> objViafem = viaemreposuitory.findById(id);
		if(!objViam.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{id}/editar")
	public ModelAndView edit(@PathVariable Long id, Viagem request) {
		Optional<Viagem> objViagem = viagemRepository.findById(id);
		
		if(objViagem.isPresent()) {
			request.fromViagem(objViagem.get());
			ModelAndView model = new ModelAndView("viagem/editar");
			model.addObject("viagemId", request.getId());
			return model;
		}
		
		return new ModelAndView("redirect/viagem");
	}


}