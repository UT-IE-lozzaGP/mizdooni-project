package org.lozza.business.services.exceptions;

public class ReservationServiceException extends ServiceException{
    public enum Type implements ServiceException.Type {
        InvalidClient("invalid client"),
        InvalidTable("invalid table"),
        TimeSlotAlreadyBooked("Selected time slot is already booked"),
        InvalidDateTime("Selected datetime is invalid"),
        ReservationNotFound("reservation not found"),
        PassedDateTime("datetime has passed");

        private final String message;

        Type(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public ReservationServiceException(ServiceException.Type type) {
        super(type);
    }
}
