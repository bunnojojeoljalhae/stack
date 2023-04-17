package music.playlist.stack.dto;

import lombok.Data;
import lombok.Setter;

import java.lang.reflect.Array;

@Data
@Setter
public class ItemDto {

    private boolean collaborative;
    private String description;
    private Object external_url;
    private String href;
    private String id;
    private String name;
    private Image[] image;

}
