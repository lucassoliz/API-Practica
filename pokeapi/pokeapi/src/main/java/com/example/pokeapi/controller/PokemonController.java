
package com.example.pokeapi.controller;

import com.example.pokeapi.dto.PokemonDTO;
import com.example.pokeapi.repository.PokeAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonController {
    
    @Autowired
    private PokeAPIClient pokeAPIClient;
    
    @GetMapping("/pokemon/{pokemonId}")
    public PokemonDTO getPokemonInfo(@PathVariable("pokemonId") int pokemonId){
        return pokeAPIClient.getPokemonInfo(pokemonId);
    }
}
