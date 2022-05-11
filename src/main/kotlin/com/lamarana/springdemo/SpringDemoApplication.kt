package com.lamarana.springdemo

import com.lamarana.springdemo.entities.Patient
import com.lamarana.springdemo.repositories.PatientRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.Instant
import java.util.*

@SpringBootApplication
class SpringDemoApplication{

    @Bean
    fun commandLineRunner(patientRepository: PatientRepository) = CommandLineRunner {
        patientRepository.save(Patient(id = UUID.randomUUID(), nom = "Diallo", dateNaissance = Date.from(Instant.now())))
    }
}
fun main(args: Array<String>) {
    runApplication<SpringDemoApplication>(*args){

    }
}

