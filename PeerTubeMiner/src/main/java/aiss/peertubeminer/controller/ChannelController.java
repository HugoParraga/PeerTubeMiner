package aiss.peertubeminer.controller;

import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.videominer.VMChannel;
import aiss.peertubeminer.repositories.ChannelRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Channel",description = "Peertube miner API")
@RestController
@RequestMapping("/peertubeminer/channel")
public class ChannelController {

    private ChannelRepository channelRepository;

    @Autowired
    public ChannelController(ChannelRepository channelRepository) { this.channelRepository = channelRepository; }

    // GET http://localhost:8081/peertubeminer/channel/{channelHandle}
    @Operation(
            summary = "Retrieve everything from a channel",
            description = "Given some maxVideos and maxComments"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Listado de detalles de un canal",
                    content = {@Content(schema = @Schema(implementation = Caption.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",description = "Canal no encontrado",
                    content = {@Content(schema = @Schema())})
    })

    @GetMapping("/{channelHandle}")
    public VMChannel findByChannelHandle(@PathVariable String channelHandle,
                                         @RequestParam(defaultValue = "10")int maxVideos,
                                         @RequestParam(defaultValue = "2")int maxComments) {
        return channelRepository.findByChannelHandle(channelHandle, maxVideos, maxComments);
    }

    // POST http://localhost:8081/peertubeminer/channel/{channelHandle}
    @Operation(
            summary = "Post a channel to videominer",
            description = "Post a channel given his properties"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Posteo de un Canal",
                    content = {@Content(schema = @Schema(implementation = Video.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",description = "Canal no creado",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404",description = "Canal no encontrado",
                    content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping("/{channelHandle}")
    public VMChannel create(@PathVariable String channelHandle,
                            @RequestParam(defaultValue = "10") int maxVideos,
                            @RequestParam(defaultValue = "2") int maxComments,
                            @RequestHeader("X-API-KEY") String apiKey) {
        return channelRepository.create(channelHandle, maxVideos, maxComments, apiKey);
    }

}
