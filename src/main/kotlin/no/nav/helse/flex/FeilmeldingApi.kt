package no.nav.helse.flex

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

data class FeilmeldingDto(
    val requestId: String,
    val applikasjon: String,
    val jsonPayload: String
)

@RestController
@RequestMapping("/syk/feilmeldinger/api/v1")
class FeilmeldingApi {

    private val log = logger()

    @PostMapping("/feilmelding")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun lagreFeilmelding(@RequestBody feilmeldingDto: FeilmeldingDto) {
    }
}
