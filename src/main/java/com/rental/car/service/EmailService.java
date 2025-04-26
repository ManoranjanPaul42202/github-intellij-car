package com.rental.car.service;

import com.rental.car.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendBookingConfirmationEmail(Booking booking) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(booking.getUser().getEmail()); // Make sure User has getEmail()
        message.setSubject("Your Booking is Confirmed!");

        String body = String.format(
                "🚗 **Car Rental Booking Confirmation** 🚗\n\n" +
                        "Hello %s,\n\n" +
                        "🎉 Your booking for **%s** has been successfully **confirmed**!\n\n" +
                        "🔖 **Booking Details:**\n" +
                        "• Booking ID     : %d\n" +
                        "• Pickup         : %s, %s\n" +
                        "• Drop-off       : %s, %s\n" +
                        "• Start Date     : %s\n" +
                        "• End Date       : %s\n" +
                        "• Status         : %s\n\n" +
                        "Thank you for choosing Car Rental. We wish you a safe and smooth journey! 😊\n\n" +
                        "Warm regards,\n" +
                        "🚙 The Car Rental Team",
                booking.getUser().getUsername(),
                booking.getCar().getName(),
                booking.getBookingId(),
                booking.getPickupLocation().getCity(),
                booking.getPickupLocation().getState(),
                booking.getDropoffLocation().getCity(),
                booking.getDropoffLocation().getState(),
                booking.getStartDate(),
                booking.getEndDate(),
                booking.getBookingStatus()
        );

        message.setText(body);
        mailSender.send(message);
    }
}
