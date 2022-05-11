package com.lamarana.springdemo.controllers

import com.lamarana.springdemo.entities.Carnet
import com.lamarana.springdemo.repositories.CarnetRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.util.*
import javax.validation.Valid

@RestController
@CrossOrigin("*")
@RequestMapping("/api/carnets")
class CarnetController {

    private val logger = LoggerFactory.getLogger(CarnetController::class.java)

    @Autowired
    private lateinit var carnetRepository: CarnetRepository



    @GetMapping
    fun index() = carnetRepository.findAll()

    @GetMapping("{id}")
    fun show(@PathVariable id: UUID) = carnetRepository.findById(id).orElse(null)


    @PostMapping
    fun save(@Valid @RequestBody carnet: Carnet): ResponseEntity<Any> =
            ResponseEntity(carnetRepository.save(carnet), HttpStatus.CREATED)


    @PutMapping("{id}")
    fun update(@PathVariable id: UUID, @Valid @RequestBody carnet: Carnet): ResponseEntity<Any> {

        val response = carnetRepository.findById(id).map { existType ->
            val newCarnet = existType.copy(
                    nom = carnet.nom,
                    patient = carnet.patient,
                    dateNaissance = carnet.dateNaissance
            )
            ResponseEntity.ok().body(carnetRepository.save(newCarnet))
        }.orElse(ResponseEntity.notFound().build())
        return ResponseEntity(response.body, response.statusCode)

    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: UUID) = carnetRepository.deleteById(id)

    @GetMapping("findByPatientId/{id}")
    fun findByPatientId(@PathVariable id: UUID) = carnetRepository.findByPatientId(id)

}