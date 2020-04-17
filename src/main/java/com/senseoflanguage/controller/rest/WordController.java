package com.senseoflanguage.controller.rest;

import com.senseoflanguage.dto.request.WordRequest;
import com.senseoflanguage.dto.response.WordResponse;
import com.senseoflanguage.mapper.WordMapper;
import com.senseoflanguage.model.Word;
import com.senseoflanguage.service.WordService;
import com.senseoflanguage.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/words")
public class WordController {

    private WordService wordService;
    private WordMapper wordMapper;

    @Autowired
    public WordController(WordService wordService,
                          WordMapper wordMapper) {
        this.wordService = wordService;
        this.wordMapper = wordMapper;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<WordResponse> create(WordRequest wordRequest) {
        Word request = wordMapper.requestToModel(wordRequest);
        Word word = wordService.create(request);
        WordResponse wordResponse = wordMapper.modelToResponse(word);

        return new Response<>(HttpStatus.CREATED, wordResponse);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<WordResponse> update(WordRequest wordRequest) {
        Word request = wordMapper.requestToModel(wordRequest);
        Word word = wordService.update(request);
        WordResponse wordResponse = wordMapper.modelToResponse(word);

        return new Response<>(HttpStatus.OK, wordResponse);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<WordResponse> delete(@PathVariable("id") String id) {
        Word word = wordService.delete(id);
        WordResponse wordResponse = wordMapper.modelToResponse(word);

        return new Response<>(HttpStatus.OK, wordResponse);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<WordResponse> deleteByBody(@RequestBody WordRequest wordRequest) {
        Word request = wordMapper.requestToModel(wordRequest);
        Word word = wordService.delete(request);
        WordResponse wordResponse = wordMapper.modelToResponse(word);

        return new Response<>(HttpStatus.OK, wordResponse);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<WordResponse> read(@PathVariable("id") String id) {
        Word word = wordService.findById(id);
        WordResponse wordResponse = wordMapper.modelToResponse(word);

        return new Response<>(HttpStatus.OK, wordResponse);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<List<WordResponse>> readAll() {
        return null;
    }

}
