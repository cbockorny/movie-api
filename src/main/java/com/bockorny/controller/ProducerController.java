package com.bockorny.controller;


import com.bockorny.dto.ProducerDTO;
import com.bockorny.dto.ProducerMinMaxDTO;
import com.bockorny.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/producer", produces = "application/json")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @GetMapping("/all")
    public ResponseEntity<List<ProducerDTO>> getProducers() {
        final List<ProducerDTO> producers = producerService.findAll();
        final HttpStatus status = producers.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(producers, status);
    }

    @GetMapping("/with-winner")
    public ResponseEntity<List<ProducerDTO>> findWithWinnerMovie() {
        final List<ProducerDTO> producers = producerService.findWithWinnerMovie();
        final HttpStatus status = producers.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(producers, status);
    }


    @GetMapping("/winner")
    public ResponseEntity<ProducerMinMaxDTO> getWinners() {
        final ProducerMinMaxDTO producers = producerService.findMinMaxWinner();
        final HttpStatus status = producers == null ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(producers, status);
    }
}
