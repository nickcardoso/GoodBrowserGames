package com.goodBrowserGames.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.goodBrowserGames.entity.GoodBrowserGamesEntity;
import com.goodBrowserGames.repository.GoodBrowserGamesRepository;



@RestController
public class GoodBrowserGamesController {
	@Autowired
	private GoodBrowserGamesRepository repository;
	
	
	 // GET ALL
    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public Iterable<GoodBrowserGamesEntity> getBrowserGames() {
        return repository.findAll();
    }

    // POST 
    @RequestMapping(value = "/games", method = RequestMethod.POST)
    public  GoodBrowserGamesEntity createBrowserGame(@RequestBody GoodBrowserGamesEntity browsergames) {
        return repository.save(browsergames); // salva no banco
    }


    @RequestMapping(value = "/games/{id}", method = RequestMethod.PUT)
    public ResponseEntity< GoodBrowserGamesEntity> Put(@PathVariable(value = "id") long id,
             @RequestBody GoodBrowserGamesEntity newGame) {
        Optional<GoodBrowserGamesEntity> oldGame = repository.findById(id);
        if (oldGame.isPresent()) {
        	GoodBrowserGamesEntity game = oldGame.get();
            game.setNome(newGame.getNome());
            game.setCategoria(newGame.getCategoria());
            game.setUrlJogo(newGame.getUrlJogo());
            game.setUrlVideo(newGame.getUrlVideo());
            game.setDescricao(newGame.getDescricao());
            game.setImagem(newGame.getImagem());
            repository.save(game);
            return new ResponseEntity<GoodBrowserGamesEntity>(game, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/games/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
        Optional<GoodBrowserGamesEntity> game = repository.findById(id);
        if (game.isPresent()) {
            repository.delete(game.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	

}
