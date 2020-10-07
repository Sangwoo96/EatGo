package kr.co.fastcampus.eatgo.domain;

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
@NoArgsConstructor //매개변수가 없는 생성자 for RequestBody
@AllArgsConstructor //모든 필드를 매개변수인 생성자
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
    private List<MenuItem> menuItems;
    //무시, 일시적인 테스트를 위해

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);
    }

    public String getInformation() {
        return name + " in " + address;
    }

    public void updateInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
