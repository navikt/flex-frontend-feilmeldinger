package no.nav.helse.flex

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

@Repository
interface FeilmeldingRepository : CrudRepository<FeilmeldingDbRecord, String>

@Table("payload")
data class FeilmeldingDbRecord(
    @Id
    val id: String? = null,
    val frontendApp: String,
    val opprettet: OffsetDateTime,
    val requestId: String,
    val payload: String
)