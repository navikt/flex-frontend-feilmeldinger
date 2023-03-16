package no.nav.helse.flex

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.OffsetDateTime

@RestController
@RequestMapping("/syk/feilmeldinger/api/v1")
class FeilmeldingApi(
    private val feilmeldingRepository: FeilmeldingRepository
) {

    private val log = logger()

    val frontendApplikasjoner = FrontendApp.values().map {
        it.toString()
    }

    @PostMapping("/feilmelding")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun postFeilmelding(@RequestBody feilmeldingDto: FeilmeldingDto) {
        if (feilmeldingDto.app in frontendApplikasjoner) {
            lagreFeilmelding(feilmeldingDto)
        } else {
            log.warn("Mottok feilmelding fra ukjent app: ${feilmeldingDto.app}")
        }
    }

    private fun lagreFeilmelding(feilmeldingDto: FeilmeldingDto) {
        try {
            feilmeldingRepository.save(
                FeilmeldingDbRecord(
                    opprettet = OffsetDateTime.now(),
                    requestId = feilmeldingDto.requestId,
                    app = feilmeldingDto.app,
                    payload = feilmeldingDto.payload
                )
            )
        } catch (e: Exception) {
            log.error("Feil ved lagring i database.", e)
        }
    }
}

data class FeilmeldingDto(
    val requestId: String,
    val app: String,
    val payload: String
)

enum class FrontendApp {
    SPINNSYN_FRONTEND,
    SYKPENGESOKNAD_FRONTEND,
    DITT_SYKEFRAVAER;
}
