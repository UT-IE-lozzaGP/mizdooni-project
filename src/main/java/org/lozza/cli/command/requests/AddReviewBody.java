package org.lozza.cli.command.requests;

public record AddReviewBody(
        String username,
        String restaurantName,
        Float foodRate,
        Float serviceRate,
        Float ambianceRate,
        Float overallRate,
        String comment) {
}
