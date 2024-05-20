package io.nqa.menetlus.service;

import io.nqa.menetlus.entity.Menetlus;
import io.nqa.menetlus.model.CustomResponse;
import io.nqa.menetlus.model.MenetlusDTO;

import java.util.List;

public interface IMenetlusService {
    /**
     * Get all Menetlus as a list.
     *
     * @return All Menetlus entities
     */
    List<Menetlus> getAll();

    /**
     * Get CustomResponse with List of MenetlusDTO as data variable.
     *
     * @return CustomResponse model for controller
     */
    CustomResponse getAllDto();

    /**
     * Get a Menetlus entity from database by Id.
     *
     * @param id Requested Menetlus' Id
     * @return Requested Menetlus entity
     */
    Menetlus getById(final long id);

    /**
     * Get CustomResponse with MenetlusDTO as data variable.
     *
     * @param id Requested Menetlus' Id
     * @return CustomResponse model for controller
     */
    CustomResponse getByIdDto(final long id);

    /**
     * Save provided Menetlus entity into database.
     *
     * @param menetlus Menetlus entity to save
     * @return Saved entity
     */
    Menetlus save(final Menetlus menetlus);

    /**
     * Save MenetlusDTO into database and provide
     * CustomResponse to be returned to client.
     * If saving failed, the response contains
     * a message to be sent to client.
     *
     * @param menetlus MenetlusDTO model to save.
     * @return CustomResponse with saved MenetlusDTO or error message
     */
    CustomResponse save(final MenetlusDTO menetlus);

    /**
     * Send RabbitMQ message to mail microservice with.
     * Sent message contains Menetlus Id, e-mail and
     * boolean for if male is already delivered.
     *
     * @param menetlus Menetlus entity that e-mail needs to be sent to
     */
    void sendEmail(final Menetlus menetlus);

    /**
     * Validate provided Menetlus before saving, checking if e-mail
     * address and personal code are valid.
     *
     * @param menetlus Menetlus to validate
     * @return validation result
     */
    boolean validateInfo(Menetlus menetlus);

    /**
     * Set e-mail delivered status for specified Menetlus.
     *
     * @param id Id of Menetlus to change
     * @param value New value for Menetlus.emailDelivered
     * @return Menetlus entity after saving
     */
    Menetlus setEmailDelivered(long id, boolean value);
}
