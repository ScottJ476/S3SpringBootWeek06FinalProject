package com.promineotech.guitar.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.guitar.entity.Guitar;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@RequestMapping("/guitars")
@OpenAPIDefinition(info = @Info(title = "Guitar Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface GuitarSalesController {
  // @formatter: off
  @Operation(
    summary = "Returns a list of Guitars", 
    description = "Returns a list of Guitars given an optional model",
    responses = {
        @ApiResponse(
            responseCode = "200", 
            description = "A list of Guitars is returned", 
            content = @Content(
                mediaType = "application/json", 
                schema = @Schema(implementation = Guitar.class))),  
        @ApiResponse(
            responseCode = "400", 
            description = "The request parameters are invalid", 
            content = @Content(mediaType = "application/json")), 
        @ApiResponse(
            responseCode = "404", 
            description = "No Guitars were found with the input criteria", 
            content = @Content(mediaType = "application/json")),  
        @ApiResponse(
            responseCode = "500", 
            description = "An unplanned error occured", 
            content = @Content(mediaType = "application/json")),
    },
    parameters = {
        @Parameter(
            name = "model", 
            allowEmptyValue = false, 
            required = false, 
            description = "The model name (i.e., '912CE_TAYLOR)"), 
    } 
  )
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Guitar> fetchGuitars(
      @RequestParam(required = false) 
          String model);
//@formatter: on
}
