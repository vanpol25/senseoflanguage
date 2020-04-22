package com.senseoflanguage.controller.rest;

import com.senseoflanguage.dto.request.PaginationRequest;
import com.senseoflanguage.dto.request.WordRequest;
import com.senseoflanguage.dto.response.PageResponse;
import com.senseoflanguage.dto.response.WordResponse;
import com.senseoflanguage.mapper.WordMapper;
import com.senseoflanguage.model.Word;
import com.senseoflanguage.model.my_db.MainText;
import com.senseoflanguage.service.WordService;
import com.senseoflanguage.transfer.WordTransfer;
import com.senseoflanguage.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "Model \"word\" controller")
@RestController
@CrossOrigin
@RequestMapping("/words")
public class WordController {

    private WordService wordService;
    private WordMapper wordMapper;
    private WordTransfer wordTransfer;

    @Autowired
    public WordController(WordService wordService,
                          WordMapper wordMapper,
                          WordTransfer wordTransfer) {
        this.wordService = wordService;
        this.wordMapper = wordMapper;
        this.wordTransfer = wordTransfer;
    }

    @ApiOperation(value = "Save model \"word\"")
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<WordResponse> save(@Valid @RequestBody WordRequest wordRequest, @RequestParam("collection") String collection) {
        Word request = wordMapper.requestToModel(wordRequest);
        request.setCollection(collection);
        Word word = wordService.save(request);
        WordResponse wordResponse = wordMapper.modelToResponse(word);

        return new Response<>(HttpStatus.OK, wordResponse);
    }

    @ApiOperation(value = "Save models \"word\"")
    @RequestMapping(
            value = "/all",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<List<WordResponse>> saveAll(@RequestBody List<@Valid MainText> wordRequests, @RequestParam("collection") String collection) {
        List<Word> requests = new ArrayList<>();
        for (MainText mainText : wordRequests) {
            Word word = wordTransfer.addMyDB(mainText);
            word.setCollection(collection);
            requests.add(word);
        }
        List<Word> words = wordService.saveAll(requests);
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
    public Response<WordResponse> deleteByBody(@Valid @RequestBody WordRequest wordRequest) {
        Word request = wordMapper.requestToModel(wordRequest);
        Word word = wordService.delete(request);
        WordResponse wordResponse = wordMapper.modelToResponse(word);

        return new Response<>(HttpStatus.OK, wordResponse);
    }

    @ApiOperation(value = "Delete models \"word\" by body")
    @RequestMapping(
            value = "/all",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<List<WordResponse>> deleteAllByBody(@RequestBody List<WordRequest> wordRequests) {
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

    @ApiOperation(value = "Find model \"word\" by name")
    @RequestMapping(
            value = "/name/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<WordResponse> readByName(@PathVariable("name") String name) {
        Word word = wordService.findByName(name);
        WordResponse wordResponse = wordMapper.modelToResponse(word);

        return new Response<>(HttpStatus.OK, wordResponse);
    }

    @ApiOperation(value = "Find all models \"word\"")
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<PageResponse<WordResponse>> readAll(@Valid PaginationRequest paginationRequest) {
        Page<Word> page = wordService.findAll(paginationRequest.toPageable());
        PageResponse<WordResponse> pageResponse = wordMapper.pageToPageResponse(page);

        return new Response<>(HttpStatus.OK, pageResponse);
    }

}
