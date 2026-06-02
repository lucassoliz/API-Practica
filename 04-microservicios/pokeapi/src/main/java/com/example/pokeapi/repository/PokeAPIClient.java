
package com.example.pokeapi.repository;

import com.example.pokeapi.dto.PokemonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pokeapi", url="https://pokeapi.co/api/v2")
public interface PokeAPIClient {
    
    @GetMapping("/pokemon/{pokemonId}")
    public PokemonDTO getPokemonInfo(@PathVariable("pokemonId") int pokemonId);
    
}
