package kr.co.fastcampus.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor //매개변수가 없는 생성자 생성 for RequestBody
@AllArgsConstructor //모든 필드를 매개변수인 생성자 생성
public class Restaurant {
    @Id
    @GeneratedValue
    @Setter
    private Long id;

    @NotEmpty(message = "이름은 필수칸입니다.")
    private String name;
    @NotEmpty
    private String address;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MenuItem> menuItems;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Review> reviews;

    public String getInformation() {
        return name + " in " + address;
    }

    public void updateInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = new ArrayList<>(reviews);
    }
}
