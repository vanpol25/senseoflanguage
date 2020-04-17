package com.senseoflanguage.controller.rest;

import com.senseoflanguage.dto.request.PaginationRequest;
import com.senseoflanguage.dto.request.WordRequest;
import com.senseoflanguage.dto.response.PageResponse;
import com.senseoflanguage.dto.response.WordResponse;
import com.senseoflanguage.mapper.WordMapper;
import com.senseoflanguage.model.Word;
import com.senseoflanguage.service.WordService;
import com.senseoflanguage.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Model \"word\" controller")
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

    @ApiOperation(value = "Create model \"word\"")
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<WordResponse> create(@Validated({WordRequest.OnCreate.class}) @RequestBody WordRequest wordRequest) {
        Word request = wordMapper.requestToModel(wordRequest);
        Word word = wordService.create(request);
        WordResponse wordResponse = wordMapper.modelToResponse(word);

        return new Response<>(HttpStatus.CREATED, wordResponse);
    }

    @ApiOperation(value = "Create models \"word\"")
    @RequestMapping(
            value = "/all",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<List<WordResponse>> createAll(@Validated({WordRequest.OnCreate.class}) @RequestBody List<WordRequest> wordRequests) {
        List<Word> requests = wordMapper.requestsToModels(wordRequests);
        List<Word> words = wordService.createAll(requests);
        List<WordResponse> wordResponses = wordMapper.modelsToResponses(words);

        return new Response<>(HttpStatus.CREATED, wordResponses);
    }

    @ApiOperation(value = "Update model \"word\"")
    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<WordResponse> update(@Validated({WordRequest.OnUpdate.class}) @RequestBody WordRequest wordRequest) {
        Word request = wordMapper.requestToModel(wordRequest);
        Word word = wordService.update(request);
        WordResponse wordResponse = wordMapper.modelToResponse(word);

        return new Response<>(HttpStatus.OK, wordResponse);
    }

    @ApiOperation(value = "Update models \"word\"")
    @RequestMapping(
            value = "/all",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<List<WordResponse>> updateAll(@Validated({WordRequest.OnUpdate.class}) @RequestBody List<WordRequest> wordRequests) {
        List<Word> requests = wordMapper.requestsToModels(wordRequests);
        List<Word> words = wordService.updateAll(requests);
        List<WordResponse> wordResponses = wordMapper.modelsToResponses(words);

        return new Response<>(HttpStatus.OK, wordResponses);
    }

    @ApiOperation(value = "Delete model \"word\" by id")
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

    @ApiOperation(value = "Delete models \"word\" by body")
    @RequestMapping(
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<WordResponse> deleteByBody(@Validated({WordRequest.OnDelete.class}) @RequestBody WordRequest wordRequest) {
        Word request = wordMapper.requestToModel(wordRequest);
        Word word = wordService.delete(request);
        WordResponse wordResponse = wordMapper.modelToResponse(word);

        return new Response<>(HttpStatus.OK, wordResponse);
    }

    @ApiOperation(value = "Delete models \"word\" by body")
    @RequestMapping(
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<List<WordResponse>> deleteAllByBody(@Validated({WordRequest.OnDelete.class}) @RequestBody List<WordRequest> wordRequests) {
        List<Word> requests = wordMapper.requestsToModels(wordRequests);
        List<Word> words = wordService.deleteAllByBody(requests);
        List<WordResponse> wordResponses = wordMapper.modelsToResponses(words);

        return new Response<>(HttpStatus.OK, wordResponses);
    }

    @ApiOperation(value = "Find model \"word\" by id")
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

    @ApiOperation(value = "Find all models \"word\"")
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<PageResponse<WordResponse>> readAll(@Validated PaginationRequest paginationRequest) {
        Page<Word> page = wordService.findAll(paginationRequest.toPageable());
        PageResponse<WordResponse> pageResponse = wordMapper.pageToPageResponse(page);

        return new Response<>(HttpStatus.OK, pageResponse);
    }

}
