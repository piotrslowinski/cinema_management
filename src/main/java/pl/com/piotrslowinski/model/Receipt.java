package pl.com.piotrslowinski.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Receipt {

    private Set<ReceiptLine> tickets = new HashSet<>();

    private BigDecimal totalPrice;

    public Receipt() {
    }

    public Set<ReceiptLine> getTickets() {
        return tickets;
    }

    public void setTickets(Set<ReceiptLine> tickets) {
        this.tickets = tickets;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addReceiptLine(String kind, int count, BigDecimal ticketPrice){
        ReceiptLine receiptLine = new ReceiptLine(kind, count, ticketPrice);
        tickets.add(receiptLine);
    }

    public void calculateTotalPrice(){
        List<BigDecimal> ticketPrices = tickets.stream().map(t -> t.totalPrice).collect(Collectors.toList());
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal price: ticketPrices){
            sum = sum.add(price);
        }
        totalPrice = sum;
    }

    public class ReceiptLine{

        private String kind;
        private Integer count;
        private BigDecimal unitPrice;
        private BigDecimal totalPrice;

        public ReceiptLine(String kind, Integer count, BigDecimal unitPrice) {
            this.kind = kind;
            this.count = count;
            this.unitPrice = unitPrice;
            totalPrice = unitPrice.multiply(BigDecimal.valueOf(count));
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public BigDecimal getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
        }
    }
}
