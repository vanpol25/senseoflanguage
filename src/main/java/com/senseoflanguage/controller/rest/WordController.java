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

    private final WordService wordService;
    private final WordMapper wordMapper;
    private final WordTransfer wordTransfer;

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
    public Response<WordResponse> save(@Valid @RequestBody WordRequest wordRequest,
                                       @RequestParam("collection") String collection) {
        Word request = wordMapper.map(wordRequest);
        request.setCollection(collection);
        Word word = wordService.create(request);
        WordResponse wordResponse = wordMapper.mapResponse(word);

        return new Response<>(HttpStatus.OK, wordResponse);
    }

    @ApiOperation(value = "Save model \"word\"")
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<WordResponse> update(@Valid @RequestBody WordRequest wordRequest,
                                         @PathVariable("id") String id,
                                         @RequestParam("collection") String collection) {
        Word request = wordMapper.map(wordRequest);
        request.setId(id);
        request.setCollection(collection);
        Word word = wordService.update(request);
        WordResponse wordResponse = wordMapper.mapResponse(word);

        return new Response<>(HttpStatus.OK, wordResponse);
    }

    @ApiOperation(value = "Delete model \"word\" by id")
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<WordResponse> delete(@PathVariable("id") String id) {
        Word word = wordService.delete(id);
        WordResponse wordResponse = wordMapper.mapResponse(word);

        return new Response<>(HttpStatus.OK, wordResponse);
    }

    @ApiOperation(value = "Find model \"word\" by id")
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<WordResponse> read(@PathVariable("id") String id) {
        Word word = wordService.findById(id);
        WordResponse wordResponse = wordMapper.mapResponse(word);

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
        WordResponse wordResponse = wordMapper.mapResponse(word);

        return new Response<>(HttpStatus.OK, wordResponse);
    }

    @ApiOperation(value = "Delete models \"word\" by body")
    @RequestMapping(
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<WordResponse> deleteByBody(@Valid @RequestBody WordRequest wordRequest) {
        Word request = wordMapper.map(wordRequest);
        Word word = wordService.delete(request);
        WordResponse wordResponse = wordMapper.mapResponse(word);

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
        List<Word> words = wordService.createAll(requests);
        List<WordResponse> wordResponses = wordMapper.mapListResponse(words);

        return new Response<>(HttpStatus.OK, wordResponses);
    }

    @ApiOperation(value = "Delete models \"word\" by body")
    @RequestMapping(
            value = "/all",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<List<WordResponse>> deleteAllByBody(@RequestBody List<WordRequest> wordRequests) {
        List<Word> requests = wordMapper.mapList(wordRequests);
        List<Word> words = wordService.deleteAllByBody(requests);
        List<WordResponse> wordResponses = wordMapper.mapListResponse(words);

        return new Response<>(HttpStatus.OK, wordResponses);
    }

    @ApiOperation(value = "Find all models \"word\"")
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<PageResponse<WordResponse>> readAll(@Valid PaginationRequest paginationRequest) {
        Page<Word> page = wordService.findAll(paginationRequest.toPageable());
        PageResponse<WordResponse> pageResponse = wordMapper.mapPage(page);

        return new Response<>(HttpStatus.OK, pageResponse);
    }

}
