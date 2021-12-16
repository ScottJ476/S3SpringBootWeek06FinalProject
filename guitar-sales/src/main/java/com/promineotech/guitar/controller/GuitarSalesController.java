package com.promineotech.guitar.controller;

import java.util.Optional;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import com.promineotech.guitar.Constants;
import com.promineotech.guitar.entity.Guitar;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/guitars")
@OpenAPIDefinition(info = @Info(title = "Guitar Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local Server.")})
public interface GuitarSalesController {
  // @formatter:off
  @Operation(
      summary = "Returns a Guitar",
      description = "Returns a guitar given a guitarId",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A guitar is returned",
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
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "guitarId",
              allowEmptyValue = false, 
              required = false, 
              description = "The guitarId name(i.e., '912CE_TAYLOR')")
      }
  )
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  Optional<Guitar> fetchGuitar(
      @Length(max = Constants.GUITARID_MAX_LENGTH)
      @Pattern(regexp = "[\\w\\s]*") 
      @RequestParam(required = false)        
          String guitarId);     
//@formatter:on
  
//  @PutMapping
//  @ResponseStatus(code = HttpStatus.OK)
//  Optional<Guitar> updateGuitar(
//      @Length(max = Constants.GUITARID_MAX_LENGTH)
//      @Pattern(regexp = "[\\w\\s]*") 
//      @RequestParam(required = false)        
//          String guitarId); 
  
  /**
   * You should add the OpenAPI doc.
   * @param image
   * @param guitarPK
   * @return
   */
  @PostMapping("/{guitarPK}/image")
  @ResponseStatus(code = HttpStatus.CREATED)
  String uploadImage(@RequestParam("image") MultipartFile image,
      @PathVariable Long guitarPK);
  
  
}
