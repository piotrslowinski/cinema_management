package pl.com.piotrslowinski.application;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import pl.com.piotrslowinski.model.Reservation;
import pl.com.piotrslowinski.model.Seat;
import pl.com.piotrslowinski.model.commands.Command;
import pl.com.piotrslowinski.model.commands.GenerateTicketsCommand;
import pl.com.piotrslowinski.model.repositories.ReservationRepository;
import pl.com.piotrslowinski.model.repositories.ShowRepository;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Component
public class GenerateTicketsHandler implements Handler<GenerateTicketsCommand, byte[]> {

    private ReservationRepository reservationRepository;
    private ShowRepository showRepository;

    public GenerateTicketsHandler(ReservationRepository reservationRepository, ShowRepository showRepository) {
        this.reservationRepository = reservationRepository;
        this.showRepository = showRepository;
    }

    @Override
    public byte[] handle(GenerateTicketsCommand command) {
        Reservation reservation = reservationRepository.get(command.getReservationNumber());
        String cinemaName = showRepository.get(reservation.getShowId()).getCinema().getName();
        Collection<Seat> seats  =reservation.getSeats();
        Document document = new Document();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, stream);
            document.open();
            document.add(new Paragraph(cinemaName.toString() + " tickets"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        String title = showRepository.get(reservation.getShowId()).getMovie().getTitle();
        Integer length = showRepository.get(reservation.getShowId()).getMovie().getLength();
        LocalDateTime time = showRepository.get(reservation.getShowId()).getDate();
        for(Seat s: seats){
            Integer row = s.getRow();
            Integer seat = s.getSeat();
            try {
                document.add(createTable(title, time, length, row, seat));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        document.close();

        return stream.toByteArray();
    }


    private static PdfPTable createTable(String title, LocalDateTime time, Integer length, Integer row, Integer seat){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm");
        PdfPTable table = new PdfPTable(2);
        PdfPCell cell = new PdfPCell(new Phrase("Movie title: " + title));
        cell.setColspan(2);
        table.addCell(cell);
        table.addCell("Date and time: " + time.format(formatter));
        table.addCell("Movie duration: " + length);
        table.addCell("Row: " + row);
        table.addCell("Seat: " + seat);
        table.setSpacingAfter(10f);
        return table;
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return GenerateTicketsCommand.class;
    }
}
