package no.nav.helse.flex

import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class IntegrationTest : FellesTestOppsett() {

    @Test
    fun `202 ACCEPTED blir returnert ved gyldig feilmelding`() {
        val feilmeldingDto = FeilmeldingDto(
            requestId = "requestId",
            applikasjon = "applikasjon",
            jsonPayload = "{\"foo\": \"foo\", \"bar\": \"bar\"}"
        )

        mockMvc.perform(
            post("/syk/feilmeldinger/api/v1/feilmelding")
                .contentType(MediaType.APPLICATION_JSON)
                .content(feilmeldingDto.serialisertTilString())
        ).andExpect(status().isAccepted)
    }

    @Test
    fun `202 ACCEPTED blir returnert selv om JSON ikke stemmer med DTO`() {
        mockMvc.perform(
            post("/syk/feilmeldinger/api/v1/feilmelding")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content("{\"foo\": \"foo\", \"bar\": \"bar\"}")
        ).andExpect(status().isAccepted)
    }

    @Test
    fun `202 ACCEPTED blir returnert selv om JSON er ugyldig`() {
        mockMvc.perform(
            post("/syk/feilmeldinger/api/v1/feilmelding")
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .content("JSON")
        ).andExpect(status().isAccepted)
    }
}
