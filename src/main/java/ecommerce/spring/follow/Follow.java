package ecommerce.spring.follow;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ecommerce.spring.store.Store;
import ecommerce.spring.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follow {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonBackReference("user-follow")
    @JoinColumn(name = "_user_id")
    private User _user;

    @ManyToOne
    @JsonBackReference("store-follow")
    @JoinColumn(name = "store_id")
    private Store store;
}
