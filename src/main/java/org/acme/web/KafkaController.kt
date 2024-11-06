package org.acme.web

import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.acme.kafka.KafkaCreationNotifier
import org.acme.model.CreationEvent
import org.jboss.logging.Logger

@Path("/api/kafka")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class KafkaController {
    @Inject
    private lateinit var notifier: KafkaCreationNotifier
    private val logger = Logger.getLogger(KafkaController::class.java)

    @POST
    fun notify(creationEvent: CreationEvent) {
        notifier.notifyCreation(creationEvent)
        logger.info("Message was processed: $creationEvent")
    }

}
