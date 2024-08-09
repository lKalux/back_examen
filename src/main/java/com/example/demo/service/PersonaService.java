package com.example.demo.service;

import java.util.*; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Persona;
import com.example.demo.repository.PersonaRepository;

@Component
public class PersonaService {
	@Autowired
	private PersonaRepository personaRepository; 
	
	public Persona crearPersona(Persona persona) {
		return personaRepository.save(persona);
	}
	
	public Optional<Persona> getPersonaById(Long id) {
		return personaRepository.findById(id);
	}
	
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }
    
    public void eliminarPersona(Long id) {
        personaRepository.deleteById(id);
    }
    
    public Optional<Persona> actualizarPersona(Long id, Persona personaActualizada) {
        Optional<Persona> personaExistente = personaRepository.findById(id);

        if (personaExistente.isPresent()) {
            Persona persona = personaExistente.get();
            persona.setNombre(personaActualizada.getNombre());
            persona.setApellido(personaActualizada.getApellido());
            persona.setEdad(personaActualizada.getEdad());
            persona.setDireccion(personaActualizada.getDireccion()); 
            
            return Optional.of(personaRepository.save(persona));
        } else { 
        	return Optional.empty();
        }
    }
}
