package Model;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Setter
public class Item {
    private String code;
    private String description;
    private Double unitPrice;
    private Integer Qty;


}
