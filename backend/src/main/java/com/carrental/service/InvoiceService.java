
package com.carrental.service;

import com.carrental.entity.Booking;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class InvoiceService {

    public String generateInvoiceHtml(Booking booking) {

        String amount = String.format(
                Locale.US,
                "%,.2f",
                booking.getPrice()
        );

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html lang='en'>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<title>DriveNest Invoice</title>");

        html.append("<style>");

        html.append("* { box-sizing: border-box; }");

        html.append("body {");
        html.append("font-family: Arial, sans-serif;");
        html.append("background: #f1f5f9;");
        html.append("margin: 0;");
        html.append("padding: 30px;");
        html.append("color: #1e293b;");
        html.append("}");

        html.append(".invoice {");
        html.append("max-width: 750px;");
        html.append("margin: auto;");
        html.append("background: white;");
        html.append("padding: 40px;");
        html.append("border-radius: 12px;");
        html.append("box-shadow: 0 4px 20px rgba(0,0,0,0.08);");
        html.append("}");

        html.append(".header {");
        html.append("display: flex;");
        html.append("justify-content: space-between;");
        html.append("align-items: center;");
        html.append("border-bottom: 3px solid #2563eb;");
        html.append("padding-bottom: 20px;");
        html.append("}");

        html.append(".brand {");
        html.append("font-size: 30px;");
        html.append("font-weight: bold;");
        html.append("color: #2563eb;");
        html.append("}");

        html.append(".subtitle {");
        html.append("color: #64748b;");
        html.append("font-size: 13px;");
        html.append("margin-top: 5px;");
        html.append("}");

        html.append(".invoice-title {");
        html.append("font-size: 24px;");
        html.append("font-weight: bold;");
        html.append("}");

        html.append(".section {");
        html.append("margin-top: 28px;");
        html.append("padding-bottom: 15px;");
        html.append("border-bottom: 1px solid #e2e8f0;");
        html.append("}");

        html.append(".section h3 {");
        html.append("color: #2563eb;");
        html.append("margin-bottom: 15px;");
        html.append("}");

        html.append(".details {");
        html.append("display: flex;");
        html.append("justify-content: space-between;");
        html.append("padding: 7px 0;");
        html.append("gap: 15px;");
        html.append("}");

        html.append(".total {");
        html.append("font-size: 23px;");
        html.append("font-weight: bold;");
        html.append("color: #2563eb;");
        html.append("}");

        html.append(".status {");
        html.append("display: inline-block;");
        html.append("padding: 6px 12px;");
        html.append("background: #fef3c7;");
        html.append("color: #92400e;");
        html.append("border-radius: 20px;");
        html.append("font-weight: bold;");
        html.append("}");

        html.append(".footer {");
        html.append("text-align: center;");
        html.append("margin-top: 35px;");
        html.append("color: #64748b;");
        html.append("}");

        html.append(".print-btn {");
        html.append("display: block;");
        html.append("margin: 25px auto 0;");
        html.append("padding: 12px 25px;");
        html.append("background: #2563eb;");
        html.append("color: white;");
        html.append("border: none;");
        html.append("border-radius: 7px;");
        html.append("font-size: 15px;");
        html.append("cursor: pointer;");
        html.append("}");

        html.append("@media print {");
        html.append("body { background: white; padding: 0; }");
        html.append(".invoice { box-shadow: none; border-radius: 0; }");
        html.append(".print-btn { display: none; }");
        html.append("}");

        html.append("</style>");
        html.append("</head>");

        html.append("<body>");
        html.append("<div class='invoice'>");

        html.append("<div class='header'>");
        html.append("<div>");
        html.append("<div class='brand'>DriveNest</div>");
        html.append("<div class='subtitle'>Your Journey, Your Ride.</div>");
        html.append("</div>");
        html.append("<div class='invoice-title'>INVOICE</div>");
        html.append("</div>");

        html.append("<div class='section'>");
        html.append("<div class='details'><strong>Booking ID</strong><span>#")
                .append(booking.getId()).append("</span></div>");
        html.append("<div class='details'><strong>Invoice Date</strong><span>")
                .append(java.time.LocalDate.now()).append("</span></div>");
        html.append("</div>");

        html.append("<div class='section'>");
        html.append("<h3>Customer Details</h3>");
        html.append("<div class='details'><strong>Name</strong><span>")
                .append(HtmlEscaper.escape(booking.getUser().getName()))
                .append("</span></div>");
        html.append("<div class='details'><strong>Email</strong><span>")
                .append(HtmlEscaper.escape(booking.getUser().getEmail()))
                .append("</span></div>");
        html.append("</div>");

        html.append("<div class='section'>");
        html.append("<h3>Vehicle Details</h3>");
        html.append("<div class='details'><strong>Vehicle</strong><span>")
                .append(HtmlEscaper.escape(booking.getCar().getBrand()))
                .append(" ")
                .append(HtmlEscaper.escape(booking.getCar().getModel()))
                .append("</span></div>");

        html.append("<div class='details'><strong>Booking Type</strong><span>")
                .append(booking.getBookingType())
                .append("</span></div>");

        html.append("<div class='details'><strong>From</strong><span>")
                .append(booking.getFromDate())
                .append("</span></div>");

        html.append("<div class='details'><strong>To</strong><span>")
                .append(booking.getToDate())
                .append("</span></div>");
        html.append("</div>");

        html.append("<div class='section'>");
        html.append("<h3>Payment Summary</h3>");

        html.append("<div class='details'><strong>Total Days</strong><span>")
                .append(booking.getDays())
                .append("</span></div>");

        html.append("<div class='details'><strong>Total Amount</strong>");
        html.append("<span class='total'>&#8377;")
                .append(amount)
                .append("</span></div>");

        html.append("<div class='details'><strong>Payment Status</strong>");
        html.append("<span class='status'>")
                .append(booking.getPaymentStatus())
                .append("</span></div>");

        html.append("</div>");

        html.append("<div class='footer'>");
        html.append("<h3>Thank you for choosing DriveNest!</h3>");
        html.append("<p>Your Journey, Your Ride.</p>");
        html.append("</div>");

        html.append("<button class='print-btn' onclick='window.print()'>");
        html.append("Print / Save as PDF");
        html.append("</button>");

        html.append("</div>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
}
